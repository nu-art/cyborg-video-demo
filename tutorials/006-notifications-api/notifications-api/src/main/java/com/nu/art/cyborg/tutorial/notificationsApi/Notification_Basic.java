package com.nu.art.cyborg.tutorial.notificationsApi;

import android.os.Bundle;
import android.support.v4.app.NotificationCompat.Builder;

import com.nu.art.core.generics.Processor;
import com.nu.art.cyborg.modules.notifications.NotificationHandler;

/**
 * Created by TacB0sS on 13-Jun 2017.
 */

class Notification_Basic
	extends NotificationHandler {

	interface NotificationActionListener {

		void onNotificationClicked(String action);
	}

	private static final short NotificationId = 42;

	@Override
	protected void processNotification(short notificationId, final String action, Bundle bundle) {
		logInfo("Notification Action: " + action);
		dispatchEvent("Notification action: " + action, NotificationActionListener.class, new Processor<NotificationActionListener>() {
			@Override
			public void process(NotificationActionListener listener) {
				listener.onNotificationClicked(action);
			}
		});
	}

	void cancel() {
		cancelNotification(NotificationId);
	}

	public void post() {
		Builder builder = createBasicBuilder(NotificationId);
		builder.setContentTitle("Basic notification");
		builder.setContentText("Body");
		builder.setSmallIcon(R.drawable.send_icon);
		builder.setTicker("Ticker");

		addActionButton(builder, NotificationId, "Action_Send", R.drawable.send_icon, "Send");
		addActionButton(builder, NotificationId, "Action_Call", R.drawable.call_icon, "Call");

		postNotification(builder, NotificationId);
	}
}
