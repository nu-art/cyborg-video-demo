package com.nu.art.cyborg.tutorial.stateLoss;

import com.nu.art.cyborg.core.CyborgStackController;

public class Controller_StatelossStack
	extends CyborgStackController {

	public static boolean onetime = false;

	@Override
	protected void onResume() {
		if (onetime)
			return;

		onetime = true;
		postOnUI(10000, new Runnable() {
			@Override
			public void run() {
				createLayerBuilder().setControllerType(Controller_Options.class).push();
			}
		});
	}
}
