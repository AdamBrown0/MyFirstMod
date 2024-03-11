package io.github.adambrown0.myfirstmod.block.blockentity;

import io.github.adambrown0.myfirstmod.block.ui.ImbuementScreenHandler;
import io.github.adambrown0.myfirstmod.initializers.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.util.Nameable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ImbuingTableBlockEntity extends BlockEntity implements Nameable {

    public int ticks;
    public float nextPageAngle;
    public float pageAngle;
    public float flipRandom;
    public float flipTurn;
    public float nextPageTurningSpeed;
    public float pageTurningSpeed;
    public float bookRotation;
    public float lastBookRotation;
    public float targetBookRotation;
    private static final Random RANDOM = Random.create();
    private Text customName;

    public ImbuingTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.getImbuementTableEntity(), pos, state);
    }

//    @Override
//    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
//        return new ImbuementScreenHandler(syncId, playerInventory, ScreenHandlerContext.create(world, player.getBlockPos()));
//    }

    @Override
    public Text getName() {
        return (Text)(this.customName != null ? this.customName : Text.translatable("container.imbue"));
    }

    public void setCustomName(@Nullable Text customName) {
        this.customName = customName;
    }

    @Nullable
    public Text getCustomName() {
        return this.customName;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Imbuement Table");
    }

    public static void tick(@NotNull World world, @NotNull BlockPos pos, BlockState state, @NotNull ImbuingTableBlockEntity blockEntity) {
        blockEntity.pageTurningSpeed = blockEntity.nextPageTurningSpeed;
        blockEntity.lastBookRotation = blockEntity.bookRotation;
        PlayerEntity playerEntity = world.getClosestPlayer((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, 3.0, false);
        if (playerEntity != null) {
            double d = playerEntity.getX() - ((double)pos.getX() + 0.5);
            double e = playerEntity.getZ() - ((double)pos.getZ() + 0.5);
            blockEntity.targetBookRotation = (float) MathHelper.atan2(e, d);
            blockEntity.nextPageTurningSpeed += 0.1F;
            if (blockEntity.nextPageTurningSpeed < 0.5F || RANDOM.nextInt(40) == 0) {
                float f = blockEntity.flipRandom;

                do {
                    blockEntity.flipRandom += (float)(RANDOM.nextInt(4) - RANDOM.nextInt(4));
                } while(f == blockEntity.flipRandom);
            }
        } else {
            blockEntity.targetBookRotation += 0.02F;
            blockEntity.nextPageTurningSpeed -= 0.1F;
        }

        while(blockEntity.bookRotation >= 3.1415927F) {
            blockEntity.bookRotation -= 6.2831855F;
        }

        while(blockEntity.bookRotation < -3.1415927F) {
            blockEntity.bookRotation += 6.2831855F;
        }

        while(blockEntity.targetBookRotation >= 3.1415927F) {
            blockEntity.targetBookRotation -= 6.2831855F;
        }

        while(blockEntity.targetBookRotation < -3.1415927F) {
            blockEntity.targetBookRotation += 6.2831855F;
        }

        float g;
        for(g = blockEntity.targetBookRotation - blockEntity.bookRotation; g >= 3.1415927F; g -= 6.2831855F) {
        }

        while(g < -3.1415927F) {
            g += 6.2831855F;
        }

        blockEntity.bookRotation += g * 0.4F;
        blockEntity.nextPageTurningSpeed = MathHelper.clamp(blockEntity.nextPageTurningSpeed, 0.0F, 1.0F);
        ++blockEntity.ticks;
        blockEntity.pageAngle = blockEntity.nextPageAngle;
        float h = (blockEntity.flipRandom - blockEntity.nextPageAngle) * 0.4F;
        float i = 0.2F;
        h = MathHelper.clamp(h, -0.2F, 0.2F);
        blockEntity.flipTurn += (h - blockEntity.flipTurn) * 0.9F;
        blockEntity.nextPageAngle += blockEntity.flipTurn;
    }

}
