package com.nu.art.cyborg.tutorial.serviceKeepAlive.core;

import android.content.Intent;

import com.nu.art.cyborg.core.CyborgServiceBase;

public class Service_KeepAlive
		extends CyborgServiceBase {

	@Override
	protected BaseBinder createBinder() {
		return new BaseBinder() {
			@Override
			public Service_KeepAlive getService() {
				return Service_KeepAlive.this;
			}
		};
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		return START_STICKY;
	}
}
