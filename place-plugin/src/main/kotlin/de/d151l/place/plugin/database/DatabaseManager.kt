package de.d151l.place.plugin.database

import de.d151l.place.api.database.DatabaseSupport
import de.d151l.place.api.database.DatabaseType
import de.d151l.place.plugin.database.mongodb.MongoDB

/**
 * @created 15/04/2022 - 23:36
 * @project R-Place
 * @author  D151l
 */
class DatabaseManager(
private val databaseType: DatabaseType
) {

    lateinit var database: DatabaseSupport

    init {
        if (this.databaseType == DatabaseType.MONGODB) {
            this.database = MongoDB()
        }

        this.database.connect("127.0.0.1", 27017, "admin", "test123", "admin")
    }
}