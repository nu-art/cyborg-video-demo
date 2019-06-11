package com.nu.art.cyborg.tutorial.customAttributes;

import android.app.Application;

import com.nu.art.cyborg.core.CyborgBuilder;
import com.nu.art.cyborg.core.CyborgBuilder.CyborgConfiguration;

public class Application_CustomAttributes
	extends Application {

	@Override
	@SuppressWarnings("unchecked")
	public void onCreate() {
		super.onCreate();
		CyborgBuilder.startCyborg(new CyborgConfiguration(this).setLaunchConfiguration(R.layout.layout__custom_attributes).setModulesPacks(ModulePack.class));
	}
}
