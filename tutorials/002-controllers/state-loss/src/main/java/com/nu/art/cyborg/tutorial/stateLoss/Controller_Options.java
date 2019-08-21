package com.nu.art.cyborg.tutorial.stateLoss;

import android.view.View;
import android.widget.TextView;

import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;

public class Controller_Options
	extends CyborgController {

	@ViewIdentifier(
		viewId = {R.id.TV_Option1},
		listeners = ViewListener.OnClick)
	private TextView[] options;

	public Controller_Options() {
		super(R.layout.controller__options);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.TV_Option1:
				createLayerBuilder().setControllerType(Controller_SimpleStateLoss.class).push();
				break;
		}
	}
}

