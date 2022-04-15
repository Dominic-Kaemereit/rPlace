package de.d151l.place.plugin.chunk

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

        for (x in 0..15) {
            for (z in 0..15) {
                chunk.setBlock(x, 100, z, Material.BEDROCK)
                chunk.setBlock(x, 99, z, Material.BEDROCK)
            }
        }
        return chunk
    }
}