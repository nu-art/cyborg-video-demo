package com.nu.art.cyborg.tutorial.activityForResult;

import com.nu.art.modular.core.ModulesPack;

@SuppressWarnings("unchecked")
public class ModulePack_ActivityForResult
	extends ModulesPack {

	private static final Class[] ModuleClasses = {
		Module_ActivityForResult.class,
	};

	ModulePack_ActivityForResult() {
		super(ModuleClasses);
	}

	@Override
	protected void init() {
	}
}
