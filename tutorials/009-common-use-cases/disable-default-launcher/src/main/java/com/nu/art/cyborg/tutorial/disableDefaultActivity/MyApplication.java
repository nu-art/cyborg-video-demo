package com.nu.art.cyborg.tutorial.disableDefaultActivity;

import android.app.Application;

import com.nu.art.cyborg.core.CyborgBuilder;
import com.nu.art.cyborg.core.CyborgBuilder.CyborgConfiguration;
import com.nu.art.cyborg.core.CyborgBuilder.LaunchConfiguration;

public class MyApplication
	extends Application {

	@Override
	@SuppressWarnings("unchecked")
	public void onCreate() {
		super.onCreate();
		LaunchConfiguration launchConfig = new LaunchConfiguration(-1, "", MyActivity.class);
		CyborgBuilder.startCyborg(new CyborgConfiguration(this, launchConfig));
	}
}
