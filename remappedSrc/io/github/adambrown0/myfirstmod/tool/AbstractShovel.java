package io.github.adambrown0.myfirstmod.tool;

import io.github.adambrown0.myfirstmod.item.AbstractItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKey;

public class AbstractShovel extends ShovelItem {

    public static final RegistryKey<ItemGroup> itemGroup = ItemGroups.TOOLS;

    public AbstractShovel(String name, ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
        AbstractItem.register(name, this, itemGroup);
    }

}
