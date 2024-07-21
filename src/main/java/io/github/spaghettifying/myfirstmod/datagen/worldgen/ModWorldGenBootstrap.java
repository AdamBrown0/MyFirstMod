package io.github.spaghettifying.myfirstmod.datagen.worldgen;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.world.gen.feature.*;
import org.jetbrains.annotations.NotNull;

public class ModWorldGenBootstrap {

    private ModWorldGenBootstrap() {
    }

    static void configuredFeatures(@NotNull Registerable<ConfiguredFeature<?, ?>> registry) {
        RegistryEntryLookup<PlacedFeature> placedFeatureLookup = registry.getRegistryLookup(RegistryKeys.PLACED_FEATURE);
    }

    private static ConfiguredFeature<?, ?> createOreConfiguredFeature(Block block, TagKey<Block> blockTags, int size, float discardOnAirChance) {
        return new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig(
                new TagMatchRuleTest(blockTags),
                block.getDefaultState(),
                size,
                discardOnAirChance
        ));
    }
}
