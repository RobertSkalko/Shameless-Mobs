package com.robertx22.shameless_mobs.parts;

import com.robertx22.shameless_mobs.caps.ChunkCap;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunk;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AntiMobFarm {

    @SubscribeEvent
    public static void onSpawn(LivingSpawnEvent.CheckSpawn event) {

        try {
            if (event.getEntityLiving() instanceof MobEntity) {
                LivingEntity en = event.getEntityLiving();

                IChunk ichunk = en.world.getChunk(en.getPosition());
                if (ichunk instanceof Chunk) {
                    Chunk chunk = (Chunk) ichunk;
                    chunk.getCapability(ChunkCap.Data)
                            .ifPresent(x -> x.onMobSpawn(en, event));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
