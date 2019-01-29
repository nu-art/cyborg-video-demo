package com.nu.art.cyborg.tutorial.activityForResult;

import android.view.View;
import android.widget.TextView;

import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;
import com.nu.art.cyborg.tutorial.activityForResult.Module_ActivityForResult.GotActivityResultListener;

public class Controller_ActivityForResult
	extends CyborgController
	implements GotActivityResultListener {

	@ViewIdentifier(
		viewId = R.id.TV_State,
		listeners = {
			ViewListener.OnClick,
			ViewListener.OnLongClick
		})
	private TextView state;

	private Module_ActivityForResult module;

	public Controller_ActivityForResult() {
		super(R.layout.controller__activity_for_result);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);

		module.performMyCustomAction();
	}

	@Override
	protected void onResume() {
		onGotActivityResult();
	}

	@Override
	public void onGotActivityResult() {
		state.setText(module.getActivityResult());
	}
}

