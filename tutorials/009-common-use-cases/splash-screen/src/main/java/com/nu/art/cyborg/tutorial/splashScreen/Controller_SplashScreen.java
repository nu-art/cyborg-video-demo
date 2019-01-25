package com.nu.art.cyborg.tutorial.splashScreen;

import com.nu.art.cyborg.core.CyborgController;

public class Controller_SplashScreen
	extends CyborgController {

	public static final int Interval_Splash = 2000;
	private static boolean shownSplash = false;

	private Runnable nextScreen = new Runnable() {
		@Override
		public void run() {
			moveToNextScreen();
		}
	};

	public Controller_SplashScreen() {
		super(R.layout.controller__splash_screen);
	}

	@Override
	protected void onCreate() {
		if (shownSplash) {
			moveToNextScreen();
			return;
		}

		postOnUI(Interval_Splash, nextScreen);
	}

	private void moveToNextScreen() {
		shownSplash = true;
		createLayerBuilder()
			.setControllerType(Controller_Homepage.class)
			.build();
	}
}

