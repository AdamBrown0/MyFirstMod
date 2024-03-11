package io.github.adambrown0.myfirstmod.block;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.Optional;

public class CustomCraftingScreenHandler extends ScreenHandler {
    private final CraftingInventory craftingInput = new CraftingInventory(this, 3, 3);
    private final CraftingResultInventory craftingResult = new CraftingResultInventory();
    private final PlayerEntity player;

    public CustomCraftingScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(null, syncId);
        this.player = playerInventory.player;
        this.addSlot(new CraftingResultSlot(playerInventory.player, this.craftingInput, this.craftingResult, 0, 124, 35));

        // Add slots for crafting input (3x3 grid)
        for (int m = 0; m < 3; ++m) {
            for (int n = 0; n < 3; ++n) {
                this.addSlot(new Slot(this.craftingInput, n + m * 3, 30 + n * 18, 17 + m * 18));
            }
        }

        // Add slots for player inventory
        for (int m = 0; m < 3; ++m) {
            for (int n = 0; n < 9; ++n) {
                this.addSlot(new Slot(playerInventory, n + m * 9 + 9, 8 + n * 18, 84 + m * 18));
            }
        }

        for (int m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        if (inventory == this.craftingInput) {
            updateResult();
        }
        super.onContentChanged(inventory);
    }

    public void updateResult() {
        ServerWorld serverWorld = (ServerWorld) player.method_48926();
        Optional<CraftingRecipe> optionalRecipe = serverWorld.getServer().getRecipeManager().getFirstMatch(RecipeType.CRAFTING, craftingInput, serverWorld);

        if (optionalRecipe.isPresent()) {
            CraftingRecipe recipe = optionalRecipe.get();
            if (craftingResult.shouldCraftRecipe(serverWorld, (ServerPlayerEntity) player, recipe)) {
                craftingResult.setLastRecipe(recipe);
                craftingResult.setStack(0, recipe.craft(craftingInput, DynamicRegistryManager.EMPTY));
            } else {
                craftingResult.setStack(0, ItemStack.EMPTY);
            }
        } else {
            craftingResult.setStack(0, ItemStack.EMPTY);
        }

        this.sendContentUpdates();
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
