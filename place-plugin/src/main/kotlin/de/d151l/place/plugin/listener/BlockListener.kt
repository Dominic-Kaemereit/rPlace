package de.d151l.place.plugin.listener

import de.d151l.place.plugin.Place
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent

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

        event.block.type = type
    }

    @EventHandler
    fun onPlaceBreak(event: BlockPlaceEvent) {
        event.isCancelled = true
    }
}