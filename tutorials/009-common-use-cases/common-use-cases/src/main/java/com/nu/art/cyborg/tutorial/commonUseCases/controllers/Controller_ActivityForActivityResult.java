package com.nu.art.cyborg.tutorial.commonUseCases.controllers;

import android.view.View;
import android.widget.TextView;

import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;
import com.nu.art.cyborg.tutorial.commonUseCases.Module_CommonUseCase;
import com.nu.art.cyborg.tutorial.commonUseCases.Module_CommonUseCase.GotActivityResultListener;
import com.nu.art.cyborg.tutorial.commonUseCases.R;

/**
 * Created by TacB0sS on 15-Jul 2017.
 */

public class Controller_ActivityForActivityResult
		extends CyborgController
		implements GotActivityResultListener {

	@ViewIdentifier(viewId = R.id.TV_Button,
									listeners = ViewListener.OnClick)
	TextView clickHere;

	@ViewIdentifier(viewId = R.id.TV_Result)
	TextView result;

	public Controller_ActivityForActivityResult() {
		super(R.layout.controller__activity_for_result);
	}

	@Override
	public void onClick(View v) {
		getModule(Module_CommonUseCase.class).askActivityForResult();
	}

	@Override
	protected void onResume() {
		renderUI();
	}

	@Override
	public void onGotActivityResult() {
		renderUI();
	}

	@Override
	protected void render() {
		result.setText(getModule(Module_CommonUseCase.class).getActivityResult());
	}
}
