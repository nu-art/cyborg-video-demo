package com.nu.art.cyborg.tutorial.helloWorld;

import android.view.View;
import android.widget.TextView;

import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;

/**
 * Created by TacB0sS on 12-May 2017.
 */

public class Controller_HelloWorld
		extends CyborgController {

	// Inject view and set an OnLongClickListener and an OnClickListener.
	@ViewIdentifier(viewId = R.id.HelloWorld,
									listeners = {
											ViewListener.OnLongClick,
											ViewListener.OnClick
									})
	TextView helloWorldTextView;

	public Controller_HelloWorld() {
		super(R.layout.controller__hello_world);
	}

	@Override
	public void onClick(View v) {
		String text = helloWorldTextView.getText() + "-";
		helloWorldTextView.setText(text);
		super.onClick(v);
	}

	@Override
	public boolean onLongClick(View v) {
		String text = helloWorldTextView.getText() + "+";
		helloWorldTextView.setText(text);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
}
