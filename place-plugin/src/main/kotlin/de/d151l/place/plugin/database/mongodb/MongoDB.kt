package de.d151l.place.plugin.database.mongodb

import com.google.common.collect.Lists
import com.google.gson.Gson
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Sorts
import de.d151l.place.api.block.BlockHistory
import de.d151l.place.api.database.DatabaseSupport
import de.d151l.place.api.player.PlacePlayer
import de.d151l.place.plugin.block.BlockHistoryImpl
import de.d151l.place.plugin.player.PlacePlayerImpl
import org.bson.Document
import java.util.*


/**
 * @created 15/04/2022 - 23:18
 * @project R-Place
 * @author  D151l
 */
class MongoDB: DatabaseSupport {

    private lateinit var client: MongoClient
    private lateinit var playerCollection: MongoCollection<Document>
    private lateinit var blockCollection: MongoCollection<Document>
    private val gson: Gson = Gson()

    override fun connect(host: String, port: Int, user: String, password: String, database: String) {
        this.client = MongoClients.create("mongodb://" + user + ":" + password + "@" + host + ":" + port);
        this.playerCollection = this.client.getDatabase(database).getCollection("placePlayer")
        this.blockCollection = this.client.getDatabase(database).getCollection("placeBlockHistory")
    }

    override fun closeConnection() {
        this.client.close()
    }

    override fun isPlayerRegistered(uuid: UUID): Boolean {
        if (this.playerCollection.find(Filters.eq("uuid", uuid.toString())).first() == null)
            return false
        return true
    }

    override fun getPlacePlayer(uuid: UUID): PlacePlayer {
        val toString = uuid.toString()
        val document: Document = this.playerCollection.find(Filters.eq("uuid", toString)).first()
        return gson.fromJson(document.toJson(), PlacePlayerImpl::class.java)
    }

    override fun getPlacePlayerByName(name: String): PlacePlayer {
        val document: Document = this.playerCollection.find(Filters.eq("name", name)).first()
        return gson.fromJson(document.toJson(), PlacePlayerImpl::class.java)
    }

    override fun savePlacePlayer(placePlayer: PlacePlayer) {
        val document = this.gson.fromJson(this.gson.toJson(placePlayer as PlacePlayerImpl), Document::class.java)
        this.playerCollection.replaceOne(Filters.eq("uuid", placePlayer.getUUID().toString()), document)
    }

    override fun createPlayerInDatabase(placePlayer: PlacePlayer) {
        val document = this.gson.fromJson(this.gson.toJson(placePlayer as PlacePlayerImpl), Document::class.java)
        this.playerCollection.insertOne(document)
    }

    override fun getRanking(placePlayer: PlacePlayer): Int {

        val documents: List<Document> =
            this.playerCollection.find().sort(Sorts.descending("blocks")).into(Lists.newArrayList())

        for (current in documents) {
            if (current["uuid"] == placePlayer.getUUID().toString())
                return (documents.indexOf(current) + 1)
        }
        return 0
    }

    override fun isBlockHistory(blockHistory: BlockHistory): Boolean {
        if (this.blockCollection.find(Filters.eq("location", blockHistory.getLocation())).first() == null)
            return false
        return true
    }

    override fun addBlockHistory(blockHistory: BlockHistory) {
        val document = this.gson.fromJson(this.gson.toJson(blockHistory as BlockHistoryImpl), Document::class.java)
        this.blockCollection.insertOne(document)
    }

    override fun getBlockHistory(location: String): BlockHistory {
        val document: Document = this.blockCollection.find(Filters.eq("location", location)).first() as Document
        return gson.fromJson(document.toJson(), BlockHistoryImpl::class.java)
    }

    override fun updateBlockHistory(blockHistory: BlockHistory) {
        val document = this.gson.fromJson(this.gson.toJson(blockHistory as BlockHistoryImpl), Document::class.java)
        this.blockCollection.replaceOne(Filters.eq("location", blockHistory.getLocation()), document)
    }

    override fun deleteBlockHistory(blockHistory: BlockHistory) {
        this.blockCollection.deleteOne(Filters.eq("location", blockHistory.getLocation()))
    }

    override fun getBlockHistoryCount(): Int {
        return this.blockCollection.find().count()
    }
}