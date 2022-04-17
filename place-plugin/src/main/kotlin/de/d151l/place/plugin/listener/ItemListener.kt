package de.d151l.place.plugin.listener

import de.d151l.place.plugin.Place
import de.d151l.place.plugin.world.block.BlockChecker
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

/**
 * @created 16/04/2022 - 16:53
 * @project rPlace
 * @author  D151l
 */
class ItemListener(
    private val place: Place
): Listener {

    @EventHandler
    fun onInventoryInteractEvent(event: InventoryClickEvent) {
        if (event.currentItem != null) {
            val item = event.currentItem
            if (item != null) {
                if (checkIfItem(item)) {

                    if (item.itemMeta.localizedName == "item-remover") {
                        event.isCancelled = true
                        return
                    }
                    if (item.itemMeta.localizedName == "item-checker") {
                        event.isCancelled = true
                        return
                    }
                }

                if (!BlockChecker.check(place, event.whoClicked as Player, item.type)) {
                    event.isCancelled = true
                    return
                }
            }

        }

        if (event.cursor != null) {
            val item = event.cursor
            if (item != null) {
                if (checkIfItem(item)) {

                    if (item.itemMeta.localizedName == "item-remover") {
                        event.isCancelled = true
                        return
                    }
                    if (item.itemMeta.localizedName == "item-checker") {
                        event.isCancelled = true
                        return
                    }
                }

                if (!BlockChecker.check(place, event.whoClicked as Player, item.type)) {
                    event.isCancelled = true
                    return
                }
            }
        }
    }

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        if (event.hasItem()) {
            val item = event.item
            if (item != null) {
                if (checkIfItem(item)) {

                    if (item.itemMeta.localizedName == "item-remover")
                        Bukkit.dispatchCommand(event.player, "removeBlock")
                    if (item.itemMeta.localizedName == "item-checker")
                        Bukkit.dispatchCommand(event.player, "checkBlock")
                }
            }
        }

        if (event.hasBlock())
            return

        event.isCancelled = true
    }

    @EventHandler
    fun onPlayerDropItem(event: PlayerDropItemEvent) {
        val itemDrop = event.itemDrop
        event.isCancelled = true

        if (itemDrop.itemStack.itemMeta != null)
            if (checkIfItem(itemDrop.itemStack))
                    return

        itemDrop.itemStack.amount = 0
    }

    private fun checkIfItem(item: ItemStack): Boolean {
        if (item.itemMeta == null)
            return false

        if (!item.itemMeta.hasLocalizedName())
            return false

        if (!item.itemMeta.localizedName.contains("item"))
            return false
        return true
    }
}