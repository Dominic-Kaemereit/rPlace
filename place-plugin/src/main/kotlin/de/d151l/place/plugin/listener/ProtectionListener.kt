package de.d151l.place.plugin.listener

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.*
import org.bukkit.event.entity.EntitySpawnEvent
import org.bukkit.event.entity.ExplosionPrimeEvent
import org.bukkit.event.player.PlayerArmorStandManipulateEvent


/**
 * @created 15/04/2022 - 22:05
 * @project R-Place
 * @author  D151l
 */
class ProtectionListener : Listener {

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

    @EventHandler
    fun onBlockPhysics(event: BlockPhysicsEvent) {
        event.isCancelled = true
    }

    @EventHandler
    fun onBlockRedstone(event: BlockRedstoneEvent) {
        event.newCurrent = 0
    }

    @EventHandler
    fun onBlockGrow(event: BlockGrowEvent) {
        event.isCancelled = true
    }

    @EventHandler
    fun onLeavesDecayEvent(event: LeavesDecayEvent) {
        event.isCancelled = true
    }

    @EventHandler
    fun onExplosionPrime(event: BlockIgniteEvent) {
        event.isCancelled = true
    }
}