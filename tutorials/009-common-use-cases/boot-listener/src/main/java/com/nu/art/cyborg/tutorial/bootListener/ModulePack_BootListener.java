package com.nu.art.cyborg.tutorial.bootListener;

import com.nu.art.modular.core.ModulesPack;

@SuppressWarnings("unchecked")
public class ModulePack_BootListener
	extends ModulesPack {

	private static final Class[] ModuleClasses = {
		Module_BootListener.class,
	};

	ModulePack_BootListener() {
		super(ModuleClasses);
	}

	@Override
	protected void init() {
	}
}
