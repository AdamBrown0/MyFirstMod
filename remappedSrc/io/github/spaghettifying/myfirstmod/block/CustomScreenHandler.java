package io.github.spaghettifying.myfirstmod.block;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.Nullable;

public class CustomScreenHandler extends ScreenHandler {

    protected CustomScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId) {
        super(ScreenHandlerType.CRAFTING, syncId);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

}
