package com.nu.art.cyborg.tutorial.sharedPreferences;

import com.nu.art.modular.core.ModulesPack;

/**
 * Created by TacB0sS on 15-May 2017.
 */

@SuppressWarnings("unchecked")
public class BasicModulePack
	extends ModulesPack {

	private static final Class[] ModuleClasses = {
		TestPrefsModule.class
	};

	BasicModulePack() {
		super(ModuleClasses);
	}

	@Override
	protected void init() {
	}
}
