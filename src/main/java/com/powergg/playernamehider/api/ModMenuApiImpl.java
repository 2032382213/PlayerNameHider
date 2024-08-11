package com.powergg.playernamehider.api;

import com.powergg.playernamehider.config.MainConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenuApiImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> MainConfig.HANDLER.generateGui().generateScreen(parent);
    }
}
