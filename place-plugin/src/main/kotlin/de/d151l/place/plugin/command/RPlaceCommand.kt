package de.d151l.place.plugin.command

import com.twodevsstudio.simplejsonconfig.SimpleJSONConfig
import com.twodevsstudio.simplejsonconfig.api.Config
import de.d151l.place.plugin.Place
import de.d151l.place.plugin.config.BlockingItemsConfig
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

/**
 * @created 17/01/2023 - 22:46
 * @project rPlace
 * @author  D151l
 */
class RPlaceCommand(
    private val place: Place
): CommandExecutor, TabCompleter {

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        string: String,
        args: Array<out String>
    ): Boolean {

        if (args.isEmpty()) {
            sender.sendMessage(place.messagesConfig.rPlaceCommandHelp.replace("%prefix%", place.messagesConfig.prefix))
            return false
        }

        when(args[0]) {
            "reload" -> {
                sender.sendMessage(place.messagesConfig.rPlaceCommandReloadTry.replace("%prefix%", place.messagesConfig.prefix))
                Config.reloadAll()
                sender.sendMessage(place.messagesConfig.rPlaceCommandReloadSuccessfully.replace("%prefix%", place.messagesConfig.prefix))
                return true
            }
        }
        sender.sendMessage(place.messagesConfig.rPlaceCommandHelp.replace("%prefix%", place.messagesConfig.prefix))
        return false
    }

    override fun onTabComplete(
        commandSender: CommandSender,
        command: Command,
        string: String,
        strings: Array<out String>
    ): MutableList<String> {
        return mutableListOf("reload")
    }
}