package me.smartius;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

@Mod(modid = Main.MODID, useMetadata = true)
public class Main {
    public static final String MODID = "StrandedKarateTracker";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // Pre-initialization code, if any
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Tracker tracker = new Tracker();
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(tracker);
    }
}