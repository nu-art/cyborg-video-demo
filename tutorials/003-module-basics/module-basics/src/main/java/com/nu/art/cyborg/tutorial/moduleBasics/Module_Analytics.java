package com.nu.art.cyborg.tutorial.moduleBasics;

import android.Manifest.permission;
import android.content.Context;

import com.nu.art.cyborg.annotations.ModuleDescriptor;
import com.nu.art.cyborg.core.CyborgModule;

/**
 * Created by TacB0sS on 15-May 2017.
 */

// EXTREMELY SIMPLIFIED
@ModuleDescriptor(usesPermissions = {permission.INTERNET})
public class Module_Analytics
	extends CyborgModule {

	private String analyticsKey;

	private Module_Analytics() {}

	@Override
	protected void init() {
		Context c = getApplicationContext();
		Module_Analytics module = getModule(Module_Analytics.class);
		String isThisTheSameInstance = module == this ? "same" : "different";
		logInfo("isThisTheSameInstance: " + isThisTheSameInstance);
	}

	// We need the analytics key to configure the sdk
	void setAnalyticsKey(String analyticsKey) {
		this.analyticsKey = analyticsKey;
	}

	// EXTREMELY SIMPLIFIED
	public final void sendEvent(String event) {
		logDebug("sending event: " + event);
		// here we can call any service/s we'd like to publish our event to.
	}
}
