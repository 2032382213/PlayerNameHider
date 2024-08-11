package com.powergg.playernamehider.client;

import com.powergg.playernamehider.config.MainConfig;
import com.powergg.playernamehider.utils.UidUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class PlayerNameHiderClient implements ClientModInitializer {
    public static final String MOD_ID = "playernamehider";
    public static final String FORMAT_MOD_ID = "PlayerNameHider";
    public static final Path ROOT_CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve(MOD_ID);
    public static final MainConfig MAIN_CONFIG;

    static {
        MAIN_CONFIG = MainConfig.HANDLER.instance();
    }

    @Override
    public void onInitializeClient() {
        ClientPlayConnectionEvents.DISCONNECT.register((s1, s2) -> UidUtils.reset());
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(literal("png")
                .then(literal("open")
                        .executes((c) -> {
                            c.getSource().getClient().setScreen(MainConfig.HANDLER.generateGui().generateScreen(
                                    c.getSource().getClient().currentScreen
                            ));
                            return 1;
                        })
                )
        ));
    }
}
