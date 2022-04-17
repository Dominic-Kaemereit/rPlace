package de.d151l.place.plugin.util

import com.destroystokyo.paper.Namespaced
import com.google.common.collect.Multimap
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.util.*


/**
 * @created 16/04/2022 - 16:32
 * @project rPlace
 * @author  D151l
 */
class ItemBuilder {
    private val itemStack: ItemStack
    private val itemMeta: ItemMeta

    constructor(material: Material) {
        itemStack = ItemStack(material)
        itemMeta = itemStack.itemMeta
    }

    constructor(itemStack: ItemStack) {
        this.itemStack = itemStack
        itemMeta = this.itemStack.itemMeta
    }

    fun setDisplayName(name: Component?): ItemBuilder {
        itemMeta.displayName(name)
        return this
    }

    fun setNoName(): ItemBuilder {
        itemMeta.displayName(Component.empty())
        return this
    }

    fun setDescription(lore: MutableList<Component> = mutableListOf()): ItemBuilder {
        itemMeta.lore(lore)
        return this
    }

    @Deprecated("")
    fun setDisplayName(name: String?): ItemBuilder {
        itemMeta.setDisplayName(name)
        return this
    }

    @JvmName("setDescription1")
    @Deprecated("")
    fun setDescription(lore: MutableList<String> = mutableListOf()): ItemBuilder {
        itemMeta.lore = lore
        return this
    }

    fun setAmount(amount: Int): ItemBuilder {
        itemStack.setAmount(amount)
        return this
    }

    fun addEnchant(enchantment: Enchantment, level: Int, visible: Boolean): ItemBuilder {
        itemMeta.addEnchant(enchantment, level, visible)
        return this
    }

    fun setUnbreakable(): ItemBuilder {
        itemMeta.isUnbreakable = true
        return this
    }

    fun hideItemAttributes(): ItemBuilder {
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
        return this
    }

    fun hideItemEnchants(): ItemBuilder {
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
        return this
    }

    fun setCustomModelData(data: Int): ItemBuilder {
        itemMeta.setCustomModelData(data)
        return this
    }

    fun setLocalizedName(name: String?): ItemBuilder {
        itemMeta.setLocalizedName(name)
        return this
    }

    fun setAttributeModifiers(multimap: Multimap<Attribute, AttributeModifier>): ItemBuilder {
        itemMeta.attributeModifiers = multimap
        return this
    }

    fun addAttributeModifier(attribute: Attribute, attributeModifier: AttributeModifier): ItemBuilder {
        itemMeta.addAttributeModifier(attribute, attributeModifier)
        return this
    }

    fun removeAttributeModifier(attribute: Attribute, attributeModifier: AttributeModifier): ItemBuilder {
        itemMeta.removeAttributeModifier(attribute, attributeModifier)
        return this
    }

    fun setDestroyableKeys(collection: Collection<Namespaced?>?): ItemBuilder {
        itemMeta.setDestroyableKeys(collection!!)
        return this
    }

    fun setPlaceableKeys(collection: Collection<Namespaced?>?): ItemBuilder {
        itemMeta.setPlaceableKeys(collection!!)
        return this
    }

    fun addItemFlags(vararg itemFlags: ItemFlag): ItemBuilder {
        itemMeta.addItemFlags(*itemFlags)
        return this
    }

    fun build(): ItemStack {
        itemStack.setItemMeta(itemMeta)
        return itemStack
    }

    fun getItemStack(): ItemStack {
        return itemStack
    }

    fun getItemMeta(): ItemMeta {
        return itemMeta
    }
}
