package com.nu.art.cyborg.tutorial.videoPlayer;

import android.widget.TextView;

import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;

public class Controller_VideoPlayer
	extends CyborgController {

	@ViewIdentifier(
		viewId = R.id.TV_State,
		listeners = {
			ViewListener.OnClick,
			ViewListener.OnLongClick
		})
	private TextView state;

	private Module_VideoPlayer module;

	public Controller_VideoPlayer() {
		super(R.layout.controller__video_player);
	}
}

