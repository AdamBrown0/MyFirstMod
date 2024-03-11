package io.github.adambrown0.myfirstmod.armour;

import io.github.adambrown0.myfirstmod.item.AbstractItem;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;

public class AbstractArmour extends ArmorItem {

    public AbstractArmour(String name, ArmorMaterial material, Type type, Settings settings, RegistryKey<ItemGroup> itemGroup) {
        super(material, type, settings);
        AbstractItem.register(name, this, itemGroup);
    }

}
