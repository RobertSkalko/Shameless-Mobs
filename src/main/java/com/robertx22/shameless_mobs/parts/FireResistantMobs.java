package com.robertx22.shameless_mobs.parts;

import com.robertx22.shameless_mobs.Config;
import net.minecraft.entity.EntityClassification;
import net.minecraftforge.registries.ForgeRegistries;

public class FireResistantMobs {

    public static void Set() {

        if (Config.INSTANCE.FIRE_RESISTANT_MOBS.get()) {
            ForgeRegistries.ENTITIES.getValues()
                    .stream()
                    .filter(x -> x.getClassification() == EntityClassification.MONSTER)
                    .forEach(x -> x.immuneToFire = true);
        }
    }
}
