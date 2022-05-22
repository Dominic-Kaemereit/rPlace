package de.d151l.place.plugin.version

import de.d151l.place.plugin.Place
import org.bukkit.Bukkit
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class PluginVersion(
    private val place: Place
) {

    private val apiLink = "https://api.spigotmc.org/legacy/update.php?resource=102155"
    private val pluginVersion: String = this.place.javaPlugin.description.version

    var newestVersion = true

    init {

        Bukkit.getScheduler().runTaskAsynchronously(this.place.javaPlugin, Runnable {
            val currentSpigotMCVersion = getCurrentSpigotMCVersion()

            if (currentSpigotMCVersion != this.pluginVersion)
                this.newestVersion = false
        })
    }

    private fun getCurrentSpigotMCVersion(): String {
        val connection: HttpsURLConnection = URL(this.apiLink).openConnection() as HttpsURLConnection
        connection.doOutput = true
        connection.requestMethod = "GET"

        return BufferedReader(InputStreamReader(connection.inputStream)).readLine()
    }

}