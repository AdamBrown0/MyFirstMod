package io.github.spaghettifying.myfirstmod.armour;

import io.github.spaghettifying.myfirstmod.item.AbstractItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.RegistryKey;

public class AbstractArmour extends ArmorItem {

    public AbstractArmour(String name, ArmorMaterial material, Type type, Settings settings, RegistryKey<ItemGroup> itemGroup) {
        super(material, type, settings);
        AbstractItem.register(name, this, itemGroup);
    }

    public static ArmorItem createHelmet(ArmorMaterial armorMaterial, Settings settings) {
        return createArmor(armorMaterial.getName() + "_helmet", armorMaterial, Type.HELMET, settings);
    }

    public static ArmorItem createChestplate(ArmorMaterial armorMaterial, Settings settings) {
        return createArmor(armorMaterial.getName() + "_chestplate", armorMaterial, Type.CHESTPLATE, settings);
    }

    public static ArmorItem createLeggings(ArmorMaterial armorMaterial, Settings settings) {
        return createArmor(armorMaterial.getName() + "_leggings", armorMaterial, Type.LEGGINGS, settings);
    }

    public static ArmorItem createBoots(ArmorMaterial armorMaterial, Settings settings) {
        return createArmor(armorMaterial.getName() + "_boots", armorMaterial, Type.BOOTS, settings);
    }

    public static ArmorItem createArmor(String name, ArmorMaterial material, Type type, Settings settings) {
        return AbstractItem.register(name, new ArmorItem(material, type, settings), ItemGroups.COMBAT);
    }

}
