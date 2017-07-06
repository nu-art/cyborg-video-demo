package com.nu.art.cyborg.tutorial.moduleCommunication;

import com.art.nu.cyborg.login_module.LoginModule.OnLoginModuleListener;
import com.nu.art.core.generics.Processor;
import com.nu.art.cyborg.annotations.ModuleDescriptor;
import com.nu.art.cyborg.core.CyborgModule;

/**
 * Created by TacB0sS on 19-May 2017.
 */

@ModuleDescriptor
public class AppModule
		extends CyborgModule
		implements OnLoginModuleListener {

	public String getToken() {
		return token;
	}

	public interface OnLoginListener {

		void onLoginCompleted();
	}

	private String token;

	@Override
	protected void init() {

	}

	public void onLoginCompleted(String token) {
		logInfo("login completed");
		this.token = token;

		dispatchEvent("login completed", OnLoginListener.class, new Processor<OnLoginListener>() {
			@Override
			public void process(OnLoginListener listener) {
				listener.onLoginCompleted();
			}
		});
	}
}
