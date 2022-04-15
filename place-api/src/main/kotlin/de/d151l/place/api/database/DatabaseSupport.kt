package de.d151l.place.api.database

import de.d151l.place.api.player.PlacePlayer
import java.util.UUID

interface DatabaseSupport {

    fun connect(host: String, port: Int, user: String, password: String)

    fun isPlayerRegistered(uuid: UUID): Boolean

    fun getPlacePlayer(uuid: UUID): PlacePlayer

    fun savePlacePlayer(placePlayer: PlacePlayer)

    fun createPlayerInDatabase(placePlayer: PlacePlayer)
}