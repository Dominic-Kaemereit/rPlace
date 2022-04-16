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
        val placePlayer = this.place.placePlayerCach.getPlayer(player.uniqueId)
        if (placePlayer != null) {
            this.place.cooledowns.get(placePlayer.getUUID())?.let { placePlayer.setLastBlockRePlace(it) }
            this.place.placePlayerCach.savePlayer(placePlayer)
        }
        this.place.placePlayerCach.unloadPlayer(player.uniqueId)
        this.place.scoreboardManager.removePlayer(player)
    }
}