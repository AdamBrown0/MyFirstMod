package io.github.spaghettifying.myfirstmod.item.QuestBook;

import io.github.spaghettifying.myfirstmod.item.AbstractItem;
import io.github.spaghettifying.myfirstmod.item.QuestBook.ui.QuestBookScreen;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class QuestBookItem extends AbstractItem {

    public QuestBookItem() {
        super("quest_book", new OwoItemSettings().tab(3)
                .maxCount(1)
                .stackGenerator(OwoItemGroup.DEFAULT_STACK_GENERATOR.andThen((item, stacks) -> {
                    final var stack = new ItemStack(item);
                    stack.setCustomName(Text.literal("Quest Book")
                            .styled(style -> style.withBold(true))
                            .styled(style -> style.withUnderline(true))
                            .styled(style -> style.withColor(Formatting.AQUA)));
                    stacks.add(stack);
                })), ItemGroups.OPERATOR);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!player.isSneaking()) {
            if (world.isClient) MinecraftClient.getInstance().setScreen(new QuestBookScreen());
        }

        return TypedActionResult.success(player.getStackInHand(hand));
    }

//    @Nullable
//    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
//        BlockEntity blockEntity = world.getBlockEntity(pos);
//        if (blockEntity == null) {
//            return new SimpleNamedScreenHandlerFactory(())
//        }
//
//    }

}
