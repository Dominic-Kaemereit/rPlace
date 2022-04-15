package de.d151l.place.plugin.material

import de.d151l.place.plugin.Place
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.Player

/**
 * @created 15/04/2022 - 20:57
 * @project R-Place
 * @author  D151l
 */
object MaterialChecker {

    fun checkInventory(place: Place, player: Player, material: Material): Boolean {

        if (!material.isBlock) {
            player.sendMessage("Diesen Block darftst du nicht benutzen1!")
            return false
        }

        if (!material.isSolid) {
            player.sendMessage("Diesen Block darftst du nicht benutzen2!")
            return false
        }
        return true
    }

    fun check(place: Place, player: Player, block: Block, material: Material): Boolean {
        if (material.isAir) {
            return false
        }

        if (!material.isBlock) {
            player.sendMessage("Diesen Block darftst du nicht platzieren!")
            return false
        }

        if (!material.isSolid) {
            player.sendMessage("Diesen Block darftst du nicht platzieren!")
            return false
        }

        if (block.type == material) {
            player.sendMessage("Dieser Block wurde hier schon platziert!")
            return false
        }

        return true
    }
}