package com.powergg.playernamehider.mixin;

import com.powergg.playernamehider.client.PlayerNameHiderClient;
import com.powergg.playernamehider.utils.UidUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.scoreboard.Team;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Objects;

@Mixin(PlayerListHud.class)
public abstract class PlayerListHudMixin {
    @Shadow protected abstract Text applyGameModeFormatting(PlayerListEntry entry, MutableText name);

    /**
     * @author PowerGG
     * @reason Hide the names in the tab list
     */
    @Overwrite
    public Text getPlayerName(PlayerListEntry entry) {
        if (!PlayerNameHiderClient.MAIN_CONFIG.enabled) {
            return entry.getDisplayName() != null
                    ? this.applyGameModeFormatting(entry, entry.getDisplayName().copy())
                    : this.applyGameModeFormatting(entry, Team.decorateName(entry.getScoreboardTeam(), Text.literal(entry.getProfile().getName())));
        }

        if (!PlayerNameHiderClient.MAIN_CONFIG.hide_own_name
                && entry.getProfile().getId().equals(Objects.requireNonNull(MinecraftClient.getInstance().player).getUuid())
        ) {
            return entry.getDisplayName() != null
                    ? this.applyGameModeFormatting(entry, entry.getDisplayName().copy())
                    : this.applyGameModeFormatting(entry, Team.decorateName(entry.getScoreboardTeam(), Text.literal(entry.getProfile().getName())));
        }

        return Text.literal(
                PlayerNameHiderClient.MAIN_CONFIG.format.replace(
                        "{}",
                        "%s".formatted(UidUtils.getUid(entry.getProfile().getId()))
        ));
    }
}
