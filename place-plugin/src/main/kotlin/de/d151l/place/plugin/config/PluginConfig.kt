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
    val worldBorderSize = 313.0

    val scoreboardTitle = "§aServer §8| §7r/Place"
    val scoreboard: List<String> = mutableListOf(
        "§4§8§l§m---------------",
        " §8§l» §7Progress§3",
        "    §8» §a %progress%%",
        "",
        " §8§l» §7Countdown§3",
        "   §8» §a %cooldown%",
        "",
        " §8§l» §7Your blocks§3",
        "    §8» §a %blocks%",
        "",
        " §8§l» §7Players§3",
        "    §8»§a %onlinePlayers%§7/%maxPlayers%",
        "",
        " §8§l» §7Your ranking§3",
        "    §8»§a #%ranking%"
    )

    @Comment("List of sounds: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html")
    val blockHasNoHistorySound = "ENTITY_STRIDER_EAT"

    @Comment("List of materials: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html")
    val itemRemoverMaterial = "STICK"
    val itemCheckerMaterial = "END_ROD"
}