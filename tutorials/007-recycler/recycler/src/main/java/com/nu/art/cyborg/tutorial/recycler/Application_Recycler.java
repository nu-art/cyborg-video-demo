package com.nu.art.cyborg.tutorial.recycler;

import android.app.Application;

import com.nu.art.cyborg.core.CyborgBuilder;
import com.nu.art.cyborg.core.CyborgBuilder.CyborgConfiguration;

public class Application_Recycler
		extends Application {

	@Override
	@SuppressWarnings("unchecked")
	public void onCreate() {
		super.onCreate();
		CyborgBuilder.startCyborg(new CyborgConfiguration(this, R.layout.layout__recycler, ModulePack.class));
	}
}
