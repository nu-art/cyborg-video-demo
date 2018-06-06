package com.nu.art.cyborg.tutorial.googleMaps;

import com.nu.art.cyborg.modules.LocationModule;
import com.nu.art.modular.core.ModulesPack;

/**
 * Created by TacB0sS on 15-May 2017.
 */

@SuppressWarnings("unchecked")
public class ModulePack_GoogleMaps
	extends ModulesPack {

	private static final Class[] ModuleClasses = {
		LocationModule.class
	};

	ModulePack_GoogleMaps() {
		super(ModuleClasses);
	}

	@Override
	protected void init() {
	}
}
