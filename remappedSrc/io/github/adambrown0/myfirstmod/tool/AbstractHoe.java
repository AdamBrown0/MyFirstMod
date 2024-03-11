package io.github.adambrown0.myfirstmod.tool;

import io.github.adambrown0.myfirstmod.item.AbstractItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKey;

public class AbstractHoe extends HoeItem {

    public static final RegistryKey<ItemGroup> itemGroup = ItemGroups.TOOLS;

    public AbstractHoe(String name, ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
        AbstractItem.register(name, this, itemGroup);
    }

}
