package com.nu.art.cyborg.tutorial.commonUseCases.controllers;

import com.nu.art.cyborg.common.interfaces.StringResourceResolver;
import com.nu.art.cyborg.core.CyborgController;
import com.nu.art.cyborg.core.KeyboardChangeListener.OnKeyboardVisibilityListener;
import com.nu.art.cyborg.core.abs.Cyborg;
import com.nu.art.cyborg.tutorial.commonUseCases.R;

/**
 * Created by TacB0sS on 15-Jul 2017.
 */

public class Controller_Keyboard
	extends CyborgController
	implements OnKeyboardVisibilityListener {

	public Controller_Keyboard() {
		super(R.layout.controller__keyboard);
	}

	@Override
	public void onKeyboardVisibilityChanged() {
		toastShort(new StringResourceResolver() {
			@Override
			public String getString(Cyborg cyborg) {
				return "Keyboard " + (isKeyboardVisible() ? "VISIBLE" : "GONE");
			}
		});
	}
}
