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

public class Controller_Communicate2
	extends CyborgController
	implements OnClickedController1, OnClickedController3 {

	// Inject view and set an OnLongClickListener and an OnClickListener.
	@ViewIdentifier(viewId = R.id.TV_Communicate,
	                listeners = {
		                ViewListener.OnLongClick,
		                ViewListener.OnClick
	                })
	TextView textView;

	public Controller_Communicate2() {
		super(R.layout.controller__communicate);
	}

	@Override
	protected void onCreate() {
		textView.setText("Controller 2");
		super.onCreate();
	}

	@Override
	public void onClick(View v) {
		dispatchEvent("controller 2 clicked", new Processor<OnClickedController2>() {
			@Override
			public void process(OnClickedController2 listener) {
				listener.onClickController2();
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
	public void onClickController1() {
		String text = textView.getText() + "1";
		textView.setText(text);
	}

	@Override
	public void onClickController3() {
		String text = textView.getText() + "3";
		textView.setText(text);
	}
}
