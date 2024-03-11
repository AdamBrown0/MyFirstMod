package io.github.adambrown0.myfirstmod.generation.pillar;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.jetbrains.annotations.NotNull;

public class PillarFeature extends Feature<PillarFeatureConfig> {

    public PillarFeature(Codec<PillarFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(@NotNull FeatureContext<PillarFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        PillarFeatureConfig featureConfig = context.getConfig();

        BlockState blockState = Registries.BLOCK.get(featureConfig.blockId()).getDefaultState();
        if (blockState == null)
            throw new IllegalStateException(featureConfig.blockId() + " could not be parsed due to a valid block identifier!");

        BlockPos testPos = new BlockPos(origin);
        for (int y = 0; y < world.getHeight(); y++) {
            testPos = testPos.up();
            if(world.getBlockState(testPos).isIn(BlockTags.DIRT) && world.getBlockState(testPos.up()).isOf(Blocks.AIR)) {
                for (int i = 0; i < featureConfig.number(); i++) {
                    world.setBlockState(testPos, blockState, 0x10);
                    testPos = testPos.up();

                    if (testPos.getY() >= world.getTopY()) break;
                }
                return true;
            }
        }

        return false;
    }

}
