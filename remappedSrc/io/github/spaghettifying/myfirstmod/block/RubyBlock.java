package io.github.spaghettifying.myfirstmod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RubyBlock extends AbstractBlock {

//    public static String type = null;
    public RubyBlock(String name, FabricBlockSettings fabricBlockSettings, FabricItemSettings fabricItemSettings, RegistryKey<ItemGroup> itemGroup) {
        super(name, fabricBlockSettings, fabricItemSettings, itemGroup);
    }

//    @Nullable
//    @Override
//    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
//        return new SimpleNamedScreenHandlerFactory((syncId, playerInventory, player) -> {
//            switch (type) {
//                case "crafting" -> {
//                    return new CustomCraftingScreenHandler(syncId, playerInventory);
//                }
//                default -> { return new CustomScreenHandler(ScreenHandlerType.ANVIL, syncId); }
//            }
//
//        }, Text.empty());
//    }

//    @Override
//    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
//        if (!world.isClient) {
//            BlockEntity blockEntity = world.getBlockEntity(pos);
//            Direction side = hit.getSide();
//            if (side == Direction.NORTH) {
//                System.out.println("HIT NORTH");
//                type = "crafting";
//                player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
//            } else if (side == Direction.DOWN) {
//                System.out.println("DOWN");
//            }
//        }
//        return ActionResult.SUCCESS;
//    }
}
