//Code in Show Spawn Time by Seosean

package kr.hahaha98757.zombiesaddon.mixins;

import kr.hahaha98757.zombiesaddon.config.ZombiesAddonConfig;
import kr.hahaha98757.zombiesaddon.features.PlayerVisibility;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelPlayer.class)
public abstract class MixinModelPlayer extends MixinModelBiped {
    @Inject(method = "render", at = @At("HEAD"))
    private void za$preRender(Entity entityIn, float f, float g, float h, float i, float j, float scale, CallbackInfo ci) {
        if (ZombiesAddonConfig.isEnableMod() && PlayerVisibility.playerVisibility && ZombiesAddonConfig.isPlayerTranslucent() && PlayerVisibility.isPlayerInvisible(entityIn)) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, PlayerVisibility.getAlpha(entityIn));
            GlStateManager.depthMask(false);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 771);
            GlStateManager.alphaFunc(516, 0.003921569F);
        }
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void za$postRender(Entity entityIn, float f, float g, float h, float i, float j, float scale, CallbackInfo ci) {
        if (ZombiesAddonConfig.isEnableMod() && PlayerVisibility.playerVisibility && ZombiesAddonConfig.isPlayerTranslucent() && PlayerVisibility.isPlayerInvisible(entityIn)) {
            GlStateManager.disableBlend();
            GlStateManager.alphaFunc(516, 0.1F);
            GlStateManager.depthMask(true);
        }
    }
}