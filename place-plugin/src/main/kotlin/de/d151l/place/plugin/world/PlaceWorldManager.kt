package de.d151l.place.plugin.world

import de.d151l.place.plugin.Place
import de.d151l.place.plugin.world.chunk.PlaceChunkGenerator
import org.bukkit.Bukkit
import org.bukkit.GameRule
import org.bukkit.World
import org.bukkit.WorldCreator

/**
 * @created 15/04/2022 - 13:27
 * @project R-Place
 * @author  D151l
 */
class PlaceWorldManager(
    private val place: Place
) {

    lateinit var world: World

    init {
        loadWorld()
    }

    private fun loadWorld() {
        val worldCreator: WorldCreator = WorldCreator("place")
        worldCreator.generator(PlaceChunkGenerator())
        val world = Bukkit.createWorld(worldCreator)

        if (world != null) {
            world.setGameRule(GameRule.SPAWN_RADIUS, 0)
            world.setGameRule(GameRule.DO_WEATHER_CYCLE, false)
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false)
            world.setGameRule(GameRule.RANDOM_TICK_SPEED, 0)

            world.spawnLocation = world.spawnLocation.set(0.5, 101.0, 0.5)
            world.time = 1000
        }

        this.world = world!!
    }

    fun setWorldBorder() {
        world.worldBorder.center = world.spawnLocation.set(0.5, 101.0, 0.5)
        world.worldBorder.size = this.place.config.worldBorderSize
        world.worldBorder.warningDistance = 0
    }
}