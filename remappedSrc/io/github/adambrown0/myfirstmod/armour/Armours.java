package io.github.adambrown0.myfirstmod.armour;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemGroups;

public class Armours {

    public static void init() {
        // Ruby
        new AbstractArmour("ruby_helmet", RubyArmourMaterial.INSTANCE, ArmorItem.Type.HELMET, new FabricItemSettings(), ItemGroups.COMBAT);
        new AbstractArmour("ruby_chestplate", RubyArmourMaterial.INSTANCE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings(), ItemGroups.COMBAT);
        new AbstractArmour("ruby_leggings", RubyArmourMaterial.INSTANCE, ArmorItem.Type.LEGGINGS, new FabricItemSettings(), ItemGroups.COMBAT);
        new AbstractArmour("ruby_boots", RubyArmourMaterial.INSTANCE, ArmorItem.Type.BOOTS, new FabricItemSettings(), ItemGroups.COMBAT);

    }

}
