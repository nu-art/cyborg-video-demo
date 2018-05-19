package com.nu.art.cyborg.tutorial.adapterBasedViews;

import com.nu.art.cyborg.tutorial.infraExample.Module_Examples;
import com.nu.art.modular.core.ModulesPack;

/**
 * Created by TacB0sS on 15-May 2017.
 */

@SuppressWarnings("unchecked")
public class ModulePack
	extends ModulesPack {

	private static final Class[] ModuleClasses = {
		Module_Examples.class,
	};

	ModulePack() {
		super(ModuleClasses);
	}

	@Override
	protected void init() {
		getModule(Module_Examples.class).setExample(Examples.class);
	}
}
