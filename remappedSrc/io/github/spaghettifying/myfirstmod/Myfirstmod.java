package io.github.spaghettifying.myfirstmod;

import io.github.spaghettifying.myfirstmod.armour.Armours;
import io.github.spaghettifying.myfirstmod.block.Blocks;
import io.github.spaghettifying.myfirstmod.generation.WorldGenerationFeatures;
import io.github.spaghettifying.myfirstmod.item.Items;
import io.github.spaghettifying.myfirstmod.tool.Tools;
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