package net.fabricmc.translate.mixin;

import meteordevelopment.meteorclient.settings.BoolSetting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import net.fabricmc.translate.TranslateMod;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(meteordevelopment.meteorclient.systems.config.Config.class)
public class ConfigMixin {
    @Shadow(remap = false) @Final private SettingGroup sgVisual;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void Config(CallbackInfo ci) {
        TranslateMod.enableTranslation = sgVisual.add(new BoolSetting.Builder()
                .name("translate")
                .description("Use the translation.")
                .defaultValue(true)
                .build()
        );
    }
}
