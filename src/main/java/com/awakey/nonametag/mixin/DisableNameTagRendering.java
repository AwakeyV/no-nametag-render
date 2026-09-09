package com.awakey.nonametag.mixin;

import com.awakey.nonametag.config.ModConfig;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityRenderer.class)
public abstract class DisableNameTagRendering<T extends Entity, S extends EntityRenderState> {

    @Shadow
    protected abstract boolean shouldShowName(T entity, double squaredDistanceToCamera);

    @Redirect(
            method = "extractNameTags(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/client/renderer/entity/state/EntityRenderState;FDD)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/EntityRenderer;shouldShowName(Lnet/minecraft/world/entity/Entity;D)Z"
            )
    )
    private boolean dontRenderTheTag(EntityRenderer<T, S> instance, T entity, double squaredDistanceToCamera) {
        if (ModConfig.getInstance().enabled && !(entity instanceof Player)) {
            return false;
        }
        return this.shouldShowName(entity, squaredDistanceToCamera);
    }
}