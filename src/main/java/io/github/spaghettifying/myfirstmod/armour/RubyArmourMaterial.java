package io.github.spaghettifying.myfirstmod.armour;

import io.github.spaghettifying.myfirstmod.initializers.ModItems;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.apache.commons.lang3.ArrayUtils;

public class RubyArmourMaterial implements ArmorMaterial {

    public static final RubyArmourMaterial INSTANCE = new RubyArmourMaterial();
    public static final int[] BASE_DURABILITY = new int[] { 13, 15, 16, 11 }; // Gets durability value based upon slot of armour, e.g. chestplate is slot 2, boots slot 4
    public static final int[] PROTECTION_VALUES = new int[] { 6, 18, 12, 6 }; // Gets protection value based upon slot of armour, e.g. helmet is slot 1, leggings slot 3

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
        return Ingredient.ofItems(ModItems.getRuby());
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
