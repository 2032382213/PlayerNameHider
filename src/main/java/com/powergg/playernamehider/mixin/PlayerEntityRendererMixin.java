package com.powergg.playernamehider.mixin;

import com.powergg.playernamehider.client.PlayerNameHiderClient;
import com.powergg.playernamehider.utils.UidUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
    @ModifyArgs(method = "renderLabelIfPresent(Lnet/minecraft/entity/Entity;Lnet/minecraft/text/Text;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IF)V", at= @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/PlayerEntityRenderer;renderLabelIfPresent(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/text/Text;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IF)V"))
    public void changePlayerNameTagText(Args args) {
        if (!PlayerNameHiderClient.MAIN_CONFIG.enabled) return;
        if (!PlayerNameHiderClient.MAIN_CONFIG.hide_own_name
                && args.get(0).equals(MinecraftClient.getInstance().player)
        ) return;
        args.set(
                1,
                Text.literal(
                        PlayerNameHiderClient.MAIN_CONFIG.format.replace(
                                "{}",
                                "%s".formatted(UidUtils.getUid(((PlayerEntity) args.get(0)).getUuid()))
                        )
                )
        );
    }
}
