package com.nu.art.cyborg.tutorial.moduleBasics;

import com.nu.art.cyborg.modules.AppDetailsModule;
import com.nu.art.modular.core.ModulesPack;

/**
 * Created by TacB0sS on 15-May 2017.
 */

@SuppressWarnings("unchecked")
public class ModulePack_Basics
	extends ModulesPack {

	// This is convenient way to set the list of modules in the ModulesPack
	private static final Class[] ModuleClasses = {
		AppDetailsModule.class,
		Module_Analytics.class,
		// NOTE this comma    ^    it is here for git merge reasons
	};

	ModulePack_Basics() {
		super(ModuleClasses);
	}

	// Here we can configure ONLY the modules declared by this ModulesPack
	@Override
	protected void init() {
		boolean debug = getModule(AppDetailsModule.class).isDebug();
		getModule(Module_Analytics.class).setAnalyticsKey(debug ? "debug" : "production");
	}
}
