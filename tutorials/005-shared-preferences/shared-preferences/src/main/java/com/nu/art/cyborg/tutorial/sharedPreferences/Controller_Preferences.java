package com.nu.art.cyborg.tutorial.sharedPreferences;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;

/**
 * Created by TacB0sS on 20-May 2017.
 */

public class Controller_Preferences
	extends CyborgController
	implements TestPrefsModule.TestPrefsModuleListener {

	@ViewIdentifier(
			viewId = R.id.btn_Clear,
			listeners = {
					ViewListener.OnClick,
			})
	private Button btnClearPrefs;

	@ViewIdentifier(
			viewId = R.id.btn_Save,
			listeners = {
					ViewListener.OnClick,
			})
	private Button btnSavePref;

	@ViewIdentifier(
			viewId = R.id.edit_InputValue)
	private EditText editInputValues;

	@ViewIdentifier(
			viewId = R.id.txt_SavedValue)
	private TextView txtSavedValue;

	public Controller_Preferences() {
		super(R.layout.controller__preferences);
	}

	@Override
	protected void onResume() {
		super.onResume();
		txtSavedValue.setText(getModule(TestPrefsModule.class).getString());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_Clear:
				getModule(TestPrefsModule.class).clearStorage();
				break;

			case R.id.btn_Save:
				getModule(TestPrefsModule.class).updateString(editInputValues.getText().toString());
				break;
		}
	}

	@Override
	public void onStringValueChanged(String updatedValue) {
		txtSavedValue.setText(updatedValue);
	}
}
