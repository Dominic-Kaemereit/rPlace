package de.d151l.place.plugin

import de.d151l.place.api.database.DatabaseType
import de.d151l.place.plugin.block.BlockHistoryManager
import de.d151l.place.plugin.countdown.CountdownManager
import de.d151l.place.plugin.database.DatabaseManager
import de.d151l.place.plugin.listener.BlockListener
import de.d151l.place.plugin.listener.PlayerJoinListener
import de.d151l.place.plugin.listener.PlayerQuitListener
import de.d151l.place.plugin.listener.ProtectionListener
import de.d151l.place.plugin.player.PlacePlayerCach
import de.d151l.place.plugin.scorebord.ScoreboardManager
import de.d151l.place.plugin.task.CooldownTask
import de.d151l.place.plugin.world.PlaceWorldManager
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.util.UUID

/**
 * @created 15/04/2022 - 02:14
 * @project R-Place
 * @author  D151l
 */
class Place(
    val javaPlugin: JavaPlugin
) {

    val placeWorldManager: PlaceWorldManager = PlaceWorldManager(this)
    val scoreboardManager: ScoreboardManager = ScoreboardManager(this)
    val countdownManager: CountdownManager = CountdownManager(this)
    val databaseManager: DatabaseManager = DatabaseManager(DatabaseType.MONGODB)
    val placePlayerCach: PlacePlayerCach = PlacePlayerCach(this)
    val blockHistoryManager: BlockHistoryManager = BlockHistoryManager(this)

    val cooldownTask: CooldownTask = CooldownTask(this)
    val cooledowns: MutableMap<UUID, Long> = mutableMapOf()

    val placeSize = 31.0
    val blockHistoryCount: Int

    init {
        instance = this

        val pluginManager = Bukkit.getPluginManager()
        pluginManager.registerEvents(PlayerJoinListener(this), this.javaPlugin)
        pluginManager.registerEvents(PlayerQuitListener(this), this.javaPlugin)
        pluginManager.registerEvents(BlockListener(this), this.javaPlugin)
        pluginManager.registerEvents(ProtectionListener(this), this.javaPlugin)

        this.placeWorldManager.setWorldBorder()
        this.blockHistoryCount = this.databaseManager.database.getBlockHistoryCount()
    }

    fun shutdown() {
        Bukkit.getOnlinePlayers().forEach {
            this.placePlayerCach.unloadPlayer(it.uniqueId)
            it.kick(null)
        }

        this.databaseManager.database.closeConnection()
    }

    companion object {
        lateinit var instance: Place
    }

    fun roundNumber(valueOne: Int, valueTow: Double): Double {
        var valueThree = valueOne.toDouble() / valueTow.toDouble()
        valueThree -= valueThree % 0.01
        valueThree *= 100.0
        valueThree = valueThree.toInt() / 100.0
        return valueThree
    }
}