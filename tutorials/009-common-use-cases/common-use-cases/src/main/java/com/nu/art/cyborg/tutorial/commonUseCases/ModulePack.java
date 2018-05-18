package com.nu.art.cyborg.tutorial.commonUseCases;

import com.nu.art.modular.core.ModulesPack;

/**
 * Created by TacB0sS on 15-May 2017.
 */

@SuppressWarnings("unchecked")
public class ModulePack
	extends ModulesPack {

	private static final Class[] ModuleClasses = {
		Module_CommonUseCase.class
	};

	ModulePack() {
		super(ModuleClasses);
	}

	@Override
	protected void init() {
	}
}
