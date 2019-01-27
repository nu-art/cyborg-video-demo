package com.nu.art.cyborg.tutorial.stackOverview;

import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;
import com.nu.art.cyborg.core.CyborgStackController;

public class Controller_StackConfig
	extends CyborgController {

	@ViewIdentifier(
		viewId = R.id.CB_KeepInStack,
		listeners = {
			ViewListener.OnClick,
		})
	private CheckBox cbkeepInStack;

	@ViewIdentifier(
		viewId = R.id.TV_ClearAll,
		listeners = {
			ViewListener.OnClick,
		})
	private TextView tvClearAll;

	@ViewIdentifier(
		viewId = R.id.TV_ClearToTag,
		listeners = {
			ViewListener.OnClick,
		})
	private TextView tvClearToTag;

	@ViewIdentifier(
		viewId = R.id.SP_Tags,
		listeners = {
			ViewListener.OnItemSelected,
		})
	private Spinner tags;

	private boolean keepInStack;
	private String tag;

	public boolean isKeepInStack() {
		return keepInStack;
	}

	public Controller_StackConfig() {
		super(R.layout.controller__stack_config);
	}

	@Override
	protected void onCreate() {
		super.onCreate();
		keepInStack = cbkeepInStack.isChecked();
	}

	@Override
	public void onClick(View v) {
		CyborgStackController stack = getStack();
		switch (v.getId()) {
			case R.id.CB_KeepInStack:
				keepInStack = cbkeepInStack.isChecked();
				break;

			case R.id.TV_ClearAll:
				stack.clear();
				stack.createLayerBuilder().setControllerType(Controller_A.class).build();
				break;

			case R.id.TV_ClearToTag:
				stack.popUntil(tag);
				break;
		}
	}
}

