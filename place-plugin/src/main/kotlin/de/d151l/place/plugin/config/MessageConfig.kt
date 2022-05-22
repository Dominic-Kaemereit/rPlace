package de.d151l.place.plugin.config

import com.twodevsstudio.simplejsonconfig.api.Config
import com.twodevsstudio.simplejsonconfig.interfaces.Comment
import com.twodevsstudio.simplejsonconfig.interfaces.Configuration

/**
 * @created 16/04/2022 - 13:04
 * @project R-Place
 * @author  D151l
 */
@Configuration("messages")
class MessageConfig: Config() {

    val prefix = "§8〣§ar/Place §8» §7"

    val blockPlaceNotAllow = "%prefix%You may §cnot §7place this block!"
    val blockAlreadyPlaced = "%prefix%This block has already been placed here!"
    val blockWaiteSeconds = "%prefix%You still have to wait §c%cooldowen% §7seconds!"
    val blockWaiteSecond = "%prefix%You still have to wait §c%cooldowen% §7second!"
    val blockHasNoHistory = "%prefix%This block has no change!"
    val blockInformation = "%prefix%This block has been set by §a%name%§7. §8(§a%date%§8)"
    val blockRemoveSuccessfully = "%prefix%You have removed this block!"
    val oldPluginWarning = "%prefix%You are using an old version of this plugin. You can download a newer version here: %link%"

    @Comment("This is for the scoreboard and not for player messages.")
    val seconds = "seconds"
    val second = "second"
    val ready = "Ready"

    val itemBlockRemoverName = "§cBlock remover §8«"
    val itemBlockCheckerName = "§eBlock Investigate §8«"
}