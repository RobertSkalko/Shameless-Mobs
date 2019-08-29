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

    public BooleanValue ANTI_MOB_FARMS;
    public ForgeConfigSpec.IntValue ANTI_MOB_FARM_MOB_COUNT_NEEDED;
    public ForgeConfigSpec.IntValue ANTI_MOB_FARM_MOB_CLEAR_COUNT_EVERY_X_MINUTES;

    Config(ForgeConfigSpec.Builder builder) {
        builder.comment("Config").push(NAME);

        FIRE_RESISTANT_MOBS = builder.comment(".").define("FIRE_RESISTANT_MOBS", true);
        ANTI_MOB_FARMS = builder.comment(".").define("ANTI_MOB_FARMS", true);
        FALL_DAMAGE_RESISTANT_MOBS = builder.comment(".")
                .define("FALL_DAMAGE_RESISTANT_MOBS", true);

        ANTI_MOB_FARM_MOB_COUNT_NEEDED = builder.comment(".")
                .defineInRange("ANTI_MOB_FARM_MOB_COUNT_NEEDED", 25, 0, Integer.MAX_VALUE);

        ANTI_MOB_FARM_MOB_CLEAR_COUNT_EVERY_X_MINUTES = builder.comment(".")
                .defineInRange("ANTI_MOB_FARM_MOB_CLEAR_COUNT_EVERY_X_MINUTES", 15, 0, Integer.MAX_VALUE);

        builder.pop();
    }

}