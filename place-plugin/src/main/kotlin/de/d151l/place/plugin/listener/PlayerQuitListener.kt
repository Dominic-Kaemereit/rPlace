package de.d151l.place.plugin.listener

import de.d151l.place.plugin.Place
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

/**
 * @created 15/04/2022 - 13:26
 * @project R-Place
 * @author  D151l
 */
class PlayerQuitListener(
    private val place: Place
): Listener {

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        val player = event.player
    }
}