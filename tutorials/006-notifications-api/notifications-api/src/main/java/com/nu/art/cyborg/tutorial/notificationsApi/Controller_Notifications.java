package com.nu.art.cyborg.tutorial.notificationsApi;

import android.view.View;
import android.widget.TextView;

import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;
import com.nu.art.cyborg.notifications.NotificationsModule;
import com.nu.art.cyborg.tutorial.notificationsApi.Notification_Basic.NotificationActionListener;

/**
 * Created by TacB0sS on 20-May 2017.
 */

public class Controller_Notifications
		extends CyborgController
		implements NotificationActionListener {

	@ViewIdentifier(viewId = R.id.TV_ShowNotification,
									listeners = {
											ViewListener.OnLongClick,
											ViewListener.OnClick
									})
	TextView notificationButton;

	@ViewIdentifier(viewId = R.id.TV_ActionLabel)
	TextView actionLabel;

	public Controller_Notifications() {
		super(R.layout.controller__notification_basics);
	}

	@Override
	public void onClick(View v) {
		getModule(NotificationsModule.class).getNotificationHandler(Notification_Basic.class).post();
	}

	@Override
	public boolean onLongClick(View v) {
		getModule(NotificationsModule.class).getNotificationHandler(Notification_Basic.class).cancel();
		return true;
	}

	@Override
	public void onNotificationClicked(String action) {
		actionLabel.setText(action);
	}
}
