package io.github.adambrown0.myfirstmod.item;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;

public class Ruby extends AbstractItem {
    public Ruby(String name, FabricItemSettings fabricItemSettings, RegistryKey<ItemGroup> itemGroup) {
        super(name, fabricItemSettings, itemGroup);
    }
}
