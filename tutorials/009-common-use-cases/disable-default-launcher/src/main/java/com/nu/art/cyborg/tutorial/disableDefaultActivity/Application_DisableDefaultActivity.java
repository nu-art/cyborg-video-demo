package com.nu.art.cyborg.tutorial.disableDefaultActivity;

import android.app.Application;

import com.nu.art.cyborg.core.CyborgBuilder;
import com.nu.art.cyborg.core.CyborgBuilder.CyborgConfiguration;

public class Application_DisableDefaultActivity
		extends Application {

	@Override
	@SuppressWarnings("unchecked")
	public void onCreate() {
		super.onCreate();
		CyborgBuilder.startCyborg(new CyborgConfiguration(this, -1));
	}
}
