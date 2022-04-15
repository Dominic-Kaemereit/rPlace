package de.d151l.place.plugin

import org.bukkit.plugin.java.JavaPlugin

/**
 * @created 15/04/2022 - 02:14
 * @project R-Place
 * @author  D151l
 */
class Place(
    val javaPlugin: JavaPlugin
) {

    init {
        instance = this
    }

    fun shutdown() {

    }

    companion object {
        lateinit var instance: Place
    }
}