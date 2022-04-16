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

    val prefix = "§8〣§arPlace §8» §7"

    val blockPlaceNotAllow = "%prefix%Diesen Block darftst du §cnicht§7 platzieren!"
    val blockAlreadyPlaced = "%prefix%Dieser Block wurde hier schon platziert!"
    val blockWahteSeconds = "%prefix%Du musst noch §c%cooldowen% §7Sekunden warten!"
    val blockWahteSecond = "%prefix%Du musst noch §c1 §7Sekunde warten!"
    val blockHasNoHistory = "%prefix%Dieser Block hat keine änderung!"
    val blockInformation = "%prefix%Dieser Block ist von §a%name% gestetzt worden. §8(§a%date%§8)"

    @Comment("This is for the scoreboard and not for player messages.")
    val seconds = "Sekunden"
    val second = "Sekunde"
    val redy = "Bereit"

    val itemBlockRemoverName = "§cBlock Entferner §8«"
    val itemBlockCheckerName = "§eBlock Untersuchen §8«"
}