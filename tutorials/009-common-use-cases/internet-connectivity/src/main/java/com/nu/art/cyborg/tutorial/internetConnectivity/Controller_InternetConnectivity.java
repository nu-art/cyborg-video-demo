package com.nu.art.cyborg.tutorial.internetConnectivity;

import android.widget.TextView;

import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;
import com.nu.art.cyborg.modules.InternetConnectivityModule;
import com.nu.art.cyborg.modules.InternetConnectivityModule.InternetConnectivityListener;

public class Controller_InternetConnectivity
	extends CyborgController
	implements InternetConnectivityListener {

	@ViewIdentifier(
		viewId = R.id.TV_State,
		listeners = {
			ViewListener.OnClick,
			ViewListener.OnLongClick
		})
	private TextView state;

	public Controller_InternetConnectivity() {
		super(R.layout.controller__internet_connectivity);
	}

	@Override
	protected void onResume() {
		super.onResume();
		renderUI();
	}

	@Override
	protected void render() {
		super.render();
		boolean connected = getModule(InternetConnectivityModule.class).isConnected();
		state.setText((connected ? "" : "NOT ") + " Connected to Internet");
	}

	@Override
	public void onInternetConnectivityChanged() {
		renderUI();
	}
}

