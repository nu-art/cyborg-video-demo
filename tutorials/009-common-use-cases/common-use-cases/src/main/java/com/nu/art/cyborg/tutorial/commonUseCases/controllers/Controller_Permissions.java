package com.nu.art.cyborg.tutorial.commonUseCases.controllers;

import android.Manifest.permission;
import android.view.View;
import android.widget.TextView;

import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;
import com.nu.art.cyborg.modules.PermissionModule;
import com.nu.art.cyborg.modules.PermissionModule.PermissionResultListener;
import com.nu.art.cyborg.tutorial.commonUseCases.R;

import java.util.Arrays;

/**
 * Created by TacB0sS on 15-Jul 2017.
 */

public class Controller_Permissions
	extends CyborgController
	implements PermissionResultListener {

	private static final int RequestCode = 30405;

	private static final String[] permissions = {
		permission.CAMERA,
		permission.READ_CONTACTS
	};

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
		getModule(PermissionModule.class).requestPermission(RequestCode, permissions);
	}

	@Override
	protected void render() {
		boolean hasStillRejectedPermissions = getModule(PermissionModule.class).getRejectedPermissions(permissions).length != 0;
		String state = hasStillRejectedPermissions ? "Ask for permissions" : "All Permissions granted";
		clickHere.setText(state);
	}

	@Override
	public void onPermissionsRejected(int requestCode, String[] permissions) {
		if (requestCode != RequestCode)
			return;

		logError("permissions rejected: " + Arrays.toString(permissions));
		renderUI();
	}

	@Override
	public void onAllPermissionsGranted(int requestCode) {
		if (requestCode != RequestCode)
			return;

		logInfo("Permissions granted");
		renderUI();
	}
}
