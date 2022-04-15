package de.d151l.place.plugin.listener

import de.d151l.place.plugin.Place
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

/**
 * @created 15/04/2022 - 13:25
 * @project R-Place
 * @author  D151l
 */
class PlayerJoinListener(
    private val place: Place
): Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
    }
}