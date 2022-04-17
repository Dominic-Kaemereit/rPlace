package de.d151l.place.plugin.scorebord

import de.d151l.place.plugin.Place
import fr.mrmicky.fastboard.FastBoard
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt

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

        bord.updateTitle(this.place.config.scoreboardTitle)
        bord.updateLines(this.place.config.scoreboard)

        this.list[player.uniqueId] = bord
    }

    fun updateScoreBoard() {
        val boarderSize = this.place.config.worldBorderSize
        val max: Double = boarderSize*boarderSize

        //        val progress = (Math.round((this.place.blockHistoryCount / max * 100)*100.0) / 100.0).toString()
        val progress = (((this.place.blockHistoryCount / max * 100) * 100.0).roundToInt() / 100.0).toString()
        val onlinePlayers = getOnlinePlayers()
        val maxPlayers = Bukkit.getMaxPlayers().toString()

        for (player in Bukkit.getOnlinePlayers()) {
            val fastBoard = this.list[player.uniqueId]
            val tempList: MutableList<String> = mutableListOf()
            val placePlayer = this.place.placePlayerCach.getPlayer(player.uniqueId)

            for (line in this.place.config.scoreboard) {
                if (placePlayer != null) {
                    tempList.add(line
                        .replace("%progress%", progress)
                        .replace("%cooldown%", getCountdown(player))
                        .replace("%blocks%", placePlayer.getBlockCount().toString())
                        .replace("%onlinePlayers%", onlinePlayers)
                        .replace("%maxPlayers%", maxPlayers)
                        .replace("%ranking%", placePlayer.getRanking().toString())
                    )
                }
            }

            fastBoard?.updateLines(tempList)
        }
    }

    private fun getCountdown(player: Player):String {
        val cooldowen = this.place.countdownManager.getCooldowen(player)
        if (cooldowen > 0) {
            if (cooldowen == TimeUnit.SECONDS.toSeconds(1)) {
                return "1 " + this.place.messagesConfig.second
            } else {
                return  "$cooldowen " + this.place.messagesConfig.seconds
            }
        }
        return this.place.messagesConfig.redy
    }

    private fun getOnlinePlayers(): String {
        val size = Bukkit.getOnlinePlayers().size
        val maxPlayers = Bukkit.getMaxPlayers()

        val onlinePlayers = if (size >= maxPlayers) {
            "§c${size}"
        } else {
            "§a${size}"
        }
        return onlinePlayers
    }

    fun removePlayer(player: Player) {
        this.list.remove(player.uniqueId)
    }
}