package com.nu.art.cyborg.tutorial.stateLoss;

import android.view.View;
import android.widget.TextView;

import com.nu.art.cyborg.annotations.Restorable;
import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;
import com.nu.art.cyborg.core.CyborgModule;

import java.util.UUID;

public class Controller_SimpleStateLoss
	extends CyborgController {

	@ViewIdentifier(
		viewId = R.id.TV_Generate,
		listeners = {
			ViewListener.OnClick,
			ViewListener.OnLongClick
		})
	private TextView state;

	@ViewIdentifier(
		viewId = R.id.TV_Data)
	private TextView data;

	@Restorable
	private String stringToRestore;

	@Restorable
	private int intToRestore;

	public Controller_SimpleStateLoss() {
		super(R.layout.controller__simple_state_loss);
	}

	@Override
	public void onClick(View v) {
		stringToRestore = UUID.randomUUID().toString();
		intToRestore = CyborgModule.getNextRandomPositiveShort();
		printState("onClick");
		updateUI();
	}

	private void updateUI() {
		data.setText("" + stringToRestore + " - " + intToRestore);
	}

	@Override
	public boolean onLongClick(View v) {
		stringToRestore = null;
		intToRestore = 0;
		printState("onLongClick");
		updateUI();
		return false;
	}

	private void printState(String state) {
		logDebug(state + " - stringToRestore: " + stringToRestore);
		logDebug(state + " - intToRestore: " + intToRestore);
	}

	@Override
	protected void onCreate() {
		printState("onCreate");
	}

	@Override
	protected void onResume() {
		printState("onResume");
		updateUI();
	}

	@Override
	protected void onPause() {
		printState("onPause");
	}

	@Override
	protected void onDestroy() {
		printState("onDestroy");
	}
}

