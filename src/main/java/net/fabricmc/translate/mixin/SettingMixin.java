package net.fabricmc.translate.mixin;

import meteordevelopment.meteorclient.systems.config.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.fabricmc.translate.TranslateMod.enableTranslation;

@Mixin(meteordevelopment.meteorclient.settings.Setting.class)
public abstract class SettingMixin {
    @Shadow(remap = false)
    public abstract boolean equals(Object o);

    @Inject(method = "get", at = @At("HEAD"), cancellable = true, remap = false)
    private void get(CallbackInfoReturnable cir) {
        if (this.equals(Config.get().customFont) && enableTranslation.get()) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}
