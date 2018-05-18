package com.nu.art.cyborg.tutorial.notificationsApi;

import com.nu.art.cyborg.modules.notifications.NotificationsModule;
import com.nu.art.modular.core.ModulesPack;

/**
 * Created by TacB0sS on 15-May 2017.
 */

@SuppressWarnings("unchecked")
public class NotificationsModulePack
	extends ModulesPack {

	private static final Class[] ModuleClasses = {
		NotificationsModule.class
	};

	NotificationsModulePack() {
		super(ModuleClasses);
	}

	@Override
	protected void init() {
		getModule(NotificationsModule.class).addNotificationHandler(Notification_Basic.class);
	}
}
