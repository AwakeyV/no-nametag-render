package com.awakey.nonametag.mixin;

import com.awakey.nonametag.config.ModConfig;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityRenderer.class)
public abstract class DisableNameTagRendering<T extends Entity, S extends EntityRenderState> {

    @Shadow
    protected abstract boolean hasLabel(T entity, double squaredDistanceToCamera);

    @Redirect(
            method = "updateRenderState",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/entity/EntityRenderer;hasLabel(Lnet/minecraft/entity/Entity;D)Z"
            )
    )
    private boolean dontRenderTheTag(EntityRenderer<T, S> instance, T entity, double squaredDistanceToCamera) {
        if (ModConfig.getInstance().enabled && !(entity instanceof PlayerEntity)) {
            return false;
        }
        return this.hasLabel(entity, squaredDistanceToCamera);
    }
}