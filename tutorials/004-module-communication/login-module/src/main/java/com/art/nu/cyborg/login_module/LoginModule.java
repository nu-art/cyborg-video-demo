package com.art.nu.cyborg.login_module;

import android.os.Handler;

import com.nu.art.core.generics.Processor;
import com.nu.art.cyborg.annotations.ModuleDescriptor;
import com.nu.art.cyborg.core.CyborgModule;

/**
 * Created by TacB0sS on 19-May 2017.
 */

@ModuleDescriptor
public class LoginModule
	extends CyborgModule {

	public interface OnLoginModuleListener {

		void onLoginCompleted(String token);
	}

	@Override
	protected void init() {

	}

	public void login(final String userName, final String pass) {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				final String token = "token-" + userName + "-" + pass;
				dispatchModuleEvent("On login completed: " + token, new Processor<OnLoginModuleListener>() {
					@Override
					public void process(OnLoginModuleListener listener) {
						listener.onLoginCompleted(token);
					}
				});
				// our login was successful!!!
			}
		}, 5000);
	}
}
