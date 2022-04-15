package de.d151l.place.plugin

import de.d151l.place.Place
import org.bukkit.plugin.java.JavaPlugin

class SpigotPlugin: JavaPlugin() {

    private lateinit var place: Place

    override fun onEnable() {
        this.place = Place(this)
    }

    override fun onDisable() {
        this.place.shutdown()
    }
}