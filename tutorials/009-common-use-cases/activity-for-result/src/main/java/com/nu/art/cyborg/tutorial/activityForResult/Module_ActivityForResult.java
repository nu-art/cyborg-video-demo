package com.nu.art.cyborg.tutorial.activityForResult;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.preference.PreferenceManager.OnActivityResultListener;

import com.nu.art.core.generics.Processor;
import com.nu.art.cyborg.core.ActivityStack.ActivityStackAction;
import com.nu.art.cyborg.core.CyborgActivityBridge;
import com.nu.art.cyborg.core.CyborgModule;
import com.nu.art.storage.StringPreference;

public class Module_ActivityForResult
	extends CyborgModule
	implements OnActivityResultListener {

	private static final int RequestCode = 30405;

	private StringPreference resultPreferences = new StringPreference("activityResult", "No Result");

	public void performMyCustomAction() {
		postActivityAction(new ActivityStackAction() {
			@Override
			public void execute(CyborgActivityBridge activityBridge) {
				Intent intent = new Intent();
				intent.setComponent(new ComponentName(getApplicationContext(), Activity_ResultProvider.class));

				try {
					activityBridge.startActivityForResult(intent, RequestCode);
				} catch (Exception e) {
					logError("Error opening activity", e);
					toastDebug("You need to install the \"disable-default-launcher\" app!!");
				}
			}
		});
	}

	public String getActivityResult() {
		return resultPreferences.get();
	}

	public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode != RequestCode)
			return false;

		if (resultCode == Activity.RESULT_OK) {
			String result = data.getStringExtra("result");
			resultPreferences.set(result);
			dispatchEvent("Got Activity Result: " + result, GotActivityResultListener.class, new Processor<GotActivityResultListener>() {
				@Override
				public void process(GotActivityResultListener listener) {
					listener.onGotActivityResult();
				}
			});
		}

		return true;
	}

	public interface GotActivityResultListener {

		void onGotActivityResult();
	}

	private Module_ActivityForResult() {}

	@Override
	protected void init() {
	}
}
