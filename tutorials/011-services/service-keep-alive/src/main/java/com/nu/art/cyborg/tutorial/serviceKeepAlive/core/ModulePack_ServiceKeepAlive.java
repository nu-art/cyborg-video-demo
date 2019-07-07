package com.nu.art.cyborg.tutorial.serviceKeepAlive.core;

import com.nu.art.cyborg.modules.notifications.NotificationsModule;
import com.nu.art.modular.core.ModulesPack;

@SuppressWarnings("unchecked")
public class ModulePack_ServiceKeepAlive
	extends ModulesPack {

	private static final Class[] ModuleClasses = {
		NotificationsModule.class,
		Module_ServiceKeepAlive.class,
	};

	ModulePack_ServiceKeepAlive() {
		super(ModuleClasses);
	}

	@Override
	protected void init() {
	}
}
