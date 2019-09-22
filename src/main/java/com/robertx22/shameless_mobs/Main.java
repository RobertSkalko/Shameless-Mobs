package com.robertx22.shameless_mobs;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.robertx22.shameless_mobs.caps.ChunkCap;
import com.robertx22.shameless_mobs.parts.AntiMobFarm;
import com.robertx22.shameless_mobs.parts.FallResistantMobs;
import com.robertx22.shameless_mobs.parts.FireResistantMobs;
import com.robertx22.shameless_mobs.parts.KnockbackResistantMobs;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;

@Mod(Main.ID)
public class Main {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final String ID = "shameless_mobs";

    public Main() {

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.spec);
        loadConfig(Config.spec, FMLPaths.CONFIGDIR.get().resolve(ID + "-common.toml"));

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        if (Config.INSTANCE.FALL_DAMAGE_RESISTANT_MOBS.get()) {
            MinecraftForge.EVENT_BUS.register(FallResistantMobs.class);
        }
        if (Config.INSTANCE.ANTI_MOB_FARMS.get()) {
            MinecraftForge.EVENT_BUS.register(AntiMobFarm.class);
        }
        if (Config.INSTANCE.KNOCKBACK_RESISTANT_MOBS.get()) {
            MinecraftForge.EVENT_BUS.register(KnockbackResistantMobs.class);
        }

        MinecraftForge.EVENT_BUS.register(this);

    }

    private static void loadConfig(ForgeConfigSpec spec, Path path) {

        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();

        spec.setConfig(configData);
    }

    private void setup(final FMLCommonSetupEvent event) {

        CapabilityManager.INSTANCE.register(ChunkCap.IChunkCap.class, new ChunkCap.Storage(), ChunkCap.DefaultImpl::new);

    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        FireResistantMobs.Set();
    }

    private void processIMC(final InterModProcessEvent event) {

    }

}
