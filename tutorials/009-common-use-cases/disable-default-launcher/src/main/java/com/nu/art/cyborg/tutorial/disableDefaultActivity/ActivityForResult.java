package com.nu.art.cyborg.tutorial.disableDefaultActivity;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import com.nu.art.cyborg.core.CyborgActivity;

import java.util.Random;

/**
 * Created by TacB0sS on 15-Jul 2017.
 */

public class ActivityForResult
		extends CyborgActivity {

	private static final String[] possibleResult = {
			"WOAH WOAH WE WAH",
			"YAHK SHEMASH",
			"GREEAAAT SUCCESSSS",
			"MY A LITTLE BROTHER BILLU",
			"YOU NEVER GET IT.. YOU NEVER GET IT...",
			"ONE A DAY, HE GOT A..  OUT OF THE CAGE...",
			"YOU BE MY WIFE... I TREAT YOU NICE...",
	};

	private TextView label;

	@Override
	protected void onCreateImpl() {
		setContentView(R.layout.activity__nothing_to_see_here);
		label = (TextView) findViewById(R.id.TV_Label);
		postOnUI(300, new TimeoutRunnable(5000, 300));
	}

	private class TimeoutRunnable
			implements Runnable {

		private final long startedAt;

		int totalInterval;

		int sleepInterval;

		public TimeoutRunnable(int totalInterval, int sleepInterval) {
			this.totalInterval = totalInterval;
			this.sleepInterval = sleepInterval;
			startedAt = System.currentTimeMillis();
		}

		@Override
		public void run() {
			if (startedAt + totalInterval < System.currentTimeMillis()) {
				Intent returnIntent = new Intent();
				returnIntent.putExtra("result", "Borat: " + possibleResult[new Random().nextInt(possibleResult.length)]);
				setResult(Activity.RESULT_OK, returnIntent);
				finish();
				return;
			}

			long elapsedInterval = System.currentTimeMillis() - startedAt;
			label.setText("Please wait..." + (int) ((float) totalInterval - elapsedInterval) / 1000);
			postOnUI(300, this);
		}
	}
}
