package de.d151l.place.plugin.block

import de.d151l.place.plugin.Place
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.Player

/**
 * @created 15/04/2022 - 20:57
 * @project R-Place
 * @author  D151l
 */
object BlockChecker {

    fun check(place: Place, player: Player, material: Material): Boolean {

        if (place.blockingItems.blockingItems.contains(material.name.uppercase())) {
            player.sendMessage(place.messagesConfig.blockPlaceNotAllow.replace("%prefix%", place.messagesConfig.prefix))
            return false
        }

        if (place.blockingItems.blockingItemKeys.any { material.name.contains(it.uppercase()) }) {
            player.sendMessage(place.messagesConfig.blockPlaceNotAllow.replace("%prefix%", place.messagesConfig.prefix))
            return false
        }

        if (!material.isBlock) {
            player.sendMessage(place.messagesConfig.blockPlaceNotAllow.replace("%prefix%", place.messagesConfig.prefix))
            return false
        }
        return true
    }
    fun check(place: Place, player: Player, block: Block, material: Material): Boolean {
        if (material.isAir) {
            return false
        }

        if (place.blockingItems.blockingItems.contains(material.name.uppercase())) {
            player.sendMessage(place.messagesConfig.blockPlaceNotAllow.replace("%prefix%", place.messagesConfig.prefix))
            return false
        }

        if (place.blockingItems.blockingItemKeys.any { material.name.contains(it.uppercase()) }) {
            player.sendMessage(place.messagesConfig.blockPlaceNotAllow.replace("%prefix%", place.messagesConfig.prefix))
            return false
        }

        if (!material.isBlock) {
            player.sendMessage(place.messagesConfig.blockPlaceNotAllow.replace("%prefix%", place.messagesConfig.prefix))
            return false
        }

        if (!material.isSolid) {
            player.sendMessage(place.messagesConfig.blockPlaceNotAllow.replace("%prefix%", place.messagesConfig.prefix))
            return false
        }

        if (block.type == material) {
            player.sendMessage(place.messagesConfig.blockAlreadyPlaced.replace("%prefix%", place.messagesConfig.prefix))
            return false
        }
        return true
    }
}