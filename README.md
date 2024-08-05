<p align="center">
  <img src="./img/rplace_title.png">
</p>
<h2 align="center">r/Place</h2>
<h4 align="center">A funny game for your server/network</h4>


<h4 align="center">

  [BStats](https://bstats.org/plugin/bukkit/rPlace/14956) | [Discord](https://discord.gg/XNf9RWe7HY) | [SpigotMC](https://www.spigotmc.org/resources/r-place.102155/) | [Download](https://github.com/D151l/rPlace/releases/download/untagged-3ce2cdb1a39976ccc978/rplace.jar)
  
</h4>

<br>
<hr>
<br>

 <div align="left">
        <p align="center">
  <img src="./img/rplace_setup.png">
          </p>

- Stop current server.
- Upload the plugin in the plugins folder
- Start your server
- Have fun!
  </div>
<br>
<hr>
<br>
      
  <div align="left">
        <p align="center">
  <img src="./img/rplace_features.png">
</p>

- Geyser compatibility
- Scoreboard full configurable
- All messages full configurable
- 1.13 - 1.19.4 Server support
- Database support (SQLite, MySQL, MongoDB)
- Custom place time with permission ("rplace-permission-time-\<seconds>")
  </div>

<br>
<hr>
<br>

 <div align="left">
        <p align="center">
  <img src="./img/rplace_config.png">
  </p>
  
  <details>
  <summary>Config</summary>
  
  This is a default config.
  
  ```json
{
  "blockCooldown": 21,
  "worldBorderSize": 313.0, //The worldBorderSize must be an odd number. (1, 7, 15, 31, 101, 1001...)
  "scoreboardEnabled": true,
  "scoreboardTitle": "§aServer §8| §7r/Place",
  "scoreboard": [
    "§4§8§l§m---------------",
    " §8§l» §7Progress§3",
    "    §8» §a %progress%%",
    "",
    " §8§l» §7Countdown§3",
    "   §8» §a %cooldown%",
    "",
    " §8§l» §7Your blocks§3",
    "    §8» §a %blocks%",
    "",
    " §8§l» §7Players§3",
    "    §8»§a %onlinePlayers%§7/%maxPlayers%",
    "",
    " §8§l» §7Your ranking§3",
    "    §8»§a #%ranking%"
  ],
  "enableOldPluginWarning": true,
  "blockHasNoHistorySound": "ENTITY_STRIDER_EAT", //List of sounds: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html
  "itemRemoverMaterial": "STICK", //List of materials: https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html
  "itemCheckerMaterial": "END_ROD"
}
  ```
  
</details>
  <br>
    <details>
  <summary>Messages</summary>
  
  This is a default messages config.
  
  ```json
{
  "prefix": "§8〣§ar/Place §8» §7",
  "blockPlaceNotAllow": "%prefix%You may §cnot §7place this block!",
  "blockAlreadyPlaced": "%prefix%This block has already been placed here!",
  "blockWaiteSeconds": "%prefix%You still have to wait §c%cooldowen% §7seconds!",
  "blockWaiteSecond": "%prefix%You still have to wait §c%cooldowen% §7second!",
  "blockHasNoHistory": "%prefix%This block has no change!",
  "blockInformation": "%prefix%This block has been set by §a%name%§7. §8(§a%date%§8)",
  "blockRemoveSuccessfully": "%prefix%You have removed this block!",
  "oldPluginWarning": "%prefix%You are using an old version of this plugin. You can download a newer version here: %link%",
  "rPlaceCommandHelp": "%prefix%§c/rplace reload",
  "rPlaceCommandReloadTry": "%prefix%§cTrying to reload all configs...",
  "rPlaceCommandReloadSuccessfully": "%prefix%§7All configs wars §asuccessfully §7reloaded.",
  "seconds": "seconds", //This is for the scoreboard and not for player messages.
  "second": "second",
  "ready": "Ready",
  "itemBlockRemoverName": "§cBlock remover §8«",
  "itemBlockCheckerName": "§eBlock Investigate §8«"
}
  ```
  
</details>
  </div>
<br>
<hr>
<br>

  <div align="center">
    <p align="center">
  <img src="./img/rplace_permissions.png">
</p>

| Permissions                |                    Features                    |
|----------------------------|:----------------------------------------------:|
| place.item.remover         |     Add a block remover to the inventory.      |
| place.item.checker         |     Add a block remover in the inventory.      |
| place.command.remove.block | Permission to execute the /removeBlock command |
| place.command.check.block  | Permission to execute the /checkBlock command  |
| place.command.rplace       |   Permission to execute the /rplace command    |
  
  </div>
