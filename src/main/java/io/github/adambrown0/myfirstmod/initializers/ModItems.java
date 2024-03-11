package io.github.adambrown0.myfirstmod.initializers;

import io.github.adambrown0.myfirstmod.item.AbstractItem;
import io.github.adambrown0.myfirstmod.item.QuestBook.QuestBookItem;
import lombok.Getter;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;


public class ModItems implements Initializer {

    @Getter
    private static Item ruby;

    @Getter
    private static QuestBookItem questBook;

    public void init() {
        ruby = AbstractItem.createItem("ruby", new FabricItemSettings(), ItemGroups.INGREDIENTS);
        questBook = new QuestBookItem();
    }

}
