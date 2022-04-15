package de.d151l.place.plugin

import de.d151l.place.plugin.world.PlaceWorldManager
import org.bukkit.plugin.java.JavaPlugin

/**
 * @created 15/04/2022 - 02:14
 * @project R-Place
 * @author  D151l
 */
class Place(
    val javaPlugin: JavaPlugin
) {

    val placeWorldManager: PlaceWorldManager = PlaceWorldManager(this)

    init {
        instance = this
    }

    fun shutdown() {

    }

    companion object {
        lateinit var instance: Place
    }
}