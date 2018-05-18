package com.nu.art.cyborg.tutorial.commonUseCases.controllers;

import android.view.View;
import android.widget.TextView;

import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;
import com.nu.art.cyborg.tutorial.commonUseCases.Module_CommonUseCase;
import com.nu.art.cyborg.tutorial.commonUseCases.Module_CommonUseCase.GotPermissionsResultListener;
import com.nu.art.cyborg.tutorial.commonUseCases.R;

/**
 * Created by TacB0sS on 15-Jul 2017.
 */

public class Controller_Permissions
	extends CyborgController
	implements GotPermissionsResultListener {

	@ViewIdentifier(viewId = R.id.TV_Button,
	                listeners = ViewListener.OnClick)
	TextView clickHere;

	public Controller_Permissions() {
		super(R.layout.controller__permissions);
	}

	@Override
	protected void onResume() {
		renderUI();
	}

	@Override
	public void onClick(View v) {
		getModule(Module_CommonUseCase.class).askPermissions();
	}

	@Override
	protected void render() {
		String state = getModule(Module_CommonUseCase.class).getRejectedPermissions().length != 0 ? "Ask for permissions" : "All Permissions granted";
		clickHere.setText(state);
	}

	@Override
	public void onGotPermissionsResult() {
		renderUI();
	}
}
