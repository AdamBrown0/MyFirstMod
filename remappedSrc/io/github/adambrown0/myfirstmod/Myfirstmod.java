package io.github.adambrown0.myfirstmod;

import io.github.adambrown0.myfirstmod.armour.Armours;
import io.github.adambrown0.myfirstmod.block.Blocks;
import io.github.adambrown0.myfirstmod.generation.WorldGenerationFeatures;
import io.github.adambrown0.myfirstmod.item.Items;
import io.github.adambrown0.myfirstmod.tool.Tools;
import net.fabricmc.api.ModInitializer;


public class Myfirstmod implements ModInitializer {

    public static final String ModIdentifier = "myfirstmod";
    
    @Override
    public void onInitialize() {
        System.err.println("initializing");

        // Blocks
        Blocks.init();

        // Items
        Items.init();

        // Tools
        Tools.init();

        // Armours
        Armours.init();

        // Generation
        WorldGenerationFeatures.init();

        System.out.println("we yes");
    }

}