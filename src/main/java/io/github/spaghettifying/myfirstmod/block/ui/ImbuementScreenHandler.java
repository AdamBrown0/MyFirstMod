package io.github.spaghettifying.myfirstmod.block.ui;

import io.github.spaghettifying.myfirstmod.initializers.ModItems;
import io.wispforest.owo.client.screens.ScreenUtils;
import io.wispforest.owo.client.screens.SlotGenerator;
import lombok.Getter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;

public class ImbuementScreenHandler extends ScreenHandler {

    @Getter
    private static final ScreenHandlerType<ImbuementScreenHandler> screenHandlerType = new ScreenHandlerType<>(ImbuementScreenHandler::new, FeatureFlags.VANILLA_FEATURES);
    private final ScreenHandlerContext context;
    private final Inventory inventory;

    public ImbuementScreenHandler(int syncId, PlayerInventory inventory) {
        this(syncId, inventory, ScreenHandlerContext.EMPTY);
    }

    public ImbuementScreenHandler(int syncId, PlayerInventory inventory, ScreenHandlerContext context) {
        super(screenHandlerType, syncId);
        this.inventory = new SimpleInventory(2) {
            public void markDirty() {
                super.markDirty();
                ImbuementScreenHandler.this.onContentChanged(this);
            }
        };
        this.context = context;

        SlotGenerator.begin(this::addSlot, 8, 84)
                .grid(new SimpleInventory(4), 0, 4, 1)
                .playerInventory(inventory);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
//        ItemStack newStack = ItemStack.EMPTY;
//        Slot slot = this.slots.get(invSlot);
//        if (slot != null && slot.hasStack()) {
//            ItemStack originalStack = slot.getStack();
//            newStack = originalStack.copy();
//            if (invSlot == 0) {
//                if (!this.insertItem(originalStack, 2, 38, true)) {
//                    return ItemStack.EMPTY;
//                }
//            } else if (invSlot == 1) {
//                if (!this.insertItem(originalStack, 2, 38, true)) {
//                    return ItemStack.EMPTY;
//                }
//            } else if (originalStack.isOf(ModItems.getRuby())) {
//                if (!this.insertItem(originalStack, 1, 2, true)) {
//                    return ItemStack.EMPTY;
//                }
//            } else {
//                if ((this.slots.get(0)).hasStack() || !((Slot)this.slots.get(0)).canInsert(originalStack)) {
//                    return ItemStack.EMPTY;
//                }
//
//                ItemStack itemStack3 = originalStack.copyWithCount(1);
//                originalStack.decrement(1);
//                (this.slots.get(0)).setStack(itemStack3);
//            }
//
//            if (originalStack.isEmpty()) {
//                slot.setStack(ItemStack.EMPTY);
//            } else {
//                slot.markDirty();
//            }
//
//            if (originalStack.getCount() == newStack.getCount()) {
//                return ItemStack.EMPTY;
//            }
//
//            slot.onTakeItem(player, originalStack);
//        }
//
//        return newStack;
        return ScreenUtils.handleSlotTransfer(this, invSlot, 2);
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.context.run((world, pos) -> {
            this.dropInventory(player, this.inventory);
        });
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
