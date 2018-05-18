package com.nu.art.cyborg.tutorial.notificationsApi;

import android.app.Application;

import com.nu.art.cyborg.core.CyborgBuilder;
import com.nu.art.cyborg.core.CyborgBuilder.CyborgConfiguration;

public class Application_NotificationApi
	extends Application {

	@Override
	@SuppressWarnings("unchecked")
	public void onCreate() {
		super.onCreate();
		CyborgBuilder.startCyborg(new CyborgConfiguration(this, R.layout.cyborgview__notifications_basics, NotificationsModulePack.class));
	}
}
