package de.d151l.place.plugin.database

import de.d151l.place.api.database.DatabaseSupport
import de.d151l.place.api.database.DatabaseType
import de.d151l.place.plugin.Place
import de.d151l.place.plugin.database.localStorage.LocalStorage
import de.d151l.place.plugin.database.mongodb.MongoDB
import de.d151l.place.plugin.database.mysql.MySQL

/**
 * @created 15/04/2022 - 23:36
 * @project R-Place
 * @author  D151l
 */
class DatabaseManager(
    private val place: Place,
    private val databaseType: DatabaseType
) {

    var database: DatabaseSupport

    init {
        when (this.databaseType) {
            DatabaseType.LOCALSTORAGE -> {
                this.database = LocalStorage()
            }
            DatabaseType.MONGODB -> {
                this.database = MongoDB()
            }
            DatabaseType.MYSQL -> {
                this.database = MySQL()
            }
        }

        val databaseConfig = this.place.databaseConfig
        this.database.connect(
            databaseConfig.host,
            databaseConfig.port,
            databaseConfig.user,
            databaseConfig.password,
            databaseConfig.databaseName
        )
    }
}