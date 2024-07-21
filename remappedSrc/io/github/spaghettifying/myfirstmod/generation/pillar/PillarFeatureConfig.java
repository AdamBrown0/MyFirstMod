package io.github.spaghettifying.myfirstmod.generation.pillar;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.gen.feature.FeatureConfig;

public record PillarFeatureConfig(int number, Identifier blockId) implements FeatureConfig {

    public static Codec<PillarFeatureConfig> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codecs.POSITIVE_INT.fieldOf("number").forGetter(PillarFeatureConfig::number),
                    Identifier.CODEC.fieldOf("blockId").forGetter(PillarFeatureConfig::blockId)
            ).apply(instance, PillarFeatureConfig::new)
    );

}
