package de.d151l.place.plugin.command

import de.d151l.place.plugin.Place
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

        val targetBlock = player.getTargetBlock(100) ?: return false

        if (!this.place.blockHistoryManager.hasBlockanHistory(targetBlock)) {
            player.sendMessage(place.messagesConfig.blockHasNoHistory.replace("%prefix%", place.messagesConfig.prefix))
            player.playSound(targetBlock.location, Sound.ENTITY_STRIDER_EAT, 1f, 1f)
            return false
        }

        return true
    }
}