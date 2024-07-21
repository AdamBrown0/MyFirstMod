package io.github.spaghettifying.myfirstmod.tool;

import io.github.spaghettifying.myfirstmod.item.AbstractItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.RegistryKey;

public class AbstractSword extends SwordItem {

    public static final RegistryKey<ItemGroup> itemGroup = ItemGroups.COMBAT;

    public AbstractSword(String name, ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        AbstractItem.register(name, this, itemGroup);
    }

}
