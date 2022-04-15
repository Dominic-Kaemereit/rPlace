package de.d151l.place.plugin.listener

import de.d151l.place.plugin.Place
import de.d151l.place.plugin.material.MaterialChecker
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
        val cooldowen = this.place.countdownManager.getCooldowen(player)

        if (cooldowen > 0) {

            if (cooldowen == TimeUnit.SECONDS.toSeconds(1)) {
                player.sendMessage("Du musst noch eine Sekunde warten!")
            } else {
                player.sendMessage("Du musst noch $cooldowen Sekunden warten!")
            }
            event.isCancelled = true
            return
        }

        val type = player.inventory.itemInMainHand.type
        event.isCancelled = true

        val successful = MaterialChecker.check(this.place, player, event.block, type)

        if (!successful)
            return

        this.place.cooledowns[player.uniqueId] = (System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(20))
        event.block.type = type
        this.place.placePlayerCach.getPlayer(player.uniqueId)?.addBlockToCount()
        this.place.blockHistoryManager.addBlock(player, event.block.location)
        this.place.scoreboardManager.updateCountdown(player)
        this.place.scoreboardManager.updateBlockCount(player.uniqueId)
    }

    @EventHandler
    fun onPlaceBreak(event: BlockPlaceEvent) {
        event.isCancelled = true
    }
}