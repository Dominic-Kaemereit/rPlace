package de.d151l.place.plugin.config

import com.twodevsstudio.simplejsonconfig.api.Config
import com.twodevsstudio.simplejsonconfig.interfaces.Comment
import com.twodevsstudio.simplejsonconfig.interfaces.Configuration
import de.d151l.place.api.database.DatabaseType
import org.bukkit.Bukkit

/**
 * @created 16/04/2022 - 11:19
 * @project R-Place
 * @author  D151l
 */
@Configuration("config")
class PluginConfig: Config() {

    val blockCooldown = 21

    @Comment("The worldBorderSize must be an odd number. (1, 7, 15, 31, 101, 1001...)")
    val worldBorderSize = 31.0

    val scoreboardTitle = "§lDEINSERVER.NET"
    val scoreboard: List<String> = mutableListOf(
        "",
        "§7Fortschritt",
        "§8»§a %progress%%",
        "",
        "§7Countdown",
        "§8»§a %cooldown%",
        "",
        "§7Deine Blöcke",
        "§8»§a %blocks%",
        "",
        "§7Spieler",
        "§8»§a %onlinePlayers%§7/%maxPlayers%",
        "",
        "§7Deine Platzierung",
        "§8»§a #%ranking%"
    )

    @Comment("LOCALSTORAGE, MYSQL and MONGODB are the database types currently available.")
    val databaseType = DatabaseType.LOCALSTORAGE.name
    val host = "127.0.0.1"
    val port = 1234
    val user = "root"
    val password = "password123"
    val databaseName = "place"
}