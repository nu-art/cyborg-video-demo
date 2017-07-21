package com.nu.art.cyborg.tutorial.commonUseCases;

import android.Manifest.permission;
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
import com.nu.art.cyborg.core.interfaces.OnSystemPermissionsResultListener;
import com.nu.art.cyborg.core.modules.PreferencesModule;
import com.nu.art.cyborg.core.modules.PreferencesModule.StringPreference;
import com.nu.art.cyborg.modules.PermissionModule;

/**
 * Created by TacB0sS on 15-Jul 2017.
 */

public class Module_CommonUseCase
		extends CyborgModule
		implements OnActivityResultListener, OnSystemPermissionsResultListener {

	private static final String[] permissions = {
			permission.CAMERA,
			permission.READ_CONTACTS
	};

	private static final int RequestCode = 30405;

	private StringPreference resultPreferences;

	@Override
	protected void init() {
		resultPreferences = getModule(PreferencesModule.class).new StringPreference("activityResult", "No Result");
	}

	public final void askPermissions() {
		getModule(PermissionModule.class).requestPermission(RequestCode, permissions);
	}

	public final String[] getRejectedPermissions() {
		return getModule(PermissionModule.class).getRejectedPermissions(permissions);
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

	@Override
	public boolean onPermissionsResult(int requestCode, String[] strings, int[] ints) {
		if (requestCode != RequestCode)
			return false;

		dispatchEvent("Got Permissions result", GotPermissionsResultListener.class, new Processor<GotPermissionsResultListener>() {
			@Override
			public void process(GotPermissionsResultListener listener) {
				listener.onGotPermissionsResult();
			}
		});

		return true;
	}

	public interface GotActivityResultListener {

		void onGotActivityResult();
	}

	public interface GotPermissionsResultListener {

		void onGotPermissionsResult();
	}
}
