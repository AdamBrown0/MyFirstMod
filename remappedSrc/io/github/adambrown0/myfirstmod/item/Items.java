package io.github.adambrown0.myfirstmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroups;

public class Items {

    public static Ruby RUBY;

    public static void init(){
        // Ruby
        RUBY = new Ruby("ruby", new FabricItemSettings(), ItemGroups.INGREDIENTS);
    }

}
