package de.d151l.place.plugin.world.chunk

import org.bukkit.Material
import org.bukkit.World
import org.bukkit.generator.ChunkGenerator
import java.util.*

/**
 * @created 15/04/2022 - 13:20
 * @project R-Place
 * @author  D151l
 */
class PlaceChunkGenerator: ChunkGenerator() {

    override fun generateChunkData(world: World, random: Random, x: Int, z: Int, biome: BiomeGrid): ChunkData {
        val chunk: ChunkData = createChunkData(world)

        for (locationX in 0..15) {
            for (locationZ in 0..15) {
                chunk.setBlock(locationX, 100, locationZ, Material.BEDROCK)
                chunk.setBlock(locationX, 99, locationZ, Material.BEDROCK)
            }
        }
        return chunk
    }
}