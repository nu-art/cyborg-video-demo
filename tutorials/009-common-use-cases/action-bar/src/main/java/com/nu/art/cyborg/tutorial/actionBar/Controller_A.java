package com.nu.art.cyborg.tutorial.actionBar;

import android.view.View;
import android.widget.TextView;

import com.nu.art.core.generics.Processor;
import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;
import com.nu.art.cyborg.tutorial.actionBar.Controller_ActionBar.Model_ActionBar;

/**
 * Created by TacB0sS on 12-May 2017.
 */

public class Controller_A
	extends CyborgController {

	@ViewIdentifier(viewId = R.id.TV_Open,
	                listeners = ViewListener.OnClick)
	private TextView title;
	private Model_ActionBar model = new Model_ActionBar();

	public Controller_A() {
		super(R.layout.controller__a);
	}

	@Override
	protected void onCreate() {
		model.title = getClass().getSimpleName();
	}

	@Override
	protected void onResume() {
		super.onResume();
		dispatchEvent("update action bar", UpdateActionBar.class, new Processor<UpdateActionBar>() {
			@Override
			public void process(UpdateActionBar updateActionBar) {
				updateActionBar.updateActionBar(model);
			}
		});
	}

	@Override
	public void onClick(View v) {
		createLayerBuilder().setControllerType(Controller_B.class).setKeepInStack(false).push();
	}
}

