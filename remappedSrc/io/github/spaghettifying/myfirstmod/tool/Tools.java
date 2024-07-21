package io.github.spaghettifying.myfirstmod.tool;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

public class Tools {

    public static void init() {
        // Ruby
        new AbstractSword("ruby_sword", RubyToolMaterial.INSTANCE, 230, -2.0f, new FabricItemSettings());
        new AbstractShovel("ruby_shovel", RubyToolMaterial.INSTANCE, 10, -3.4f, new FabricItemSettings());
        new AbstractPickaxe("ruby_pickaxe", RubyToolMaterial.INSTANCE, 12, -3.4f, new FabricItemSettings());
        new AbstractAxe("ruby_axe", RubyToolMaterial.INSTANCE, 500, -1.0f, new FabricItemSettings());
        new AbstractHoe("ruby_hoe", RubyToolMaterial.INSTANCE, 1000, -95.0f, new FabricItemSettings());
    }

}
