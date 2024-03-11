package io.github.adambrown0.myfirstmod.initializers;

import io.github.adambrown0.myfirstmod.Myfirstmod;
import lombok.Getter;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;

public class ModFeatures implements Initializer {

    @Getter
    private static RegistryKey<ConfiguredFeature<?, ?>> rubyOreCF;
    @Getter
    private static RegistryKey<PlacedFeature> rubyOrePF;

    public void init() {
        rubyOreCF = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Myfirstmod.ModIdentifier, "my_ruby_ore"));
        rubyOrePF = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(Myfirstmod.ModIdentifier, "my_ruby_ore"));
    }

}
