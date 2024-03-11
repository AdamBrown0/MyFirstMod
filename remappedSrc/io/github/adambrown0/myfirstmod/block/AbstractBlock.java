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

    public static void createBlock (String name, FabricBlockSettings fabricBlockSettings, FabricItemSettings fabricItemSettings, RegistryKey<ItemGroup> itemGroup) {
        register(name, new Block(fabricBlockSettings), fabricItemSettings, itemGroup);
    }

    private static void register (String name, Block block, FabricItemSettings fabricItemSettings, RegistryKey<ItemGroup> itemGroup) {
        Registry.register(Registries.BLOCK, new Identifier(Myfirstmod.ModIdentifier, name), block);
        AbstractItem.register(name, new BlockItem(block, fabricItemSettings), itemGroup);
    }

    //public abstract void onBlockBreakStart(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player);
}
