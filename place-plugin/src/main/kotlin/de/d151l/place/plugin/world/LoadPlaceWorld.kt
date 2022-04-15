package de.d151l.place.plugin.world

import de.d151l.place.plugin.chunk.PlaceChunkGenerator
import net.minecraft.world.level.World
import org.bukkit.Bukkit
import org.bukkit.WorldCreator

/**
 * @created 15/04/2022 - 13:44
 * @project R-Place
 * @author  D151l
 */
object LoadPlaceWorld {

    fun loadWorld() {
        val worldCreator: WorldCreator = WorldCreator("world")
        worldCreator.generator(PlaceChunkGenerator())
        Bukkit.createWorld(worldCreator)
    }
}