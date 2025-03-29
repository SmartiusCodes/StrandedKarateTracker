package me.smartius;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

public class StrandedKarateTracker {

    public static KeyBinding strandedKarateTrackerKeybind;
    boolean isTrackerOn = false;
    int clicks = 0;

    public StrandedKarateTracker() {
        strandedKarateTrackerKeybind = new KeyBinding("Stranded Karate Tracker ON/OFF", Keyboard.KEY_K, "Smart Mod");
        ClientRegistry.registerKeyBinding(strandedKarateTrackerKeybind);
    }

    @SubscribeEvent
    public void onKeyPressed(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        if (strandedKarateTrackerKeybind.isPressed()) {
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
                notify(mc);
            }
        }
    }

    private void notify(Minecraft mc) {
        // Sends a message in chat
        mc.thePlayer.addChatComponentMessage(new ChatComponentText("You hit the required clicks!"));

        // Play the sound "random.levelup" as an example
        ResourceLocation soundLocation = new ResourceLocation("random.levelup");
        mc.theWorld.playSound(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, soundLocation.getResourcePath(), 1.0F, 1.0F, false);

        mc.ingameGUI.displayTitle("Karate!", "", 10, 20, 10);
    }
}