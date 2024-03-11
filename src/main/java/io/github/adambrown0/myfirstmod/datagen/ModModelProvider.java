package io.github.adambrown0.myfirstmod.datagen;

import io.github.adambrown0.myfirstmod.initializers.ModArmours;
import io.github.adambrown0.myfirstmod.initializers.ModBlocks;
import io.github.adambrown0.myfirstmod.initializers.ModItems;
import io.github.adambrown0.myfirstmod.initializers.ModTools;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    /**
     * Generates the blockstate folder under generated.assets.myfirstmod
     * Usage: blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLOCK_HERE);
     * For extra functionality look at the other .register methods
     * @param blockStateModelGenerator
     */
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.getRubyBlock());
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.getRubyOre());
    }

    /**
     * Generates the models folder under generated.assets.myfirstmod
     * Usage: itemModelGenerator(ModItems.ITEM_HERE, Models.TYPE); //ModItems can be swapped, Models.TYPE can be swapped
     * @param itemModelGenerator
     */
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.getRuby(), Models.GENERATED);
        itemModelGenerator.register(ModTools.getRubySword(), Models.GENERATED);
        itemModelGenerator.register(ModTools.getRubyHoe(), Models.GENERATED);
        itemModelGenerator.register(ModTools.getRubyAxe(), Models.GENERATED);
        itemModelGenerator.register(ModTools.getRubyPickaxe(), Models.GENERATED);
        itemModelGenerator.register(ModTools.getRubyShovel(), Models.GENERATED);
        itemModelGenerator.registerArmor(ModArmours.getRubyHelmet());
        itemModelGenerator.registerArmor(ModArmours.getRubyChestplate());
        itemModelGenerator.registerArmor(ModArmours.getRubyLeggings());
        itemModelGenerator.registerArmor(ModArmours.getRubyBoots());
    }



}
