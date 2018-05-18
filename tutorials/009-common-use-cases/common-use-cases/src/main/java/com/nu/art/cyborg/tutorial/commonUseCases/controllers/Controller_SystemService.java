package com.nu.art.cyborg.tutorial.commonUseCases.controllers;

import android.net.wifi.WifiInfo;

import com.nu.art.cyborg.core.CyborgController;
import com.nu.art.cyborg.tutorial.commonUseCases.Module_CommonUseCase;
import com.nu.art.cyborg.tutorial.commonUseCases.R;

/**
 * Created by TacB0sS on 15-Jul 2017.
 */

public class Controller_SystemService
	extends CyborgController {

	public Controller_SystemService() {
		super(R.layout.controller__permissions);
	}

	@Override
	protected void onCreate() {
		WifiInfo wifiInfo = getModule(Module_CommonUseCase.class).doSomethingWithTheWifiManager();
		logInfo("WifiInfo: " + wifiInfo);
	}
}
