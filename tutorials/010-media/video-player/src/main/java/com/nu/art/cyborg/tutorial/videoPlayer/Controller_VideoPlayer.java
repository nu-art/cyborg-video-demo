package com.nu.art.cyborg.tutorial.videoPlayer;

import android.view.View;
import android.widget.TextView;

import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;
import com.nu.art.cyborg.media.CyborgMediaPlayer;
import com.nu.art.cyborg.media.CyborgMediaPlayer.MediaPlayerListenerImpl;
import com.nu.art.cyborg.media.CyborgMediaPlayer.PlayerState;
import com.nu.art.cyborg.ui.views.VideoView;

public class Controller_VideoPlayer
	extends CyborgController {

	@ViewIdentifier(viewId = R.id.VV_VideoView)
	private VideoView videoView;

	@ViewIdentifier(
		viewId = R.id.TV_State,
		listeners = {
			ViewListener.OnClick,
		})
	private TextView state;

	private Module_VideoPlayer module;
	private CyborgMediaPlayer player;

	public Controller_VideoPlayer() {
		super(R.layout.controller__video_player);
	}

	@Override
	protected void onCreate() {
		player = module.getPlayer();
		videoView.setMediaPlayer(player);
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
			      .setListener(new MediaPlayerListenerImpl() {
				      @Override
				      public void onPrepared() {
					      renderUI();
				      }

				      @Override
				      public void onPaused() {
					      renderUI();
				      }

				      @Override
				      public void onPlaying() {
					      renderUI();
				      }
			      })
			      .prepare();

		if (player.isState(PlayerState.Preparing))
			return;

		if (player.isPlaying())
			player.pause();
		else
			player.play();
	}

	@Override
	protected void onPause() {
		if (player.isPlaying())
			player.pause();
	}

	@Override
	protected void onDestroy() {
		if (player.isAlive())
			player.dispose();
	}
}

