package de.d151l.place.plugin.player

import de.d151l.place.api.player.PlacePlayer
import java.util.*

/**
 * @created 15/04/2022 - 23:28
 * @project R-Place
 * @author  D151l
 */
class PlacePlayerImpl(
    private val uuid: String
): PlacePlayer {

    private var blocks: Int = 0
    private var lastBlockRePlace: Long = 0

    override fun getUUID(): UUID {
        return UUID.fromString(this.uuid)
    }

    override fun getBlockCount(): Int {
        return this.blocks
    }

    override fun addBlockToCount() {
        this.blocks++
    }

    override fun getLastBlockRePlace(): Long {
        return this.lastBlockRePlace
    }

    override fun setLastBlockRePlace(time: Long) {
        this.lastBlockRePlace = time
    }
}