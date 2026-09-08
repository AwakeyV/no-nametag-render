package com.awakey.nonametag.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigManager;
import me.shedaniel.autoconfig.gui.ConfigScreenProvider;
import me.shedaniel.autoconfig.gui.DefaultGuiProviders;
import me.shedaniel.autoconfig.gui.DefaultGuiTransformers;
import me.shedaniel.autoconfig.gui.registry.GuiRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            GuiRegistry registry = new GuiRegistry();
            DefaultGuiProviders.apply(registry);
            DefaultGuiTransformers.apply(registry);

            @SuppressWarnings("unchecked")
            ConfigManager<ModConfig> manager = (ConfigManager<ModConfig>) AutoConfig.getConfigHolder(ModConfig.class);

            return new ConfigScreenProvider<>(manager, registry, parent).get();
        };
    }
}