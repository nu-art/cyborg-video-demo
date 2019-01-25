package com.nu.art.cyborg.tutorial.actionBar;

import android.widget.TextView;

import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.core.CyborgController;

/**
 * Created by TacB0sS on 12-May 2017.
 */

public class Controller_ActionBar
	extends CyborgController
	implements UpdateActionBar {

	@ViewIdentifier(viewId = R.id.TV_Title)
	private TextView title;

	public Controller_ActionBar() {
		super(R.layout.controller__action_bar);
	}

	@Override
	public void updateActionBar(Model_ActionBar model) {
		title.setText(model.title);
	}

	public static class Model_ActionBar {

		public String title;
	}
}

