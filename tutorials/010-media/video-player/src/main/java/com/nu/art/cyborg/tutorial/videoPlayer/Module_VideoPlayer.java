package com.nu.art.cyborg.tutorial.videoPlayer;

import com.nu.art.cyborg.core.CyborgModule;
import com.nu.art.cyborg.media.CyborgMediaPlayer;

public class Module_VideoPlayer
	extends CyborgModule {

	private CyborgMediaPlayer player;

	private Module_VideoPlayer() {}

	@Override
	protected void init() {
		player = createModuleItem(CyborgMediaPlayer.class);
	}

	public CyborgMediaPlayer getPlayer() {
		return player;
	}
}
