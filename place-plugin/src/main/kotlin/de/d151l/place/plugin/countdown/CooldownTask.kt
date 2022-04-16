package de.d151l.place.plugin.countdown

import de.d151l.place.plugin.Place
import org.bukkit.Bukkit

/**
 * @created 15/04/2022 - 20:29
 * @project R-Place
 * @author  D151l
 */
class CooldownTask(
    private val place: Place
) {

    init {
        Bukkit.getScheduler().runTaskTimerAsynchronously(this.place.javaPlugin, Runnable {
            this.place.scoreboardManager.updateScoreBoard()
        }, 10, 10)
    }
}