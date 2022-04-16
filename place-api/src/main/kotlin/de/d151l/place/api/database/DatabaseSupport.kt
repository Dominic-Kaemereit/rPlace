package de.d151l.place.api.database

import de.d151l.place.api.block.BlockHistory
import de.d151l.place.api.player.PlacePlayer
import java.util.UUID

/**
 * @created 15/04/2022 - 02:22
 * @project R-Place
 * @author  D151l
 */
interface DatabaseSupport {

    fun connect(host: String, port: Int, user: String, password: String, database: String)

    fun closeConnection()

    fun isPlayerRegistered(uuid: UUID): Boolean

    fun getPlacePlayer(uuid: UUID): PlacePlayer

    fun savePlacePlayer(placePlayer: PlacePlayer)

    fun createPlayerInDatabase(placePlayer: PlacePlayer)

    fun getRanking(uuid: UUID): Int

    fun isBlockHistory(blockHistory: BlockHistory): Boolean

    fun addBlockHistory(blockHistory: BlockHistory)

    fun getBlockHistory(location: String): BlockHistory

    fun updateBlockHistory(blockHistory: BlockHistory)

    fun deleteBlockHistory(blockHistory: BlockHistory)

    fun getBlockHistoryCount(): Int
}