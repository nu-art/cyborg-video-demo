package com.nu.art.cyborg.tutorial.controllerInjections;

import com.nu.art.modular.core.ModulesPack;

/**
 * Created by TacB0sS on 15-May 2017.
 */

@SuppressWarnings("unchecked")
public class ModulePack_ControllerInjections
	extends ModulesPack {

	private static final Class[] ModuleClasses = {
		Module_ControllerInjections.class,
	};

	ModulePack_ControllerInjections() {
		super(ModuleClasses);
	}

	@Override
	protected void init() {
	}
}
