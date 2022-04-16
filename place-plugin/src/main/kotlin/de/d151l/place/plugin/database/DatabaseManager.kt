package de.d151l.place.plugin.database

import de.d151l.place.api.database.DatabaseSupport
import de.d151l.place.api.database.DatabaseType
import de.d151l.place.plugin.Place
import de.d151l.place.plugin.database.h2.H2
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

    lateinit var database: DatabaseSupport

    init {
        if (this.databaseType == DatabaseType.MONGODB) {
            this.database = MongoDB()
        } else if (this.databaseType == DatabaseType.MYSQL) {
            this.database = MySQL()
        } else {
            this.database = H2()
        }

        val config = this.place.config
        this.database.connect(config.host, config.port, config.user, config.password, config.databaseName)
    }
}