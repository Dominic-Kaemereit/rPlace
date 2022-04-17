package de.d151l.place.plugin.util

import de.d151l.place.plugin.Place
import java.util.UUID

/**
 * @created 16/04/2022 - 18:27
 * @project rPlace
 * @author  D151l
 */
object UUIDFetcher {

    fun getUuidByName(name: String): UUID {
        val placePlayerByName = Place.instance.databaseManager.database.getPlacePlayerByName(name)
        return placePlayerByName.getUUID()
    }

    fun getNameByUuid(uuid: UUID): String {
        val placePlayerByName = Place.instance.databaseManager.database.getPlacePlayer(uuid)
        return placePlayerByName.getName()
    }
}