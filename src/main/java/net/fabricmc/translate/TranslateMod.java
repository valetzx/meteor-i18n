package net.fabricmc.translate;

import meteordevelopment.meteorclient.settings.Setting;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.resource.language.I18n;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TranslateMod implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("meteor-translate");

    public static Setting<Boolean> enableTranslation = null;

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        LOGGER.info("彗星翻译模组初始化");
    }

    public static String translateText(String key, String text) {
        if (/*enableTranslation.get()*/true){
            String value = I18n.translate(key);
            if (key != value){
                return value;
            }
        }
        return text;
    }
}
