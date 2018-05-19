package com.nu.art.cyborg.tutorial.commonUseCases.controllers;

import android.Manifest.permission;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import com.nu.art.belog.BeLogged;
import com.nu.art.belog.FileLoggerClient;
import com.nu.art.core.tools.SizeTools;
import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;
import com.nu.art.cyborg.modules.PermissionModule;
import com.nu.art.cyborg.modules.PermissionModule.PermissionResultListener;
import com.nu.art.cyborg.tutorial.commonUseCases.Module_CommonUseCase;
import com.nu.art.cyborg.tutorial.commonUseCases.R;

import java.io.File;
import java.util.Arrays;

/**
 * Created by TacB0sS on 15-Jul 2017.
 */

public class Controller_FileLogger
	extends CyborgController
	implements PermissionResultListener {

	private FileLoggerClient fileLoggerClient = new FileLoggerClient();

	@ViewIdentifier(viewId = R.id.TV_Button,
	                listeners = ViewListener.OnClick)
	TextView clickHere;

	public Controller_FileLogger() {
		super(R.layout.controller__permissions);
	}

	@Override
	protected void onCreate() {
		logDebug("Adding File Logger - this line will NOT be in the file!!");

		File logFolder = new File(Environment.getExternalStorageDirectory() + "/Downloads/cyborg-logs");
		fileLoggerClient.set(logFolder, "log-file", 100 * SizeTools.KiloByte, 10);
		BeLogged.getInstance().addClient(fileLoggerClient);

		logInfo("Added File Logger - this line will be in the file!!");
	}

	@Override
	protected void onResume() {
		getModule(PermissionModule.class).requestPermission(100, permission.WRITE_EXTERNAL_STORAGE);
	}

	@Override
	public void onClick(View v) {
		getModule(Module_CommonUseCase.class).askPermissions();
	}

	@Override
	protected void render() {
	}

	@Override
	public void onPermissionsRejected(int requestCode, String[] rejected) {
		logInfo("Permissions rejected(" + requestCode + "): " + Arrays.toString(rejected));
		postOnUI(new Runnable() {
			@Override
			public void run() {
				logInfo("Closing controller");
				getActivity().onBackPressed();
			}
		});
	}

	@Override
	public void onAllPermissionsGranted(int requestCode) {
		logInfo("Permissions granted(" + requestCode + ")");
	}

	@Override
	protected void onDestroy() {
		BeLogged.getInstance().removeClient(fileLoggerClient);
	}
}
