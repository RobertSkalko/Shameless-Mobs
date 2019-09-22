package com.robertx22.shameless_mobs;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import org.apache.commons.lang3.tuple.Pair;

public class Config {

    public static final String NAME = "config";
    public static final ForgeConfigSpec spec;
    public static final Config INSTANCE;

    static {
        final Pair<Config, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Config::new);
        spec = specPair.getRight();
        INSTANCE = specPair.getLeft();

    }

    public BooleanValue FIRE_RESISTANT_MOBS;
    public BooleanValue FALL_DAMAGE_RESISTANT_MOBS;
    public BooleanValue KNOCKBACK_RESISTANT_MOBS;

    public BooleanValue ANTI_MOB_FARMS;
    public ForgeConfigSpec.IntValue ANTI_MOB_FARM_MOB_COUNT_NEEDED;
    public ForgeConfigSpec.IntValue ANTI_MOB_FARM_MOB_CLEAR_COUNT_EVERY_X_MINUTES;
    public ForgeConfigSpec.DoubleValue KNOCKPACK_RESISTANCE_ADDITION;

    Config(ForgeConfigSpec.Builder builder) {
        builder.comment("Config").push(NAME);

        FIRE_RESISTANT_MOBS = builder.comment(".").define("FIRE_RESISTANT_MOBS", true);
        KNOCKBACK_RESISTANT_MOBS = builder.comment(".")
                .define("KNOCKBACK_RESISTANT_MOBS", true);
        ANTI_MOB_FARMS = builder.comment(".").define("ANTI_MOB_FARMS", false);
        FALL_DAMAGE_RESISTANT_MOBS = builder.comment(".")
                .define("FALL_DAMAGE_RESISTANT_MOBS", true);

        ANTI_MOB_FARM_MOB_COUNT_NEEDED = builder.comment(".")
                .defineInRange("ANTI_MOB_FARM_MOB_COUNT_NEEDED", 25, 0, Integer.MAX_VALUE);

        KNOCKPACK_RESISTANCE_ADDITION = builder.comment(".")
                .defineInRange("KNOCKPACK_RESISTANCE_ADDITION", 0.9D, 0D, 1D);

        ANTI_MOB_FARM_MOB_CLEAR_COUNT_EVERY_X_MINUTES = builder.comment(".")
                .defineInRange("ANTI_MOB_FARM_MOB_CLEAR_COUNT_EVERY_X_MINUTES", 15, 0, Integer.MAX_VALUE);

        builder.pop();
    }

}