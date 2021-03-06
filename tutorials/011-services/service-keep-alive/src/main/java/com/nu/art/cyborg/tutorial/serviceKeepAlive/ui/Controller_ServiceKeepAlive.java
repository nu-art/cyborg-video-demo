package com.nu.art.cyborg.tutorial.serviceKeepAlive.ui;

import android.view.View;
import android.widget.TextView;

import com.nu.art.core.exceptions.runtime.BadImplementationException;
import com.nu.art.core.exceptions.runtime.DontCallThisException;
import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.common.interfaces.StringResourceResolver;
import com.nu.art.cyborg.core.CyborgController;
import com.nu.art.cyborg.core.abs.Cyborg;
import com.nu.art.cyborg.tutorial.serviceKeepAlive.R;
import com.nu.art.cyborg.tutorial.serviceKeepAlive.core.Module_ServiceKeepAlive;
import com.nu.art.cyborg.tutorial.serviceKeepAlive.core.Notification_ForegroundService.NotificationClickListener;

public class Controller_ServiceKeepAlive
	extends CyborgController
	implements NotificationClickListener {

	@ViewIdentifier(
		viewId = R.id.TV_State,
		listeners = {
			ViewListener.OnClick,
			ViewListener.OnLongClick
		})
	private TextView state;

	@ViewIdentifier(
		viewId = R.id.TV_Crash,
		listeners = {
			ViewListener.OnClick,
		})
	private TextView crash;

	private Module_ServiceKeepAlive module;

	public Controller_ServiceKeepAlive() {
		super(R.layout.controller__service_keep_alive);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.TV_State:
				logInfo("raising keep alive service");
				module.postNotificationAndStartService();
				break;

			case R.id.TV_Crash:
				throw new DontCallThisException("Intentional!!");
		}
	}

	@Override
	public boolean onLongClick(View v) {
		logInfo("killing service");
		module.killService();
		return super.onLongClick(v);
	}

	@Override
	public void onClicked() {
		toastLong(new StringResourceResolver() {
			@Override
			public String getString(Cyborg cyborg) {
				return "Toast from Controller: notification clicked";
			}
		});
	}
}

