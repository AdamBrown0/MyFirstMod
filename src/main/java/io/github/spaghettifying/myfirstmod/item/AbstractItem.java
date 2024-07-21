package io.github.spaghettifying.myfirstmod.item;

import io.github.spaghettifying.myfirstmod.Myfirstmod;
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

    /**
     * Wrapper for {@link AbstractItem#register(String, Item, RegistryKey<ItemGroup>)}
     * @param name The internal name for the item
     * @param fabricItemSettings The settings defined for the item
     * @param itemGroup The group of items assigned to the block, also defines which creative mode tab it is displayed in
     */
    public static Item createItem(String name, FabricItemSettings fabricItemSettings, RegistryKey<ItemGroup> itemGroup) {
        return register(name, new Item(fabricItemSettings), itemGroup);
    }

    /**
     * Adds the passed item to the internal item registry
     * @param name The internal name for the item
     * @param item THe item being added to the internal registry
     * @param itemGroup The group of items assigned to the block, also defines which creative mode tab it is displayed in
     */
    public static <T extends Item> T register(String name, T item, RegistryKey<ItemGroup> itemGroup) {
        Registry.register(Registries.ITEM, new Identifier(Myfirstmod.ModIdentifier, name), item);
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register(content -> content.add(item));

        return item;
    }

}
