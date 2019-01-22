package com.nu.art.cyborg.tutorial.moduleBasics;

import android.view.View;
import android.widget.TextView;

import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;

/**
 * Created by TacB0sS on 12-May 2017.
 */

public class Controller_ModuleBasics
	extends CyborgController {

	@ViewIdentifier(viewId = R.id.TV_FireEvent,
	                listeners = {
		                ViewListener.OnLongClick,
		                ViewListener.OnClick
	                })
	private TextView textView;

	private Module_Analytics analyticsModule;

	public Controller_ModuleBasics() {
		super(R.layout.controller__module_basics);
	}

	@Override
	public boolean onLongClick(View v) {
		analyticsModule.sendEvent("Event-LongClick");
		return super.onLongClick(v);
	}

	@Override
	public void onClick(View v) {
		getModule(Module_Analytics.class).sendEvent("Event-Click");
		super.onClick(v);
	}
}

