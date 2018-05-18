package com.nu.art.cyborg.tutorial.moduleBasics;

import android.Manifest.permission;
import android.content.Context;

import com.nu.art.cyborg.annotations.ModuleDescriptor;
import com.nu.art.cyborg.core.CyborgModule;

/**
 * Created by TacB0sS on 15-May 2017.
 */
@ModuleDescriptor(usesPermissions = {permission.INTERNET})
public class BasicAnalyticsModule
	extends CyborgModule {

	private String analyticsKey;

	@Override
	protected void init() {
		Context c = getApplicationContext();
		BasicAnalyticsModule analyticsModuleFromInfra = getModule(BasicAnalyticsModule.class);
		String isThisTheSameInstance = analyticsModuleFromInfra == this ? "same" : "different";
		logInfo("isThisTheSameInstance: " + isThisTheSameInstance);
	}

	void setAnalyticsKey(String analyticsKey) {
		this.analyticsKey = analyticsKey;
	}
}
