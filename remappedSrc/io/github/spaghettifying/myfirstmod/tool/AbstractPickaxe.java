package io.github.spaghettifying.myfirstmod.tool;

import io.github.spaghettifying.myfirstmod.item.AbstractItem;
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
