package io.github.adambrown0.myfirstmod.datagen;

import io.github.adambrown0.myfirstmod.initializers.ModArmours;
import io.github.adambrown0.myfirstmod.initializers.ModBlocks;
import io.github.adambrown0.myfirstmod.initializers.ModItems;
import io.github.adambrown0.myfirstmod.initializers.ModTools;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {

    private static final List<ItemConvertible> RUBY_SMELTABLES = List.of(ModItems.getRuby(),
            ModBlocks.getRubyOre());

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    /**
     * Generates the JSON files for all recipes
     * Usage: offerSmelting(exporter, LIST_OF_SMELTABLES, RecipeCategory.CATEGORY, ModItems.ITEM_RECEIVED_FROM_SMELTING, EXPERIENCE_GAINED, COOKING_TIME, GROUP);
     * Usage: offerReversibleCompactingRecipes(exporter, RecipeCategory.CATEGORY, ModItems.UNCOMPACTED, RecipeCategory.CATEGORY, ModBlocks.COMPACTED_BLOCK);
     * Usage: ShapedRecipeJsonBuilder.create(RecipeCategory.CATEGORY, ITEM_TO_CRAFT, COUNT)
     *              .pattern("###")
     *              .pattern("#|#") // Make sure each pattern is same length
     *              .pattern(" | ")
     *              .input('#', ITEM)
     *              .input('|', Items.STICK)
     *              .criterion(UNLOCKED_FROM) // What to unlock recipe from
     *              .offerTo(exporter, new Identifier(getRecipeName(ITEM_TO_CRAFT)));
     * @param exporter Used to export recipes
     */
    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerSmelting(exporter, RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.getRuby(), 10.0f, 10, "ruby");
        offerBlasting(exporter, RUBY_SMELTABLES, RecipeCategory.MISC, ModItems.getRuby(), 10.0f, 5, "ruby");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.getRuby(), RecipeCategory.DECORATIONS, ModBlocks.getRubyBlock());

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModTools.getRubySword(), 1)
                .pattern("R")
                .pattern("R")
                .pattern("S")
                .input('R', ModItems.getRuby())
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.getRuby()), conditionsFromItem(ModItems.getRuby()))
                .offerTo(exporter, new Identifier(getRecipeName(ModTools.getRubySword())));
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModTools.getRubyShovel(), 1)
                .pattern("R")
                .pattern("S")
                .pattern("S")
                .input('R', ModItems.getRuby())
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.getRuby()), conditionsFromItem(ModItems.getRuby()))
                .offerTo(exporter, new Identifier(getRecipeName(ModTools.getRubyShovel())));
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModTools.getRubyPickaxe(), 1)
                .pattern("RRR")
                .pattern(" S ")
                .pattern(" S ")
                .input('R', ModItems.getRuby())
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.getRuby()), conditionsFromItem(ModItems.getRuby()))
                .offerTo(exporter, new Identifier(getRecipeName(ModTools.getRubyPickaxe())));
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModTools.getRubyHoe(), 1)
                .pattern("RR")
                .pattern("S ")
                .pattern("S ")
                .input('R', ModItems.getRuby())
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.getRuby()), conditionsFromItem(ModItems.getRuby()))
                .offerTo(exporter, new Identifier(getRecipeName(ModTools.getRubyHoe())));
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModTools.getRubyAxe(), 1)
                .pattern("RR")
                .pattern("SR")
                .pattern("S ")
                .input('R', ModItems.getRuby())
                .input('S', Items.STICK)
                .criterion(hasItem(ModItems.getRuby()), conditionsFromItem(ModItems.getRuby()))
                .offerTo(exporter, new Identifier(getRecipeName(ModTools.getRubyAxe())));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModArmours.getRubyHelmet(), 1)
                .pattern("RRR")
                .pattern("R R")
                .input('R', ModItems.getRuby())
                .criterion(hasItem(ModItems.getRuby()), conditionsFromItem(ModItems.getRuby()))
                .offerTo(exporter, new Identifier(getRecipeName(ModArmours.getRubyHelmet())));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModArmours.getRubyChestplate(), 1)
                .pattern("R R")
                .pattern("RRR")
                .pattern("RRR")
                .input('R', ModItems.getRuby())
                .criterion(hasItem(ModItems.getRuby()), conditionsFromItem(ModItems.getRuby()))
                .offerTo(exporter, new Identifier(getRecipeName(ModArmours.getRubyChestplate())));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModArmours.getRubyLeggings(), 1)
                .pattern("RRR")
                .pattern("R R")
                .pattern("R R")
                .input('R', ModItems.getRuby())
                .criterion(hasItem(ModItems.getRuby()), conditionsFromItem(ModItems.getRuby()))
                .offerTo(exporter, new Identifier(getRecipeName(ModArmours.getRubyLeggings())));
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModArmours.getRubyBoots(), 1)
                .pattern("R R")
                .pattern("R R")
                .input('R', ModItems.getRuby())
                .criterion(hasItem(ModItems.getRuby()), conditionsFromItem(ModItems.getRuby()))
                .offerTo(exporter, new Identifier(getRecipeName(ModArmours.getRubyBoots())));
    }

}
