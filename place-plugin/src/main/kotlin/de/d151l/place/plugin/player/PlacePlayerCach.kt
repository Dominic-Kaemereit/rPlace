package de.d151l.place.plugin.player

import de.d151l.place.api.player.PlacePlayer
import de.d151l.place.plugin.Place
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

    fun loadPlayer(uuid: UUID) {
        if (this.place.databaseManager.database.isPlayerRegistered(uuid)) {
            val placePlayer = this.place.databaseManager.database.getPlacePlayer(uuid)
            this.players[uuid] = placePlayer
            return
        }

        val placePlayer: PlacePlayer = PlacePlayerImpl(uuid.toString()) as PlacePlayer
        this.place.databaseManager.database.createPlayerInDatabase(placePlayer)
        this.players[uuid] = placePlayer
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