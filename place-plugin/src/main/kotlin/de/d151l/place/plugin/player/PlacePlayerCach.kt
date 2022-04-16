package de.d151l.place.plugin.player

import de.d151l.place.api.player.PlacePlayer
import de.d151l.place.plugin.Place
import org.bukkit.entity.Player
import java.util.UUID

/**
 * @created 15/04/2022 - 23:45
 * @project R-Place
 * @author  D151l
 */
class PlacePlayerCach(
    private val place: Place
) {

    private val players: MutableMap<UUID, PlacePlayer> = mutableMapOf()

    fun loadPlayer(player: Player): PlacePlayer {
        if (this.place.databaseManager.database.isPlayerRegistered(player.uniqueId)) {
            val placePlayer = this.place.databaseManager.database.getPlacePlayer(player.uniqueId)
            placePlayer.setRanking(this.place.databaseManager.database.getRanking(placePlayer))
            this.players[player.uniqueId] = placePlayer
            return placePlayer
        }

        val placePlayer: PlacePlayer = PlacePlayerImpl(player.uniqueId.toString(), player.name) as PlacePlayer
        this.place.databaseManager.database.createPlayerInDatabase(placePlayer)
        placePlayer.setRanking(this.place.databaseManager.database.getRanking(placePlayer))
        this.players[player.uniqueId] = placePlayer
        return placePlayer
    }

    fun unloadPlayer(uuid: UUID) {
        val placePlayer = this.players[uuid]
        if (placePlayer != null) {
            this.place.databaseManager.database.savePlacePlayer(placePlayer)
        }
    }

    fun getPlayer(uuid: UUID): PlacePlayer? {
        return this.players[uuid]
    }

    fun savePlayer(player: PlacePlayer) {
        this.players[player.getUUID()] = player
    }
}