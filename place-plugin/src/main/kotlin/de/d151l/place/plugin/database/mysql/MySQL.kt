package de.d151l.place.plugin.database.mysql

import de.d151l.place.api.block.BlockHistory
import de.d151l.place.api.database.DatabaseSupport
import de.d151l.place.api.player.PlacePlayer
import de.d151l.place.plugin.world.block.BlockHistoryImpl
import de.d151l.place.plugin.player.PlacePlayerImpl
import java.sql.*
import java.util.*

/**
 * @created 16/04/2022 - 13:58
 * @project rPlace
 * @author  D151l
 */
class MySQL: DatabaseSupport {

    private lateinit var connection: Connection

    override fun connect(host: String, port: Int, user: String, password: String, database: String) {
        try {
            val connectionUrl = "jdbc:mysql://$host:3306/$database?autoReconnect=true"
            this.connection = DriverManager.getConnection(connectionUrl, user, password);
            val statement: Statement = connection.createStatement();
            statement.executeUpdate(
                "CREATE TABLE IF NOT EXISTS placePlayer(uuid VARCHAR(36), name VARCHAR(36), blocks INTEGER, lastBlockRePlace VARCHAR(36), ranking INTEGER)"
            );
            statement.executeUpdate(
                "CREATE TABLE IF NOT EXISTS placeBlockHistory(uuid VARCHAR(36), time VARCHAR(36), location VARCHAR(36))"
            );
        } catch (exception: SQLException) {
            exception.printStackTrace();
        }
    }

    override fun closeConnection() {
        try {
            connection.close()
        } catch (exception: SQLException) {
            exception.printStackTrace()
        }
    }

    override fun isPlayerRegistered(uuid: UUID): Boolean {
        val preparedStatement = connection
            .prepareStatement("SELECT * FROM placePlayer WHERE uuid = ?")
        preparedStatement.setString(1, uuid.toString())

        val resultSet: ResultSet = preparedStatement.executeQuery()
        if (resultSet.next())
            return true
        return false
    }

    override fun getPlacePlayer(uuid: UUID): PlacePlayer {
        val preparedStatement = connection
            .prepareStatement("SELECT * FROM placePlayer WHERE uuid = ?")
        preparedStatement.setString(1, uuid.toString())

        val resultSet: ResultSet = preparedStatement.executeQuery()
        resultSet.next()

        val player: PlacePlayerImpl = PlacePlayerImpl(
            resultSet.getObject("uuid") as String,
            resultSet.getString("name")
        )
        player.setRanking(resultSet.getInt("ranking"))
        player.setLastBlockRePlace(resultSet.getString("lastBlockRePlace").toLong())
        player.setBlockToCount(resultSet.getInt("blocks"))
        return player
    }

    override fun getPlacePlayerByName(name: String): PlacePlayer {
        val preparedStatement = connection
            .prepareStatement("SELECT * FROM placePlayer WHERE name = ?")
        preparedStatement.setString(1, name)

        val resultSet: ResultSet = preparedStatement.executeQuery()
        resultSet.next()

        val player: PlacePlayerImpl = PlacePlayerImpl(
            resultSet.getString("uuid"),
            resultSet.getString("name")
        )
        player.setRanking(resultSet.getInt("ranking"))
        player.setLastBlockRePlace(resultSet.getString("lastBlockRePlace").toLong())
        player.setBlockToCount(resultSet.getInt("blocks"))
        return player
    }

    override fun savePlacePlayer(placePlayer: PlacePlayer) {
        val deleteStatement = connection
            .prepareStatement("DELETE FROM placePlayer WHERE uuid = ?")
        deleteStatement.setString(1, placePlayer.getUUID().toString())
        deleteStatement.executeUpdate()
        createPlayerInDatabase(placePlayer)
    }

    override fun createPlayerInDatabase(placePlayer: PlacePlayer) {
        val preparedStatement: PreparedStatement = connection
            .prepareStatement("INSERT INTO placePlayer(uuid, name, blocks, lastBlockRePlace, ranking) VALUES (?, ?, ?, ?, ?)")
        preparedStatement.setString(1, placePlayer.getUUID().toString())
        preparedStatement.setString(2, placePlayer.getName())
        preparedStatement.setInt(3, placePlayer.getBlockCount())
        preparedStatement.setLong(4, placePlayer.getLastBlockRePlace())
        preparedStatement.setInt(5, placePlayer.getRanking())

        preparedStatement.executeUpdate()
    }

    override fun getRanking(placePlayer: PlacePlayer): Int {
        val preparedStatement = connection
            .prepareStatement("SELECT * FROM placePlayer ORDER BY blocks DESC")

        val resultSet: ResultSet = preparedStatement.executeQuery()
        val placePlayers: MutableList<UUID> = mutableListOf()
        while (resultSet.next()) {
            placePlayers.add(UUID.fromString(resultSet.getString("uuid")))
        }

        val indexOf = placePlayers.indexOf(placePlayer.getUUID())
        return (indexOf + 1)
    }


    override fun isBlockHistory(blockHistory: BlockHistory): Boolean {
        val preparedStatement = connection
            .prepareStatement("SELECT * FROM placeBlockHistory WHERE location = ?")
        preparedStatement.setString(1, blockHistory.getLocation())

        val resultSet: ResultSet = preparedStatement.executeQuery()
        if (resultSet.next())
            return true
        return false
    }

    override fun addBlockHistory(blockHistory: BlockHistory) {
        val preparedStatement: PreparedStatement = connection
            .prepareStatement("INSERT INTO placeBlockHistory(uuid, time, location) VALUES (?, ?, ?)")
        preparedStatement.setString(1, blockHistory.getPlayer().toString())
        preparedStatement.setString(2, blockHistory.getTime().toString())
        preparedStatement.setString(3, blockHistory.getLocation())

        preparedStatement.executeUpdate()
    }

    override fun getBlockHistory(location: String): BlockHistory {
        val preparedStatement = connection
            .prepareStatement("SELECT * FROM placeBlockHistory WHERE location = ?")
        preparedStatement.setString(1, location)

        val resultSet: ResultSet = preparedStatement.executeQuery()
        resultSet.next()

        return BlockHistoryImpl(
            resultSet.getString("uuid"),
            resultSet.getString("time").toLong(),
            resultSet.getString("location")
        )
    }

    override fun updateBlockHistory(blockHistory: BlockHistory) {
        deleteBlockHistory(blockHistory)
        addBlockHistory(blockHistory)
    }

    override fun deleteBlockHistory(blockHistory: BlockHistory) {
        val deleteStatement = connection
            .prepareStatement("DELETE FROM placeBlockHistory WHERE location = ?")
        deleteStatement.setString(1, blockHistory.getLocation())
        deleteStatement.executeUpdate()
    }

    override fun getBlockHistoryCount(): Int {
        val preparedStatement = connection
            .prepareStatement("SELECT count(*) FROM placeBlockHistory")
        val resultSet: ResultSet = preparedStatement.executeQuery()
        while (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0
    }
}