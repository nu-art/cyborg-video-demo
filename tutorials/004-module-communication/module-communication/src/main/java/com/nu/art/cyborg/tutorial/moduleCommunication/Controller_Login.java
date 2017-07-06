package com.nu.art.cyborg.tutorial.moduleCommunication;

import android.view.View;
import android.widget.TextView;

import com.art.nu.cyborg.login_module.LoginModule;
import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;
import com.nu.art.cyborg.tutorial.moduleCommunication.AppModule.OnLoginListener;

/**
 * Created by TacB0sS on 20-May 2017.
 */

public class Controller_Login
		extends CyborgController
		implements OnLoginListener {

	// Inject view and set an OnLongClickListener and an OnClickListener.
	@ViewIdentifier(viewId = R.id.LoginView,
									listeners = {
											ViewListener.OnClick
									})
	private TextView loginTextView;

	public Controller_Login() {
		super(R.layout.controller__login);
	}

	@Override
	public void onClick(View v) {
		getModule(LoginModule.class).login("userName", "pass");
		super.onClick(v);
	}

	@Override
	protected void onResume() {
		super.onResume();
		loginTextView.setText("Login");
	}

	@Override
	public void onLoginCompleted() {
		postOnUI(new Runnable() {
			@Override
			public void run() {
				logInfo("Login completed: " + getModule(AppModule.class).getToken());
				loginTextView.setText("Login completed");
			}
		});
	}
}
