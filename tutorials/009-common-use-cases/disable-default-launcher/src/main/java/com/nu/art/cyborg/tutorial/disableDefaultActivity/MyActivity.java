package com.nu.art.cyborg.tutorial.disableDefaultActivity;

import com.nu.art.cyborg.core.CyborgActivity;

/**
 * Created by TacB0sS on 15-Jul 2017.
 */

public class MyActivity
	extends CyborgActivity {

	@Override
	protected void onCreateImpl() {
		setContentView(R.layout.activity__my_layout);
	}
}
