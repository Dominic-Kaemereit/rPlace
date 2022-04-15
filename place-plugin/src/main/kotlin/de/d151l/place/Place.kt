package de.d151l.place

import org.bukkit.plugin.java.JavaPlugin

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