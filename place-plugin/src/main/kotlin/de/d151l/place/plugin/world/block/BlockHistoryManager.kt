package de.d151l.place.plugin.world.block

import de.d151l.place.api.block.BlockHistory
import de.d151l.place.plugin.Place
import org.bukkit.Location
import org.bukkit.block.Block
import org.bukkit.entity.Player
import java.util.UUID

/**
 * @created 16/04/2022 - 01:23
 * @project rPlace
 * @author  D151l
 */
class BlockHistoryManager(
    private val place: Place
) {

    fun addBlock(player: Player, location: Location) {
        val block: BlockHistory = BlockHistoryImpl(player.uniqueId.toString(), System.currentTimeMillis(),
            "${location.blockX}:${location.blockY}:${location.blockZ}")

        if (!this.place.databaseManager.database.isBlockHistory(block)) {
            this.place.databaseManager.database.addBlockHistory(block)
            this.place.blockHistoryCount++
        } else
            this.place.databaseManager.database.updateBlockHistory(block)
    }

    fun hasBlockanHistory(current: Block): Boolean {
        val location = current.location
        val block: BlockHistory = BlockHistoryImpl(UUID.randomUUID().toString(), System.currentTimeMillis(),
            "${location.blockX}:${location.blockY}:${location.blockZ}")

        if (this.place.databaseManager.database.isBlockHistory(block))
            return true
        return false
    }

    fun getBlockHistory(current: Block): BlockHistory {
        val location = current.location
        val blockHistory =
            this.place.databaseManager.database.getBlockHistory("${location.blockX}:${location.blockY}:${location.blockZ}")
        return blockHistory
    }
}