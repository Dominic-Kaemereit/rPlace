package de.d151l.place.plugin.listener

import de.d151l.place.plugin.Place
import org.bukkit.Bukkit
import org.bukkit.GameMode
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
        player.teleport(this.place.placeWorldManager.world.spawnLocation.set(0.5, 101.0, 0.5))
        player.gameMode = GameMode.CREATIVE
        this.place.placePlayerCach.loadPlayer(player.uniqueId)
        this.place.scoreboardManager.setScoreBoard(player)
        this.place.scoreboardManager.updatePlayerCount()
    }
}