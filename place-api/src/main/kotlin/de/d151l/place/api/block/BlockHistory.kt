package de.d151l.place.api.block

import java.util.UUID

/**
 * @created 16/04/2022 - 01:15
 * @project rPlace
 * @author  D151l
 */
interface BlockHistory {

    fun getLocation(): String

    fun getPlayer(): UUID

    fun getTime(): Long
}