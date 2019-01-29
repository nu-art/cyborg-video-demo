package com.nu.art.cyborg.tutorial.keyboardStateListener;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;
import com.nu.art.cyborg.core.KeyboardChangeListener.OnKeyboardVisibilityListener;

public class Controller_KeyboardStateListener
	extends CyborgController
	implements OnKeyboardVisibilityListener {

	@ViewIdentifier(
		viewId = R.id.TV_State,
		listeners = {
			ViewListener.OnClick,
			ViewListener.OnLongClick
		})
	private TextView state;

	@ViewIdentifier(
		viewId = R.id.ET_Input)
	private EditText input;

	public Controller_KeyboardStateListener() {
		super(R.layout.controller__keyboard_state_listener);
	}

	@Override
	protected void onResume() {
		onKeyboardVisibilityChanged();
	}

	@Override
	public void onClick(View v) {
		if (isKeyboardVisible())
			hideKeyboard();
		else
			showKeyboard();
	}

	@Override
	public void onKeyboardVisibilityChanged() {
		state.setText("keyboard is " + (isKeyboardVisible() ? "visible" : "gone"));
	}
}

