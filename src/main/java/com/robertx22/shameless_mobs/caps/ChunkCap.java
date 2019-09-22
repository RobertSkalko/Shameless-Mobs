package com.robertx22.shameless_mobs.caps;

import com.robertx22.shameless_mobs.Config;
import com.robertx22.shameless_mobs.Main;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ChunkCap {

    public static final ResourceLocation RESOURCE = new ResourceLocation(Main.ID, "chunkcap");

    @CapabilityInject(IChunkCap.class)
    public static final Capability<IChunkCap> Data = null;

    static final String MOB_SPAWNS = "MOB_SPAWNS";
    static final String TIME_OF_FIRST_SPAWN = "TIME_OF_FIRST_SPAWN";

    public interface IChunkCap extends ICommonCapability {

        void onMobSpawn(LivingEntity en, LivingSpawnEvent.CheckSpawn event);

    }

    @Mod.EventBusSubscriber
    public static class EventHandler {

        @SubscribeEvent
        public static void onChunkConstruct(AttachCapabilitiesEvent<Chunk> event) {
            event.addCapability(RESOURCE, new Provider());
        }

    }

    public static class Provider extends BaseProvider<IChunkCap> {

        @Override
        public IChunkCap defaultImpl() {
            return new DefaultImpl();
        }

        @Override
        public Capability<IChunkCap> dataInstance() {
            return Data;
        }
    }

    public static class DefaultImpl implements IChunkCap {

        int mobSpawns = 0;
        int timeOfFirstSpawn = 0;

        @Override
        public CompoundNBT getNBT() {
            CompoundNBT nbt = new CompoundNBT();
            nbt.putInt(MOB_SPAWNS, mobSpawns);
            nbt.putInt(TIME_OF_FIRST_SPAWN, timeOfFirstSpawn);

            return nbt;

        }

        @Override
        public void setNBT(CompoundNBT nbt) {

            this.mobSpawns = nbt.getInt(MOB_SPAWNS);
            this.timeOfFirstSpawn = nbt.getInt(TIME_OF_FIRST_SPAWN);
        }

        private boolean shouldPreventSpawns() {
            return this.mobSpawns > Config.INSTANCE.ANTI_MOB_FARM_MOB_COUNT_NEEDED.get();
        }

        public static int serverTimeToMinutes(MinecraftServer server) {
            return (int) (server.getServerTime() / 60000);
        }

        @Override
        public void onMobSpawn(LivingEntity en, LivingSpawnEvent.CheckSpawn event) {

            int currentTime = serverTimeToMinutes(en.getServer());

            if (this.timeOfFirstSpawn >= currentTime) { // if server restarted
                this.timeOfFirstSpawn = 0;
            }

            if (currentTime >= Config.INSTANCE.ANTI_MOB_FARM_MOB_CLEAR_COUNT_EVERY_X_MINUTES
                    .get()) {
                if (this.timeOfFirstSpawn + Config.INSTANCE.ANTI_MOB_FARM_MOB_CLEAR_COUNT_EVERY_X_MINUTES
                        .get() >= currentTime) {
                    this.mobSpawns = 0;
                }
            }

            if (this.shouldPreventSpawns()) {
                event.setResult(Event.Result.DENY);
            } else {

                if (this.mobSpawns == 0) {
                    this.timeOfFirstSpawn = serverTimeToMinutes(en.getServer());
                }
                this.mobSpawns++;
            }

        }
    }

    public static class Storage extends BaseStorage<IChunkCap> {

    }

}