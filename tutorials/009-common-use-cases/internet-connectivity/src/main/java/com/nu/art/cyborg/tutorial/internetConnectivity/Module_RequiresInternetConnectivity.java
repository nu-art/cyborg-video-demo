package com.nu.art.cyborg.tutorial.internetConnectivity;

import com.nu.art.cyborg.core.CyborgModule;
import com.nu.art.cyborg.modules.InternetConnectivityModule;
import com.nu.art.cyborg.modules.InternetConnectivityModule.InternetConnectivityListener;

public class Module_RequiresInternetConnectivity
	extends CyborgModule
	implements InternetConnectivityListener {

	private Module_RequiresInternetConnectivity() {}

	@Override
	protected void init() {
		performAction();
	}

	@Override
	public void onInternetConnectivityChanged() {
		performAction();
	}

	private void performAction() {
		if (!getModule(InternetConnectivityModule.class).isConnected())
			return;

		logInfo("perform action!!");
	}
}
