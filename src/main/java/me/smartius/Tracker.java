package me.smartius;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class Tracker {

    public static KeyBinding trackerKeybind;
    boolean isTrackerOn = false;
    int clicks = 0;

    public Tracker() {
        trackerKeybind = new KeyBinding("Keybind to turn the tracker ON/OFF", Keyboard.KEY_I, "Stranded Karate Tracker");
        ClientRegistry.registerKeyBinding(trackerKeybind);
    }

    @SubscribeEvent
    public void onKeyPressed(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        if (trackerKeybind.isPressed()) {
            isTrackerOn = !isTrackerOn;
            mc.thePlayer.addChatComponentMessage(new ChatComponentText("The tracker status is set to: " + isTrackerOn));
        }
    }

    @SubscribeEvent
    public void onMouseEvent(InputEvent.MouseInputEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.gameSettings.keyBindUseItem.isKeyDown() && isTrackerOn) {
            clicks = 0;
        }
        if (mc.gameSettings.keyBindAttack.isKeyDown() && isTrackerOn) {
            clicks++;
            if (clicks == 20) {
                mc.thePlayer.addChatComponentMessage(new ChatComponentText("You hit the required clicks!"));
                playSound(mc);
            }
        }
    }

    private void playSound(Minecraft mc) {
        // Play the sound "random.levelup" as an example
        ResourceLocation soundLocation = new ResourceLocation("random.levelup");
        mc.theWorld.playSound(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, soundLocation.getResourcePath(), 1.0F, 1.0F, false);
    }
}