package com.nu.art.cyborg.tutorial.mediaPlayer;

import com.nu.art.modular.core.ModulesPack;

@SuppressWarnings("unchecked")
public class ModulePack_MediaPlayer
	extends ModulesPack {

	private static final Class[] ModuleClasses = {
		Module_MediaPlayer.class,
	};

	ModulePack_MediaPlayer() {
		super(ModuleClasses);
	}

	@Override
	protected void init() {
	}
}
