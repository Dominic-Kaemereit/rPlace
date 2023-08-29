package de.d151l.place.plugin.command

import de.d151l.place.plugin.Place
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * @created 16/04/2022 - 17:26
 * @project rPlace
 * @author  D151l
 */
class RemoveBlockCommand(
    private val place: Place
): CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        commandName: String,
        args: Array<out String>
    ): Boolean {
        val player: Player = sender as Player

        val targetBlock = player.getTargetBlockExact(100) ?: return false

        if (!this.place.blockHistoryManager.hasBlockanHistory(targetBlock)) {
            player.sendMessage(place.messagesConfig.blockHasNoHistory.replace("%prefix%", place.messagesConfig.prefix))
            player.playSound(targetBlock.location, Sound.valueOf(this.place.config.blockHasNoHistorySound), 1f, 1f)
            return false
        }

        val blockHistory = this.place.blockHistoryManager.getBlockHistory(targetBlock)
        val targetPlayerUuid = blockHistory.getPlayer()

        if (Bukkit.getPlayer(targetPlayerUuid) == null) {
            val placePlayer = this.place.databaseManager.database.getPlacePlayer(targetPlayerUuid)

            placePlayer.setBlockToCount(placePlayer.getBlockCount() - 1)
            this.place.databaseManager.database.savePlacePlayer(placePlayer)
        } else {
            val placePlayer = this.place.placePlayerCach.getPlayer(targetPlayerUuid)

            placePlayer?.setBlockToCount(placePlayer.getBlockCount() - 1)
            this.place.placePlayerCach.savePlayer(placePlayer!!)
        }

        this.place.blockHistoryCount--

        this.place.databaseManager.database.deleteBlockHistory(blockHistory)
        targetBlock.type = Material.BEDROCK
        player.sendMessage(place.messagesConfig.blockRemoveSuccessfully.replace("%prefix%", place.messagesConfig.prefix))
        return true
    }
}