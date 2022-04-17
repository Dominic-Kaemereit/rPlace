package de.d151l.place.plugin.config

import com.twodevsstudio.simplejsonconfig.api.Config
import com.twodevsstudio.simplejsonconfig.interfaces.Comment
import com.twodevsstudio.simplejsonconfig.interfaces.Configuration
import de.d151l.place.api.database.DatabaseType

/**
 * @created 17/04/2022 - 12:03
 * @project rPlace
 * @author  D151l
 */
@Configuration("database")
class DatabaseConfig: Config() {
    @Comment("LOCALSTORAGE, MYSQL and MONGODB are the database types currently available.")
    val databaseType = DatabaseType.LOCALSTORAGE.name
    val host = "127.0.0.1"
    val port = 1234
    val user = "root"
    val password = "password123"
    val databaseName = "place"
}