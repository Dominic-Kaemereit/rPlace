package de.d151l.place.plugin.database.mongodb

import com.google.gson.Gson
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import de.d151l.place.api.database.DatabaseSupport
import de.d151l.place.api.player.PlacePlayer
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
    private lateinit var collection: MongoCollection<Document>
    private val gson: Gson = Gson()

    override fun connect(host: String, port: Int, user: String, password: String, database: String) {
        this.client = MongoClients.create("mongodb://" + user + ":" + password + "@" + host + ":" + port);
        this.collection = this.client.getDatabase(database).getCollection("placePlayer")
    }

    override fun closeConnection() {
        this.client.close()
    }

    override fun isPlayerRegistered(uuid: UUID): Boolean {
        if (this.collection.find(Filters.eq("uuid", uuid.toString())).first() == null)
            return false
        return true
    }

    override fun getPlacePlayer(uuid: UUID): PlacePlayer {
        val toString = uuid.toString()
        val document: Document = this.collection.find(Filters.eq("uuid", toString)).first()
        return gson.fromJson(document.toJson(), PlacePlayerImpl::class.java)
    }

    override fun savePlacePlayer(placePlayer: PlacePlayer) {
        val document = this.gson.fromJson(this.gson.toJson(placePlayer as PlacePlayerImpl), Document::class.java)
        this.collection.replaceOne(Filters.eq("uuid", placePlayer.getUUID().toString()), document)
    }

    override fun createPlayerInDatabase(placePlayer: PlacePlayer) {
        val document = this.gson.fromJson(this.gson.toJson(placePlayer as PlacePlayerImpl), Document::class.java)
        this.collection.insertOne(document)
    }
}