package de.d151l.place.plugin.database.mysql

import de.d151l.place.api.block.BlockHistory
import de.d151l.place.api.database.DatabaseSupport
import de.d151l.place.api.player.PlacePlayer
import java.util.*

/**
 * @created 16/04/2022 - 13:58
 * @project rPlace
 * @author  D151l
 */
class MySQL: DatabaseSupport {

    override fun connect(host: String, port: Int, user: String, password: String, database: String) {
        TODO("Not yet implemented")
    }

    override fun closeConnection() {
        TODO("Not yet implemented")
    }

    override fun isPlayerRegistered(uuid: UUID): Boolean {
        TODO("Not yet implemented")
    }

    override fun getPlacePlayer(uuid: UUID): PlacePlayer {
        TODO("Not yet implemented")
    }

    override fun savePlacePlayer(placePlayer: PlacePlayer) {
        TODO("Not yet implemented")
    }

    override fun createPlayerInDatabase(placePlayer: PlacePlayer) {
        TODO("Not yet implemented")
    }

    override fun getRanking(uuid: UUID): Int {
        TODO("Not yet implemented")
    }

    override fun isBlockHistory(blockHistory: BlockHistory): Boolean {
        TODO("Not yet implemented")
    }

    override fun addBlockHistory(blockHistory: BlockHistory) {
        TODO("Not yet implemented")
    }

    override fun getBlockHistory(location: String): BlockHistory {
        TODO("Not yet implemented")
    }

    override fun updateBlockHistory(blockHistory: BlockHistory) {
        TODO("Not yet implemented")
    }

    override fun deleteBlockHistory(blockHistory: BlockHistory) {
        TODO("Not yet implemented")
    }

    override fun getBlockHistoryCount(): Int {
        TODO("Not yet implemented")
    }
}