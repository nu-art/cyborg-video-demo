package com.nu.art.cyborg.tutorial.mediaPlayer;

import com.nu.art.cyborg.core.CyborgModule;
import com.nu.art.cyborg.media.CyborgMediaPlayer;

public class Module_MediaPlayer
	extends CyborgModule {

	private CyborgMediaPlayer player;

	private Module_MediaPlayer() {}

	@Override
	protected void init() {
		player = instantiateModuleItem(CyborgMediaPlayer.class);
	}

	public CyborgMediaPlayer getPlayer() {
		return player;
	}
}
