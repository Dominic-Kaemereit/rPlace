package de.d151l.place.plugin.listener

import de.d151l.place.plugin.Place
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

        if (this.place.cooledowns.containsKey(player.uniqueId)) {
            var cooldown: Long? = this.place.cooledowns[player.uniqueId]

            if (cooldown != null) {
                if (cooldown >= System.currentTimeMillis()) {
                    cooldown = (cooldown - System.currentTimeMillis())
                    val seconds = TimeUnit.MILLISECONDS.toSeconds(cooldown)

                    if (seconds == TimeUnit.SECONDS.toSeconds(1)) {
                        player.sendMessage("Du musst noch eine Sekunde warten!")
                    } else {
                        player.sendMessage("Du musst noch $seconds Sekunden warten!")
                    }
                    event.isCancelled = true
                    return
                }
            }
        }

        if (player.inventory.itemInMainHand == null) {
            event.isCancelled = true
            return
        }

        val type = player.inventory.itemInMainHand.type
        event.isCancelled = true

        if (!type.isBlock)
            return

        if (!type.isSolid)
            return

        this.place.cooledowns[player.uniqueId] = (System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(20))
        event.block.type = type
    }

    @EventHandler
    fun onPlaceBreak(event: BlockPlaceEvent) {
        event.isCancelled = true
    }
}