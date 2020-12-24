package net.mcpandemic.core.foodhealing;

import org.bukkit.inventory.ItemStack;

public enum Food
{
    APPLE(4, "APPLE", 0),
    GOLDEN_APPLE(20, "GOLDEN_APPLE", 0),
    GOD_APPLE(20, "GOLDEN_APPLE", 1),
    BAKED_POTATO(5, "BAKED_POTATO", 0),
    POTATO(2, "POTATO", 0),
    POTATO_ITEM(2, "POTATO_ITEM", 0),
    POISONOUS_POTATO(2, "POISONOUS_POTATO", 0),
    BREAD(5, "BREAD", 0),
    FULL_CAKE(14, "CAKE_BLOCK", 0),
    CAKE(2, "CAKE", 0),
    CARROT(3, "CARROT", 3),
    GOLDEN_CARROT(6, "GOLDEN_CARROT", 3),
    COOKED_FISH(5, "COOKED_FISH", 0),
    RAW_FISH(2, "RAW_FISH", 0),
    COOKED_SALMON(6, "COOKED_FISH", 1),
    RAW_SALMON(2, "RAW_FISH", 1),
    CLOWNFISH(1, "COOKED_FISH", 2),
    PUFFERFISH(1, "COOKED_FISH", 3),
    COOKED_BEEF(8, "COOKED_BEEF", 0),
    COOKED_CHICKEN(6, "COOKED_CHICKEN", 0),
    COOKED_MUTTON(6, "COOKED_MUTTON", 0),
    COOKED_PORKCHOP(8, "COOKED_PORKCHOP", 0),
    COOKED_RABBIT(5, "COOKED_RABBIT", 0),
    RAW_BEEF(3, "RAW_BEEF", 0),
    RAW_CHICKEN(2, "RAW_CHICKEN", 0),
    RAW_MUTTON(2, "MUTTON", 0),
    RAW_PORKCHOP(3, "PORK", 0),
    RAW_RABBIT(5, "RABBIT", 0),
    COOKIE(2, "COOKIE", 0),
    MELON(2, "MELON_SLICE", 0),
    MUSHROOM_STEW(6, "MUSHROOM_SOUP", 0);

    short data;
    String mat;
    int heal;

    Food(final int heal, final String mat, final int n) {
        this.heal = heal;
        this.mat = mat;
        this.data = (short)n;
    }

    public static Food getFood(final ItemStack itemStack) {
        for (final Food food : values()) {
            if (food.mat.equals(itemStack.getType().toString()) && food.data == itemStack.getDurability()) {
                return food;
            }
        }
        return null;
    }

    public double getHeal() {
        return this.heal;
    }
}
