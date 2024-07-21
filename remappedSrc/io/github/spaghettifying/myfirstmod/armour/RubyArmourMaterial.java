package io.github.spaghettifying.myfirstmod.armour;

import io.github.spaghettifying.myfirstmod.item.Items;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class RubyArmourMaterial implements ArmorMaterial {

    public static final RubyArmourMaterial INSTANCE = new RubyArmourMaterial();
    public static final int[] BASE_DURABILITY = new int[] { 13, 15, 16, 11 };
    public static final int[] PROTECTION_VALUES = new int[] { 6, 12, 18, 6 };

    public static final ArmorItem.Type[] armorType = new ArmorItem.Type[] { ArmorItem.Type.HELMET, ArmorItem.Type.CHESTPLATE, ArmorItem.Type.LEGGINGS, ArmorItem.Type.BOOTS };

    @Override
    public int getDurability(ArmorItem.Type type) {
        return BASE_DURABILITY[ArrayUtils.indexOf(armorType, type)];
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return PROTECTION_VALUES[ArrayUtils.indexOf(armorType, type)];
    }

    @Override
    public int getEnchantability() {
        return 100;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.RUBY);
    }

    @Override
    public String getName() {
        return "ruby";
    }

    @Override
    public float getToughness() {
        return 10;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.9F;
    }

}
