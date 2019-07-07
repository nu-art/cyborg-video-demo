package com.nu.art.cyborg.tutorial.serviceKeepAlive.core;

import android.app.Notification;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.Builder;
import android.widget.RemoteViews;

import com.nu.art.core.generics.Processor;
import com.nu.art.cyborg.common.interfaces.StringResourceResolver;
import com.nu.art.cyborg.core.abs.Cyborg;
import com.nu.art.cyborg.modules.notifications.NotificationHandler;
import com.nu.art.cyborg.tutorial.serviceKeepAlive.R;

public class Notification_ForegroundService
	extends NotificationHandler {

	public interface NotificationClickListener {

		void onClicked();
	}

	public static final short NotificationId = (short) "Notification_ForegroundService".hashCode();

	public static final String Action_ActionA = "Action A";
	public static final String Action_ActionB = "Action B";

	@Override
	protected void processNotification(short notificationId, String action, Bundle bundle) {
		Module_ServiceKeepAlive module = getModule(Module_ServiceKeepAlive.class);
		switch (action) {
			case Action_Click:
				dispatchEvent("Notification clicked", NotificationClickListener.class, new Processor<NotificationClickListener>() {
					@Override
					public void process(NotificationClickListener listener) {
						listener.onClicked();
					}
				});
				break;

			case Action_ActionA:
				toastLong(new StringResourceResolver() {
					@Override
					public String getString(Cyborg cyborg) {
						return "Toast from notification: Action A";
					}
				});
				break;

			case Action_ActionB:
				module.handleActionB();
				break;

			case Action_Cancel:
				module.handleNotificationCancel();
				break;
		}
	}

	public final void post() {
		Builder builder = createBasicBuilder(NotificationId);

		RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notification__foreground_service);
		contentView.setTextViewText(R.id.TV_Label, "Here be label");

		contentView.setOnClickPendingIntent(R.id.IV_Actionable, createPendingIntent(NotificationId, Action_ActionA));

		builder.setCustomContentView(contentView);
		builder.setAutoCancel(false);
		builder.setSmallIcon(R.drawable.icon__location);
		builder.setOngoing(true);
		Notification notification = postNotification(builder, NotificationId);
		cyborg.startForegroundService(Service_KeepAlive.class, NotificationId, notification);
	}
}
