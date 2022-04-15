package de.d151l.place.plugin.countdown

import de.d151l.place.plugin.Place
import org.bukkit.entity.Player
import java.util.concurrent.TimeUnit

/**
 * @created 15/04/2022 - 20:23
 * @project R-Place
 * @author  D151l
 */
class CountdownManager(
    private val place: Place
) {

    fun getCooldowen(player: Player): Long {
        if (this.place.cooledowns.containsKey(player.uniqueId)) {
            var cooldown: Long? = this.place.cooledowns[player.uniqueId]

            if (cooldown != null) {
                if (cooldown >= System.currentTimeMillis()) {
                    cooldown = (cooldown - System.currentTimeMillis())
                    return TimeUnit.MILLISECONDS.toSeconds(cooldown)
                }
            }
        }
        return 0
    }
}