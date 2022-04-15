package de.d151l.place.plugin.plugin

import de.d151l.place.plugin.Place
import org.bukkit.plugin.java.JavaPlugin

/**
 * @created 15/04/2022 - 02:12
 * @project R-Place
 * @author  D151l
 */
class SpigotPlugin: JavaPlugin() {

    private lateinit var place: Place

    override fun onEnable() {
        this.place = Place(this)
    }

    override fun onDisable() {
        this.place.shutdown()
    }
}