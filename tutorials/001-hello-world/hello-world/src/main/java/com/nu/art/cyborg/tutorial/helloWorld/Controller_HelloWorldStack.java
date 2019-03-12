package com.nu.art.cyborg.tutorial.helloWorld;

import android.graphics.Color;
import android.view.View;

import com.nu.art.core.exceptions.runtime.ImplementationMissingException;
import com.nu.art.core.generics.Processor;
import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;
import com.nu.art.cyborg.core.CyborgStackController;
import com.nu.art.cyborg.core.stackTransitions.StackTransitions;
import com.nu.art.cyborg.core.stackTransitions.Transition;

/**
 * Created by TacB0sS on 06-Jul 2017.
 */

public class Controller_HelloWorldStack
	extends CyborgController {

	//	@ViewIdentifier(viewId = R.id.TV_AddSecondLayer,
	//									listeners = {
	//											ViewListener.OnClick,
	//											ViewListener.OnLongClick
	//									})
	//	TextView helloWorldTextView;

	@ViewIdentifier(viewId = {
		R.id.TV_AddSecondLayer1,
		R.id.TV_AddSecondLayer2
	},
	                listeners = {
		                ViewListener.OnClick,
		                ViewListener.OnLongClick
	                })
	View[] clickableViews;

	public Controller_HelloWorldStack() {
		super(R.layout.controller__hello_world_stack);
	}

	@Override
	public boolean onLongClick(View v) {
		CyborgStackController stackController = getControllerById(R.id.Tag_RootStack);
		stackController.createLayerBuilder().setControllerType(Controller_HelloWorld2.class).setDuration(2000).build();
		return true;
	}

	@Override
	public void onClick(View v) {
		Transition animation;
		switch (v.getId()) {
			case R.id.TV_AddSecondLayer1:
				animation = StackTransitions.Slide;
				break;

			case R.id.TV_AddSecondLayer2:
				animation = StackTransitions.SlideT2B;
				break;

			default:
				throw new ImplementationMissingException("Unhandled view click event...");
		}

		CyborgStackController stackController = getControllerById(R.id.Tag_RootStack);
		stackController.createLayerBuilder()
		               .setControllerType(Controller_HelloWorld2.class)
		               .setTransitions(animation)
		               .setTransitionDuration(600)
		               .setProcessor(new Processor<Controller_HelloWorld2>() {
			               @Override
			               public void process(Controller_HelloWorld2 controller_helloWorld2) {
				               controller_helloWorld2.getRootView()
				                                     .setBackgroundColor(Color.argb(255, UtilsRandom.nextInt(256), UtilsRandom.nextInt(256), UtilsRandom.nextInt(256)));
			               }
		               })
		               .build();
	}
}
