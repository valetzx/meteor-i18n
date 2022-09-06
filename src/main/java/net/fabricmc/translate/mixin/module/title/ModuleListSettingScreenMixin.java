package net.fabricmc.translate.mixin.module.title;

import meteordevelopment.meteorclient.systems.modules.Module;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.fabricmc.translate.TranslateMod.translateText;

@Mixin(meteordevelopment.meteorclient.gui.screens.settings.ModuleListSettingScreen.class)
public class ModuleListSettingScreenMixin {
    @Redirect(method = "getValueName", at = @At(value = "FIELD", target = "Lmeteordevelopment/meteorclient/systems/modules/Module;title:Ljava/lang/String;", opcode = Opcodes.GETFIELD), remap = false)
    private String translateTitle(Module module) {
        return translateText("meteor-translate.module." + module.name + ".title", module.title);
    }
}
