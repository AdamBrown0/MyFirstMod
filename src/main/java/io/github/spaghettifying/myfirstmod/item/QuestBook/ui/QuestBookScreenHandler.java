package io.github.spaghettifying.myfirstmod.item.QuestBook.ui;

import io.github.spaghettifying.myfirstmod.block.ui.ImbuementScreenHandler;
import lombok.Getter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.Nullable;

public class QuestBookScreenHandler extends ScreenHandler {

    @Getter
    private static final ScreenHandlerType<QuestBookScreenHandler> screenHandlerType = new ScreenHandlerType<>(QuestBookScreenHandler::new, FeatureFlags.VANILLA_FEATURES);
//    private final ScreenHandlerContext context;

    public QuestBookScreenHandler(int syncId, PlayerInventory inventory) {
//        this(syncId, inventory, ScreenHandlerContext.EMPTY);
        super(screenHandlerType, syncId);
    }


    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return false;
    }

}
