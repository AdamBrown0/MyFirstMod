package io.github.adambrown0.myfirstmod.initializers;

import io.github.adambrown0.myfirstmod.Myfirstmod;
import io.github.adambrown0.myfirstmod.block.AbstractBlock;
import io.github.adambrown0.myfirstmod.block.ImbuementTable;
import io.github.adambrown0.myfirstmod.block.blockentity.ImbuingTableBlockEntity;
import lombok.Getter;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks implements Initializer {

    @Getter
    private static Block rubyBlock;

    @Getter
    private static Block rubyOre;

    @Getter
    private static Block imbuementTable;

    @Getter
    private static BlockEntityType<ImbuingTableBlockEntity> imbuementTableEntity;

    public void init() {
        rubyBlock = AbstractBlock.createBlock("ruby_block", FabricBlockSettings.create().strength(4.0f), new FabricItemSettings(), ItemGroups.BUILDING_BLOCKS);
        rubyOre = AbstractBlock.createBlock("ruby_ore", FabricBlockSettings.create().strength(4.0f).requiresTool(), new FabricItemSettings(), ItemGroups.NATURAL);
        imbuementTable = new ImbuementTable();
        imbuementTableEntity = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier(Myfirstmod.ModIdentifier, "imbuement_table_block_entity"),
                FabricBlockEntityTypeBuilder.create(ImbuingTableBlockEntity::new, ModBlocks.getImbuementTable()).build()
        );
    }

}
