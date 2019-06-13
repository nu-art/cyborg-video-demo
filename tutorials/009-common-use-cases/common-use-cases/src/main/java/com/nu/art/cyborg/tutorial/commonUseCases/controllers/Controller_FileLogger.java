package com.nu.art.cyborg.tutorial.commonUseCases.controllers;

import android.Manifest.permission;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import com.nu.art.belog.BeConfig;
import com.nu.art.belog.BeLogged;
import com.nu.art.belog.loggers.FileLogger;
import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;
import com.nu.art.cyborg.core.loggers.AndroidLogger;
import com.nu.art.cyborg.modules.PermissionModule;
import com.nu.art.cyborg.modules.PermissionModule.PermissionResultListener;
import com.nu.art.cyborg.tutorial.commonUseCases.Module_CommonUseCase;
import com.nu.art.cyborg.tutorial.commonUseCases.R;

import java.io.File;
import java.util.Arrays;

import static com.nu.art.belog.loggers.FileLogger.Config_FastFileLogger;
import static com.nu.art.belog.loggers.FileLogger.LogConfig_FileLogger;

/**
 * Created by TacB0sS on 15-Jul 2017.
 */

public class Controller_FileLogger
	extends CyborgController
	implements PermissionResultListener {

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
		LogConfig_FileLogger.setFolder(logFolder.getAbsolutePath()).setFileName("log-file");
		BeLogged.getInstance().setConfig(AndroidLogger.Config_FastAndroidLogger.merge(Config_FastFileLogger));

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
		BeLogged.getInstance().setConfig(AndroidLogger.Config_FastAndroidLogger);
	}
}
