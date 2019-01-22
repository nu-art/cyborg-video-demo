package com.nu.art.cyborg.tutorial.commonUseCases;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.preference.PreferenceManager.OnActivityResultListener;

import com.nu.art.core.generics.Processor;
import com.nu.art.cyborg.core.ActivityStack.ActivityStackAction;
import com.nu.art.cyborg.core.CyborgActivityBridge;
import com.nu.art.cyborg.core.CyborgModule;
import com.nu.art.storage.StringPreference;

/**
 * Created by TacB0sS on 15-Jul 2017.
 */

public class Module_CommonUseCase
	extends CyborgModule
	implements OnActivityResultListener {

	private static final int RequestCode = 30405;

	private StringPreference resultPreferences = new StringPreference("activityResult", "No Result");

	@Override
	protected void init() {

	}

	public final void askPermissions() {
	}

	public final WifiInfo doSomethingWithTheWifiManager() {
		// UI MUST not have anything to do with a system service... only a module!!

		WifiManager wifiManager = getSystemService(WifiService);
		return wifiManager.getConnectionInfo();
	}

	public void askActivityForResult() {
		postActivityAction(new ActivityStackAction() {
			@Override
			public void execute(CyborgActivityBridge activityBridge) {
				Intent intent = new Intent();
				intent.setComponent(new ComponentName("com.nu.art.cyborg.tutorial_009.disableDefaultLauncher", "com.nu.art.cyborg.tutorial.disableDefaultActivity.ActivityForResult"));

				try {
					activityBridge.startActivityForResult(intent, RequestCode);
				} catch (Exception e) {
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
}
