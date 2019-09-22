package com.robertx22.shameless_mobs.parts;

import com.robertx22.shameless_mobs.Config;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class KnockbackResistantMobs {

    @SubscribeEvent
    public static void onMobTakeFallDmg(EntityJoinWorldEvent event) {

        if (event.getEntity() instanceof LivingEntity && !(event.getEntity() instanceof PlayerEntity)) {

            LivingEntity en = (LivingEntity) event.getEntity();

            IAttributeInstance attribute = en.getAttributes()
                    .getAttributeInstance(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);

            if (attribute != null) {
                attribute.setBaseValue(MathHelper.clamp(attribute.getBaseValue() + Config.INSTANCE.KNOCKPACK_RESISTANCE_ADDITION
                        .get(), 0, 1));
            }
        }
    }
}
