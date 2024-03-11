package io.github.adambrown0.myfirstmod.block;

import io.github.adambrown0.myfirstmod.Myfirstmod;
import io.github.adambrown0.myfirstmod.item.AbstractItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public abstract class AbstractBlock extends Block {

    private final FabricBlockSettings fabricBlockSettings;
    private final FabricItemSettings fabricItemSettings;

    public AbstractBlock (String name, FabricBlockSettings fabricBlockSettings, FabricItemSettings fabricItemSettings, RegistryKey<ItemGroup> itemGroup) {
        super(fabricBlockSettings);
        this.fabricBlockSettings = fabricBlockSettings;
        this.fabricItemSettings = fabricItemSettings;

        register(name, this, fabricItemSettings, itemGroup);
    }

    /**
     * Wrapper for {@link AbstractBlock#register(String, Block, FabricItemSettings, RegistryKey<ItemGroup>)}
     * @param name The internal name for the block
     * @param fabricBlockSettings The settings defined for the block
     * @param fabricItemSettings The settings defined for the item of the block
     * @param itemGroup The group of items assigned to the block, also defines which creative mode tab it is displayed in
     */
    public static Block createBlock (String name, FabricBlockSettings fabricBlockSettings, FabricItemSettings fabricItemSettings, RegistryKey<ItemGroup> itemGroup) {
        return register(name, new Block(fabricBlockSettings), fabricItemSettings, itemGroup);
    }

    /**
     * Add the passed block to the internal block and item registry
     * @param name The internal name for the block
     * @param block The block to register
     * @param fabricItemSettings The settings defined for the item of the block
     * @param itemGroup The group of items assigned to the block, also defines which creative mode tab it is displayed in
     */
    public static Block register (String name, Block block, FabricItemSettings fabricItemSettings, RegistryKey<ItemGroup> itemGroup) {
        Registry.register(Registries.BLOCK, new Identifier(Myfirstmod.ModIdentifier, name), block);
        AbstractItem.register(name, new BlockItem(block, fabricItemSettings), itemGroup); // Adds to item registry
        return block;
    }

}
