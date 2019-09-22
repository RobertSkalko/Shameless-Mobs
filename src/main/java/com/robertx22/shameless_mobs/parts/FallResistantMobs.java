package com.robertx22.shameless_mobs.parts;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class FallResistantMobs {

    @SubscribeEvent
    public static void onMobTakeFallDmg(LivingDamageEvent event) {

        LivingEntity en = event.getEntityLiving();

        if (en instanceof PlayerEntity == false) {
            if (en instanceof MobEntity || en instanceof IMob) {
                if (event.getSource().damageType.equals(DamageSource.FALL.damageType)) {
                    event.setAmount(0);
                }
            }
        }
    }

}
