package io.github.spaghettifying.myfirstmod.datagen;


import io.github.spaghettifying.myfirstmod.initializers.ModBlocks;
import io.github.spaghettifying.myfirstmod.initializers.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModLootTableProvider extends FabricBlockLootTableProvider {

    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    /**
     * Generates loot tables
     * Usage: addDrop(ModBlocks.BLOCK_HERE);
     * For extra functionality look at some stuff
     */
    @Override
    public void generate() {
        addDrop(ModBlocks.getRubyBlock());

        addDrop(ModBlocks.getRubyOre(), oreDrops(ModBlocks.getRubyOre(), ModItems.getRuby()));
    }

}
