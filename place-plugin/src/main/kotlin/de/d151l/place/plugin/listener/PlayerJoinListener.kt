package de.d151l.place.plugin.listener

import de.d151l.place.plugin.Place
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

/**
 * @created 15/04/2022 - 13:25
 * @project R-Place
 * @author  D151l
 */
class PlayerJoinListener(
    private val place: Place
): Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player

        player.teleport(Location(this.place.placeWorldManager.world,0.5, 101.0, 0.5))
        player.gameMode = GameMode.CREATIVE

        val placePlayer = this.place.placePlayerCach.loadPlayer(player)
        this.place.placePlayerCach.savePlayer(placePlayer)
        this.place.cooledowns[player.uniqueId] = placePlayer.getLastBlockRePlace()

        if (this.place.config.scoreboardEnabled)
            this.place.scoreboardManager.setScoreBoard(player)

        addItems(player)

        if (player.hasPermission("place.warning.old.plugin.version") && this.place.config.enableOldPluginWarning
            && !this.place.pluginVersion.newestVersion) {
            player.sendMessage(place.messagesConfig.oldPluginWarning.replace("%prefix%", place.messagesConfig.prefix)
                .replace("%link%", "https://www.spigotmc.org/resources/r-place.102155/"))
        }
    }

    private fun addItems(player: Player) {
        if (player.hasPermission("place.item.remover")) {
            val blockRemover = ItemStack(Material.valueOf(this.place.config.itemRemoverMaterial))
            val blockRemoverMeta = blockRemover.itemMeta!!

            blockRemoverMeta.setDisplayName(this.place.messagesConfig.itemBlockRemoverName)
            blockRemoverMeta.persistentDataContainer.set(NamespacedKey(this.place.javaPlugin, "item-remover"), PersistentDataType.STRING, "item-remover")
            blockRemover.itemMeta = blockRemoverMeta
            player.inventory.setItem(7, blockRemover)
        }
        if (player.hasPermission("place.item.checker")) {
            val blockChecker = ItemStack(Material.valueOf(this.place.config.itemCheckerMaterial))
            val blockCheckerMeta = blockChecker.itemMeta!!

            blockCheckerMeta.setDisplayName(this.place.messagesConfig.itemBlockCheckerName)
            blockCheckerMeta.persistentDataContainer.set(NamespacedKey(this.place.javaPlugin, "item-checker"), PersistentDataType.STRING, "item-checker")
            blockChecker.itemMeta = blockCheckerMeta
            player.inventory.setItem(8, blockChecker)
        }
    }
}