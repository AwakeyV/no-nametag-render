package com.awakey.nonametag;

import com.awakey.nonametag.config.ModConfig;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoNametagRender implements ModInitializer {
	public static final String MOD_ID = "no-nametag-render";


	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
	ModConfig.init();
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
