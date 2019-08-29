package com.robertx22.shameless_mobs.parts;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class FallResistantMobs {

    @SubscribeEvent
    public static void onMobSpawn(EntityJoinWorldEvent event) {

        if (event.getEntity() instanceof PlayerEntity == false) {
            if (event.getEntity() instanceof MobEntity) {
                LivingEntity en = (LivingEntity) event.getEntity();
                en.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, Integer.MAX_VALUE, 1, false, false));
            }
        }
    }

}
