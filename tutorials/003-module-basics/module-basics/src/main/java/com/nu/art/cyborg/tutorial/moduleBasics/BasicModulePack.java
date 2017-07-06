package com.nu.art.cyborg.tutorial.moduleBasics;

import com.nu.art.cyborg.modules.AppDetailsModule;
import com.nu.art.modular.core.ModulesPack;

/**
 * Created by TacB0sS on 15-May 2017.
 */

@SuppressWarnings("unchecked")
public class BasicModulePack
		extends ModulesPack {

	private static final Class[] ModuleClasses = {
			AppDetailsModule.class,
			BasicAnalyticsModule.class,
	};

	BasicModulePack() {
		super(ModuleClasses);
	}

	@Override
	protected void init() {
		boolean debug = getModule(AppDetailsModule.class).isDebug();
		getModule(BasicAnalyticsModule.class).setAnalyticsKey(debug ? "debug" : "production");
	}
}
