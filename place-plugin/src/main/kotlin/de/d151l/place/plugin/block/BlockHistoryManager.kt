package de.d151l.place.plugin.block

import de.d151l.place.api.block.BlockHistory
import de.d151l.place.plugin.Place
import org.bukkit.Location
import org.bukkit.entity.Player

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
}