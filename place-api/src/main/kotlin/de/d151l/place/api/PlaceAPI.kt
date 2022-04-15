package de.d151l.place.api

/**
 * @created 15/04/2022 - 02:14
 * @project R-Place
 * @author  D151l
 */
class PlaceAPI {

    init {
        instance = this
    }

    fun shutdown() {

    }

    companion object {
        lateinit var instance: PlaceAPI
    }
}