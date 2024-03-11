package io.github.adambrown0.myfirstmod.item;

import io.github.adambrown0.myfirstmod.Myfirstmod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.util.Optional;

public abstract class AbstractItem extends Item {

    private final FabricItemSettings fabricItemSettings;

    public AbstractItem(String name, FabricItemSettings fabricItemSettings, RegistryKey<ItemGroup> itemGroup) {
        super(fabricItemSettings);
        this.fabricItemSettings = fabricItemSettings;

        register(name, this, itemGroup);
    }

    public static void createItem(String name, FabricItemSettings fabricItemSettings, RegistryKey<ItemGroup> itemGroup) {
        register(name, new Item(fabricItemSettings), itemGroup);
    }

    public static void register(String name, Item item, RegistryKey<ItemGroup> itemGroup) {
        Registry.register(Registries.ITEM, new Identifier(Myfirstmod.ModIdentifier, name), item);
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register(content -> content.add(item));
    }

}
