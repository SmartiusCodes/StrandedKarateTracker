package me.smartius;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.MODID, useMetadata = true)
public class Main {
    public static final String MODID = "SmartMod";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // Pre-initialization code, if any
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        StrandedKarateTracker skt = new StrandedKarateTracker();
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(skt);
    }
}