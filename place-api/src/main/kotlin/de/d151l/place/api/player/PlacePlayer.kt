package de.d151l.place.api.player

import java.util.UUID

interface PlacePlayer {

    fun getUUID(): UUID

    fun getBlockCount(): Int

    fun addBlockToCount()
}