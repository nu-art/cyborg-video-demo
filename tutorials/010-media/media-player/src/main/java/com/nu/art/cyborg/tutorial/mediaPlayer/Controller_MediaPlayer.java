package com.nu.art.cyborg.tutorial.mediaPlayer;

import android.widget.TextView;

import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;

public class Controller_MediaPlayer
	extends CyborgController {

	@ViewIdentifier(
		viewId = R.id.TV_State,
		listeners = {
			ViewListener.OnClick,
			ViewListener.OnLongClick
		})
	private TextView state;

	private Module_MediaPlayer module;

	public Controller_MediaPlayer() {
		super(R.layout.controller__media_player);
	}
}

