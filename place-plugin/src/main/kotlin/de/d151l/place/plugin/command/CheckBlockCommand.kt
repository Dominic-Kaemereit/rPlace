package de.d151l.place.plugin.command

import de.d151l.place.plugin.Place
import de.d151l.place.plugin.uuid.UUIDFetcher
import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.text.SimpleDateFormat
import java.util.*

/**
 * @created 16/04/2022 - 17:26
 * @project rPlace
 * @author  D151l
 */
class CheckBlockCommand(
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

        val blockHistory = this.place.blockHistoryManager.getBlockHistory(targetBlock)
        player.sendMessage(place.messagesConfig.blockInformation
            .replace("%prefix%", place.messagesConfig.prefix)
            .replace("%name%", UUIDFetcher.getNameByUuid(blockHistory.getPlayer())!!)
            .replace("%date%", SimpleDateFormat("HH:mm dd.MM.yyyy").format(Date(blockHistory.getTime())))
        )

        return true
    }
}