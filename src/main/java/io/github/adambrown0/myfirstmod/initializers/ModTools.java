package io.github.adambrown0.myfirstmod.initializers;

import io.github.adambrown0.myfirstmod.tool.*;
import lombok.Getter;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

public class ModTools implements Initializer {

    @Getter private static RubySword rubySword;
    @Getter private static RubyShovel rubyShovel;
    @Getter private static RubyPickaxe rubyPickaxe;
    @Getter private static RubyAxe rubyAxe;
    @Getter private static RubyHoe rubyHoe;

    public void init() {
        // Ruby
        rubySword = new RubySword("ruby_sword", RubyToolMaterial.INSTANCE, 230, -2.0f, new FabricItemSettings());
        rubyShovel = new RubyShovel("ruby_shovel", RubyToolMaterial.INSTANCE, 10, -3.4f, new FabricItemSettings());
        rubyPickaxe = new RubyPickaxe("ruby_pickaxe", RubyToolMaterial.INSTANCE, 12, -3.4f, new FabricItemSettings());
        rubyAxe = new RubyAxe("ruby_axe", RubyToolMaterial.INSTANCE, 500, -1.0f, new FabricItemSettings());
        rubyHoe = new RubyHoe("ruby_hoe", RubyToolMaterial.INSTANCE, 1000, -95.0f, new FabricItemSettings());
    }

}