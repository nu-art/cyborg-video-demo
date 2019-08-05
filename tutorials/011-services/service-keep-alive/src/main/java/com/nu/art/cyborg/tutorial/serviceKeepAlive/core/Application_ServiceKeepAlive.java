package com.nu.art.cyborg.tutorial.serviceKeepAlive.core;

import android.app.Application;

import com.nu.art.cyborg.core.CyborgBuilder;
import com.nu.art.cyborg.core.CyborgBuilder.CyborgConfiguration;
import com.nu.art.cyborg.tutorial.serviceKeepAlive.R;

public class Application_ServiceKeepAlive
	extends Application {

	@Override
	@SuppressWarnings("unchecked")
	public void onCreate() {
		super.onCreate();
		// Providing the first layout to preset once the application launches.
		CyborgBuilder.startCyborg(new CyborgConfiguration(this).setLaunchConfiguration(R.layout.cyborgview__root)
		                                                       .setModulesPacks(ModulePack_ServiceKeepAlive.class));
	}
}
