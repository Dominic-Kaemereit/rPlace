package de.d151l.place.plugin.block

import de.d151l.place.api.block.BlockHistory
import java.util.*

/**
 * @created 16/04/2022 - 01:19
 * @project rPlace
 * @author  D151l
 */
class BlockHistoryImpl(
    private val uuid: String,
    private val time: Long,
    private val location: String
): BlockHistory {

    override fun getLocation(): String {
        return this.location
    }

    override fun getPlayer(): UUID {
        return UUID.fromString(this.uuid)
    }

    override fun getTime(): Long {
        return this.time
    }
}