package com.robertx22.shameless_mobs.parts;

import com.robertx22.shameless_mobs.caps.ChunkCap;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunk;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AntiMobFarm {

    public static int ANTI_MOB_FARM_MOB_CLEAR_COUNT_EVERY_X_MINUTES = 0;
    public static int ANTI_MOB_FARM_MOB_COUNT_NEEDED = 0;

    @SubscribeEvent
    public static void onSpawn(LivingSpawnEvent.CheckSpawn event) {

        try {
            if (event.getEntityLiving() instanceof MobEntity) {
                LivingEntity en = event.getEntityLiving();

                IChunk ichunk = en.world.getChunk(en.getPosition());
                if (ichunk instanceof Chunk) {
                    Chunk chunk = (Chunk) ichunk;

                    ChunkCap.IChunkCap cap = chunk.getCapability(ChunkCap.Data)
                            .orElse(new ChunkCap.DefaultImpl());

                    cap.onMobSpawn(en, event);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
