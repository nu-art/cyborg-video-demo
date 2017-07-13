package com.nu.art.cyborg.tutorial.controllerCommunication;

import android.view.View;
import android.widget.TextView;

import com.nu.art.core.generics.Processor;
import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;

/**
 * Created by TacB0sS on 12-May 2017.
 */

public class Controller_Communicate3
		extends CyborgController
		implements OnClickedController2 {

	// Inject view and set an OnLongClickListener and an OnClickListener.
	@ViewIdentifier(viewId = R.id.TV_Communicate,
									listeners = {
											ViewListener.OnLongClick,
											ViewListener.OnClick
									})
	TextView textView;

	public Controller_Communicate3() {
		super(R.layout.controller__communicate);
	}

	@Override
	protected void onCreate() {
		textView.setText("Controller 3");
		super.onCreate();
	}

	@Override
	public void onClick(View v) {
		dispatchEvent("controller 3 clicked", OnClickedController3.class, new Processor<OnClickedController3>() {
			@Override
			public void process(OnClickedController3 listener) {
				listener.onClickController3();
			}
		});

		super.onClick(v);
	}

	@Override
	public boolean onLongClick(View v) {
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public void onClickController2() {
		String text = textView.getText() + "2";
		textView.setText(text);
	}
}
