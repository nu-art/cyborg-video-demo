package com.nu.art.cyborg.tutorial.injectionExample;

import com.nu.art.modular.core.ModulesPack;

/**
 * Created by TacB0sS on 15-May 2017.
 */

@SuppressWarnings("unchecked")
public class ModulePack_InjectionExample
	extends ModulesPack {

	private static final Class[] ModuleClasses = {
		Module_InjectionExample.class,
	};

	ModulePack_InjectionExample() {
		super(ModuleClasses);
	}

	@Override
	protected void init() {
	}
}
