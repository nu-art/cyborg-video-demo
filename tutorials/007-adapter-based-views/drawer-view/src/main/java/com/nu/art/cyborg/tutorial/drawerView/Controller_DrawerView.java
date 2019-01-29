package com.nu.art.cyborg.tutorial.drawerView;

import android.widget.TextView;

import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;

public class Controller_DrawerView
	extends CyborgController {

	@ViewIdentifier(
		viewId = R.id.TV_State,
		listeners = {
			ViewListener.OnClick,
			ViewListener.OnLongClick
		})
	private TextView state;

	private Module_DrawerView module;

	public Controller_DrawerView() {
		super(R.layout.controller__drawer_view);
	}
}

