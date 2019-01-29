package com.nu.art.cyborg.tutorial.videoPlayer;

import com.nu.art.modular.core.ModulesPack;

@SuppressWarnings("unchecked")
public class ModulePack_VideoPlayer
	extends ModulesPack {

	private static final Class[] ModuleClasses = {
		Module_VideoPlayer.class,
	};

	ModulePack_VideoPlayer() {
		super(ModuleClasses);
	}

	@Override
	protected void init() {
	}
}
