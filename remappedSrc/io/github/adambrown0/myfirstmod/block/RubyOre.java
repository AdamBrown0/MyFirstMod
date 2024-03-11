package io.github.adambrown0.myfirstmod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;

public class RubyOre extends AbstractBlock {

    private final FabricBlockSettings fabricBlockSettings;
    private final FabricItemSettings fabricItemSettings;

    public RubyOre(String name, FabricBlockSettings fabricBlockSettings, FabricItemSettings fabricItemSettings, RegistryKey<ItemGroup> itemGroup) {
        super(name, fabricBlockSettings, fabricItemSettings, itemGroup);
        this.fabricBlockSettings = fabricBlockSettings;
        this.fabricItemSettings = fabricItemSettings;

        //createBlock(name, fabricBlockSettings, fabricItemSettings, itemGroup);
    }

//    @Override
//    public void onBlockBreakStart(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player){
//        ItemStack tool = player.getMainHandStack();
//        if (tool.getItem() instanceof PickaxeItem pickaxeItem) {
//            int harvestLevel = pickaxeItem.getMaterial().getMiningLevel();
//            if (harvestLevel > 2) return;
//        }
//        if (world.random.nextInt(10) == 0) {
//            dropStack(world, blockPos, new ItemStack(ModItems.ruby));
//        }
//    }

}
