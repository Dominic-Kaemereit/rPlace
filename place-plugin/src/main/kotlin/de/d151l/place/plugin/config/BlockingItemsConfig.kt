package de.d151l.place.plugin.config

import com.twodevsstudio.simplejsonconfig.api.Config
import com.twodevsstudio.simplejsonconfig.interfaces.Configuration
import org.bukkit.Material

/**
 * @created 16/04/2022 - 12:58
 * @project R-Place
 * @author  D151l
 */
@Configuration("blockingItems")
class BlockingItemsConfig: Config() {

    val blockingItems: List<String> = mutableListOf(
        Material.BEDROCK.name,
        Material.CONDUIT.name,
        Material.TURTLE_EGG.name,
        Material.BAMBOO.name,
        "FARMLAND"
    )

    val blockingItemKeys: List<String> = mutableListOf(
        "TRAPDOOR",
        "PRESSURE_PLATE"
    )
}