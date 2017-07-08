package com.nu.art.cyborg.tutorial.helloWorld;

import android.view.View;
import android.widget.TextView;

import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;
import com.nu.art.cyborg.core.CyborgStackController;
import com.nu.art.cyborg.core.animations.PredefinedStackTransitionAnimator;
import com.nu.art.cyborg.core.animations.PredefinedTransitions;
import com.nu.art.cyborg.core.animations.transitions.BaseTransition;

/**
 * Created by TacB0sS on 06-Jul 2017.
 */

public class Controller_HelloWorldStack
		extends CyborgController {

	@ViewIdentifier(viewId = R.id.TV_AddSecondLayer,
									listeners = {
											ViewListener.OnClick
									})
	TextView helloWorldTextView;

	public Controller_HelloWorldStack() {
		super(R.layout.controller__hello_world_stack);
	}

	@Override
	public void onClick(View v) {
		CyborgStackController stackController = getControllerById(R.id.Tag_RootStack);
		PredefinedStackTransitionAnimator animation = new PredefinedStackTransitionAnimator(getActivity(), PredefinedTransitions.Slide , BaseTransition.ORIENTATION_HORIZONTAL);
		stackController.createLayerBuilder().setControllerType(Controller_HelloWorld2.class).setStackTransitionAnimators(animation).setDuration(600).build();
	}
}
