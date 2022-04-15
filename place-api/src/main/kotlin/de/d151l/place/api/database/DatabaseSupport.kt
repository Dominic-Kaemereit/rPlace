package de.d151l.place.api.database

import de.d151l.place.api.player.PlacePlayer
import java.util.UUID

/**
 * @created 15/04/2022 - 02:22
 * @project R-Place
 * @author  D151l
 */
interface DatabaseSupport {

    fun connect(host: String, port: Int, user: String, password: String)

    fun isPlayerRegistered(uuid: UUID): Boolean

    fun getPlacePlayer(uuid: UUID): PlacePlayer

    fun savePlacePlayer(placePlayer: PlacePlayer)

    fun createPlayerInDatabase(placePlayer: PlacePlayer)
}