package com.nu.art.cyborg.tutorial.splashScreen;

import com.nu.art.modular.core.ModulesPack;

@SuppressWarnings("unchecked")
public class ModulePack_SplashScreen
	extends ModulesPack {

	private static final Class[] ModuleClasses = {
		Module_SplashScreen.class,
	};

	ModulePack_SplashScreen() {
		super(ModuleClasses);
	}

	@Override
	protected void init() {
	}
}
