package de.d151l.place.plugin.scorebord

import de.d151l.place.plugin.Place
import fr.mrmicky.fastboard.FastBoard
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @created 15/04/2022 - 18:52
 * @project R-Place
 * @author  D151l
 */
class ScoreboardManager(
    private val place: Place
) {

    private var list: MutableMap<UUID, FastBoard> = mutableMapOf()

    fun setScoreBoard(player: Player) {
        val bord = FastBoard(player)
        val placePlayer = this.place.placePlayerCach.getPlayer(player.uniqueId)

        val placeSize = this.place.placeSize
        val max: Double = placeSize*placeSize

        bord.updateTitle("§lDEINSERVER.NET")
        if (placePlayer != null) {
            bord.updateLines(
                "",
                "§7Fortschritt",
                "§8»§a ${this.place.roundNumber(this.place.blockHistoryCount, max)}%",
                "",
                "§7Countdown",
                "§8»§a Bereit",
                "",
                "§7Deine Blöcke",
                "§8»§a ${placePlayer.getBlockCount()}",
                "",
                "§7Spieler",
                "§8»§a ${Bukkit.getOnlinePlayers().size}§7/${Bukkit.getMaxPlayers()}",
                "",
                "§7Deine Platzierung",
                "§8»§a #0"
            )
        }

        this.list[player.uniqueId] = bord
    }

    fun updateCountdown(player: Player) {

        val cooldowen = this.place.countdownManager.getCooldowen(player)

        if (cooldowen > 0) {

            if (cooldowen == TimeUnit.SECONDS.toSeconds(1)) {
                this.list[player.uniqueId]?.updateLine(5, "§8»§a 1 Sekunde")
            } else {
                this.list[player.uniqueId]?.updateLine(5, "§8»§a $cooldowen Sekunden")
            }
            return
        }
        this.list[player.uniqueId]?.updateLine(5, "§8»§a Bereit")
    }

    fun updatePlayerCount() {
        val size = Bukkit.getOnlinePlayers().size
        val maxPlayers = Bukkit.getMaxPlayers()

        val onlinePlayers = if (size >= maxPlayers) {
            "§c${size}"
        } else {
            "§a${size}"
        }
        for (entry in this.list) {
            entry.value.updateLine(11, "§8»§a ${onlinePlayers}§7/${maxPlayers}")
        }
    }

    fun updateBlockCount(uuid: UUID) {
        val player = this.place.placePlayerCach.getPlayer(uuid)
        if (player != null) {
            this.list[uuid]?.updateLine(8, "§8»§a ${player.getBlockCount()}")
        }
    }

    fun removePlayer(player: Player) {
        this.list.remove(player.uniqueId)
    }
}