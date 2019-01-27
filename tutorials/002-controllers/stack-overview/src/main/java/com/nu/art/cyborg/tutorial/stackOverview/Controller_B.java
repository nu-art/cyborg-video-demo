package com.nu.art.cyborg.tutorial.stackOverview;

import android.view.View;
import android.widget.TextView;

import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;

public class Controller_B
	extends CyborgController {

	@ViewIdentifier(viewId = R.id.TV_Open,
	                listeners = ViewListener.OnClick)
	private TextView title;

	@ViewIdentifier(
		viewId = R.id.CV_StackConfig)
	private Controller_StackConfig stackConfig;

	public Controller_B() {
		super(R.layout.controller__b);
	}

	@Override
	public void onClick(View v) {
		createLayerBuilder().setControllerType(Controller_A.class).setKeepInStack(stackConfig.isKeepInStack()).build();
	}
}

