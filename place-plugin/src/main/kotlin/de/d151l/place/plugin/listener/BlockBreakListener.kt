package de.d151l.place.plugin.listener

import de.d151l.place.plugin.Place
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

/**
 * @created 15/04/2022 - 13:11
 * @project R-Place
 * @author  D151l
 */
class BlockBreakListener(
    private val place: Place
): Listener {

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val player = event.player
    }
}