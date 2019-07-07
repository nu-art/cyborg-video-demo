package com.nu.art.cyborg.tutorial.serviceKeepAlive.core;

import com.nu.art.cyborg.common.interfaces.StringResourceResolver;
import com.nu.art.cyborg.core.CyborgModule;
import com.nu.art.cyborg.core.abs.Cyborg;
import com.nu.art.cyborg.modules.notifications.NotificationsModule;

public class Module_ServiceKeepAlive
	extends CyborgModule {

	private Module_ServiceKeepAlive() {}

	@Override
	protected void init() {
	}

	final void handleActionB() {
		toastLong(new StringResourceResolver() {
			@Override
			public String getString(Cyborg cyborg) {
				return "Toast from notification: Action B";
			}
		});
	}

	public void postNotificationAndStartService() {
		getModule(NotificationsModule.class).getNotificationHandler(Notification_ForegroundService.class).post();
	}

	public void handleNotificationCancel() {
		toastLong(new StringResourceResolver() {
			@Override
			public String getString(Cyborg cyborg) {
				return "Toast notification swiped";
			}
		});
	}
}
