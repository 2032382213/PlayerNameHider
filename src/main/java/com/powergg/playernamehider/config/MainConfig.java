package com.powergg.playernamehider.config;

import com.powergg.playernamehider.client.PlayerNameHiderClient;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.autogen.AutoGen;
import dev.isxander.yacl3.config.v2.api.autogen.Boolean;
import dev.isxander.yacl3.config.v2.api.autogen.StringField;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.minecraft.util.Identifier;

public class MainConfig {
    public static ConfigClassHandler<MainConfig> HANDLER = ConfigClassHandler.createBuilder(MainConfig.class)
            .id(Identifier.of(PlayerNameHiderClient.MOD_ID, "message.main_config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(
                            PlayerNameHiderClient.ROOT_CONFIG_PATH
                                    .resolve(PlayerNameHiderClient.MOD_ID+".json5")
                    )
                    .setJson5(true)
                    .build())
            .build();

    static {
        MainConfig.HANDLER.load();
    }

    @SerialEntry @AutoGen(category = "Settings") @Boolean public boolean enabled = true;
    @SerialEntry @AutoGen(category = "Settings") @Boolean public boolean hide_own_name = false;
    @SerialEntry @AutoGen(category = "Settings") @StringField public String format = "Player#{}";
}
