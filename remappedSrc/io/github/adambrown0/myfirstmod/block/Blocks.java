package io.github.adambrown0.myfirstmod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.item.ItemGroups;

public class Blocks {

    public static void init(){
        // Ruby
        new RubyOre("ruby_ore", FabricBlockSettings.create().strength(4.0f).requiresTool(), new FabricItemSettings(), ItemGroups.NATURAL);
        RubyBlock rubyBlock = new RubyBlock("ruby_block", FabricBlockSettings.create().strength(4.0f), new FabricItemSettings(), ItemGroups.BUILDING_BLOCKS);
    }

}
