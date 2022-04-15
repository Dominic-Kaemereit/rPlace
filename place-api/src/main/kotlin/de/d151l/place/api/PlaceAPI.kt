package de.d151l.place.api

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