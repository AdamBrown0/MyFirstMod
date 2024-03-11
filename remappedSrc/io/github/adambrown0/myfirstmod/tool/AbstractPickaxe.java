package io.github.adambrown0.myfirstmod.tool;

import io.github.adambrown0.myfirstmod.item.AbstractItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKey;

public class AbstractPickaxe extends PickaxeItem {

    public static final RegistryKey<ItemGroup> itemGroup = ItemGroups.TOOLS;

    public AbstractPickaxe(String name, ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
        AbstractItem.register(name, this, itemGroup);
    }

}
