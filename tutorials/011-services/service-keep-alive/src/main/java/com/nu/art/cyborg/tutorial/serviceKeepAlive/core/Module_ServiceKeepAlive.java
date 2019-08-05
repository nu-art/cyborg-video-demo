package com.nu.art.cyborg.tutorial.serviceKeepAlive.core;

import android.app.PendingIntent;
import android.os.Handler;

import com.nu.art.core.tools.DateTimeTools;
import com.nu.art.cyborg.common.interfaces.StringResourceResolver;
import com.nu.art.cyborg.common.utils.BootStarterReceiver.OnBootCompletedListener;
import com.nu.art.cyborg.core.CyborgBuilder;
import com.nu.art.cyborg.core.CyborgModule;
import com.nu.art.cyborg.core.abs.Cyborg;
import com.nu.art.cyborg.core.modules.ThreadsModule;
import com.nu.art.cyborg.modules.notifications.NotificationsModule;
import com.nu.art.cyborg.modules.scheduler.Task;
import com.nu.art.cyborg.modules.scheduler.TaskScheduler;

public class Module_ServiceKeepAlive
	extends CyborgModule
	implements OnBootCompletedListener {

	private Handler reviveHandler;

	private Module_ServiceKeepAlive() {}

	@Override
	protected void init() {
		reviveHandler = getModule(ThreadsModule.class).getDefaultHandler("Revive-Thread");
		keepAlive();
		postNotificationAndStartService();
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

	public void killService() {
		cyborg.stopService(Service_KeepAlive.class);
	}

	public void handleNotificationCancel() {
		toastLong(new StringResourceResolver() {
			@Override
			public String getString(Cyborg cyborg) {
				return "Toast notification swiped";
			}
		});
	}

	public void keepAlive() {
		reviveHandler.removeCallbacks(reviveRunnable);
		reviveHandler.post(reviveRunnable);
	}

	@Override
	public void onBootCompleted() {
		keepAlive();
	}

	private Runnable reviveRunnable = new Runnable() {
		PendingIntent lastIntent;

		@Override
		public void run() {
			TaskScheduler taskScheduler = getModule(TaskScheduler.class);
			if (lastIntent != null)
				taskScheduler.cancel(lastIntent);

			lastIntent = taskScheduler.scheduleTask(ReviveTask.class, null, ReviveTask.TaskId, System.currentTimeMillis() + 6 * DateTimeTools.Second);
			reviveHandler.postDelayed(this, 4 * DateTimeTools.Second);
		}
	};

	public static class ReviveTask
		extends Task<String> {

		public static final short TaskId = (short) "ReviveTask".hashCode();

		protected ReviveTask() { super(String.class); }

		@Override
		protected void execute(String s) {
			CyborgBuilder.getModule(Module_ServiceKeepAlive.class).keepAlive();
		}
	}
}
