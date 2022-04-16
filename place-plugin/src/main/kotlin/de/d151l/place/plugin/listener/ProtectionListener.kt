package de.d151l.place.plugin.listener

import de.d151l.place.plugin.Place
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockExplodeEvent
import org.bukkit.event.block.BlockPhysicsEvent
import org.bukkit.event.entity.EntitySpawnEvent
import org.bukkit.event.entity.ExplosionPrimeEvent
import org.bukkit.event.player.PlayerArmorStandManipulateEvent


/**
 * @created 15/04/2022 - 22:05
 * @project R-Place
 * @author  D151l
 */
class ProtectionListener(
    private val place: Place
): Listener {

    @EventHandler
    fun onArmorStand(event: PlayerArmorStandManipulateEvent) {
        event.isCancelled = true
    }

    @EventHandler
    fun onPhysics(event: BlockPhysicsEvent) {
        event.isCancelled = true
    }

    @EventHandler
    fun onExplosionPrime(event: ExplosionPrimeEvent) {
        event.isCancelled = true
    }

    @EventHandler
    fun onBlockExplode(event: BlockExplodeEvent) {
        event.isCancelled = true
    }

    @EventHandler
    fun onEntitySpawn(event: EntitySpawnEvent) {
        event.isCancelled = true
    }
}