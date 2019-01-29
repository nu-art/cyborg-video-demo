package com.nu.art.cyborg.tutorial.internetConnectivity;

import com.nu.art.modular.core.ModulesPack;

@SuppressWarnings("unchecked")
public class ModulePack_InternetConnectivity
	extends ModulesPack {

	private static final Class[] ModuleClasses = {
		Module_RequiresInternetConnectivity.class,
	};

	ModulePack_InternetConnectivity() {
		super(ModuleClasses);
	}
}
