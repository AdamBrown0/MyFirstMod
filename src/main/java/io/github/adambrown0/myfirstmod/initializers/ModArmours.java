package io.github.adambrown0.myfirstmod.initializers;

import io.github.adambrown0.myfirstmod.armour.*;
import io.github.adambrown0.myfirstmod.item.AbstractItem;
import lombok.Getter;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import software.bernie.geckolib.event.GeoRenderEvent;

public class ModArmours implements Initializer {

    @Getter private static ArmorItem rubyHelmet;
    @Getter private static ArmorItem rubyChestplate;
    @Getter private static ArmorItem rubyLeggings;
    @Getter private static ArmorItem rubyBoots;

    public void init() {
        rubyChestplate = AbstractArmour.createChestplate(RubyArmourMaterial.INSTANCE, new FabricItemSettings());
        rubyLeggings = AbstractArmour.createLeggings(RubyArmourMaterial.INSTANCE, new FabricItemSettings());
        rubyBoots = AbstractArmour.createBoots(RubyArmourMaterial.INSTANCE, new FabricItemSettings());
        rubyHelmet = AbstractArmour.createHelmet(RubyArmourMaterial.INSTANCE, new FabricItemSettings());
    }

}
