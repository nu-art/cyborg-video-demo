package com.nu.art.cyborg.tutorial.injectionExample;

import android.app.Application;

import com.nu.art.cyborg.core.CyborgBuilder;
import com.nu.art.cyborg.core.CyborgBuilder.CyborgConfiguration;

public class Application_InjectionExample
	extends Application {

	@Override
	@SuppressWarnings("unchecked")
	public void onCreate() {
		super.onCreate();
		// Providing the first layout to preset once the application launches.
		CyborgBuilder.startCyborg(new CyborgConfiguration(this, R.layout.cyborgview__root, ModulePack_InjectionExample.class));
	}
}
