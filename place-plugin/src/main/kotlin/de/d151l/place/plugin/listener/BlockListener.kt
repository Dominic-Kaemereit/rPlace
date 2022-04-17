package de.d151l.place.plugin.listener

import de.d151l.place.plugin.Place
import de.d151l.place.plugin.world.block.BlockChecker
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import java.util.concurrent.TimeUnit

/**
 * @created 15/04/2022 - 13:11
 * @project R-Place
 * @author  D151l
 */
class BlockListener(
    private val place: Place
): Listener {

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val player = event.player
        val block = event.block

        if (block.location.blockY != 100) {
            event.isCancelled = true
            return
        }

        val cooldowen = this.place.countdownManager.getCooldowen(player)

        if (cooldowen > 0) {

            if (cooldowen == TimeUnit.SECONDS.toSeconds(1)) {
                player.sendMessage(place.messagesConfig.blockWaiteSecond.replace("%prefix%", place.messagesConfig.prefix))
            } else {
                player.sendMessage(place.messagesConfig.blockWaiteSeconds.replace("%prefix%", place.messagesConfig.prefix)
                    .replace("%cooldowen%", cooldowen.toString()))

            }
            event.isCancelled = true
            return
        }

        val type = player.inventory.itemInMainHand.type
        event.isCancelled = true

        val successful = BlockChecker.check(this.place, player, block, type)

        if (!successful)
            return

        this.place.cooledowns[player.uniqueId] = (System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(
            this.place.config.blockCooldown.toLong()
        ))
        block.type = type

        this.place.placePlayerCach.getPlayer(player.uniqueId)?.addBlockToCount()
        this.place.blockHistoryManager.addBlock(player, block.location)
    }

    @EventHandler
    fun onPlaceBreak(event: BlockPlaceEvent) {
        event.isCancelled = true
    }
}