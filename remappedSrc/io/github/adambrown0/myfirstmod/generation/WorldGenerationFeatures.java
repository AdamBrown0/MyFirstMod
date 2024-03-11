package io.github.adambrown0.myfirstmod.generation;


import io.github.adambrown0.myfirstmod.Myfirstmod;
import io.github.adambrown0.myfirstmod.generation.pillar.PillarFeature;
import io.github.adambrown0.myfirstmod.generation.pillar.PillarFeatureConfig;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class WorldGenerationFeatures {

    public static void init() {
        // Ruby
        //Registry.register(Registries.FEATURE, new Identifier(Myfirstmod.ModIdentifier, "ruby_feature"), new PillarFeature(PillarFeatureConfig.CODEC));

    }

    private static class FeatureInitializer<F extends Feature<C>, C extends FeatureConfig> {

        private final String name;
        private final F feature;
        private final C config;

        public FeatureInitializer(String name, F feature, C config) {
            this.name = name;
            this.feature = feature;
            this.config = config;
        }

        public void init(Object ... args) {
            Identifier identifier = new Identifier(Myfirstmod.ModIdentifier, name);
            ConfiguredFeature<C, F> configuredFeature = new ConfiguredFeature<>(feature, config);

            Registry.register(Registries.FEATURE, identifier, feature);
        }

    }

}
