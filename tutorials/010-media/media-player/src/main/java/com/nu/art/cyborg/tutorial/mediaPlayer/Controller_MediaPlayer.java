package com.nu.art.cyborg.tutorial.mediaPlayer;

import android.view.View;
import android.widget.TextView;

import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;
import com.nu.art.cyborg.media.CyborgMediaPlayer;
import com.nu.art.cyborg.media.CyborgMediaPlayer.MediaPlayerListener;
import com.nu.art.cyborg.media.CyborgMediaPlayer.MediaPlayerListenerImpl;
import com.nu.art.cyborg.media.CyborgMediaPlayer.PlayerState;

public class Controller_MediaPlayer
	extends CyborgController {

	@ViewIdentifier(
		viewId = R.id.TV_PlayPause,
		listeners = {
			ViewListener.OnClick
		})
	private TextView playPause;

	private Module_MediaPlayer module;
	private CyborgMediaPlayer player;

	public Controller_MediaPlayer() {
		super(R.layout.controller__media_player);
	}

	@Override
	protected void onCreate() {
		this.player = module.getPlayer();
	}

	@Override
	protected void onResume() {
		renderUI();
	}

	@Override
	public void onClick(View v) {
		if (player.isState(PlayerState.Idle))
			player.createBuilder()
			      .setUri("https://www.myinstants.com/media/sounds/goodbadugly-whistle-long.mp3")
			      .setAutoPlay(true)
			      .prepare();

		if (player.isState(PlayerState.Preparing))
			return;

		if (player.isPlaying())
			player.pause();
		else
			player.play();
	}

	@Override
	protected void render() {
		playPause.setText(player.isPlaying() ? "Pause" : "Play");
	}
}

