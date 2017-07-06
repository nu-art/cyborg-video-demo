package com.nu.art.cyborg.tutorial.moduleCommunication;

import com.art.nu.cyborg.login_module.LoginModule;
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
			LoginModule.class,
			AppModule.class
	};

	BasicModulePack() {
		super(ModuleClasses);
	}
}
