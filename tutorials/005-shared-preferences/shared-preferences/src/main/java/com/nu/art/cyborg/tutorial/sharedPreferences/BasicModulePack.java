package com.nu.art.cyborg.tutorial.sharedPreferences;

import android.os.Environment;

import com.nu.art.modular.core.ModulesPack;
import com.nu.art.storage.PreferencesModule;

import java.io.File;

/**
 * Created by TacB0sS on 15-May 2017.
 */

@SuppressWarnings("unchecked")
public class BasicModulePack
	extends ModulesPack {

	private static final Class[] ModuleClasses = {
		PreferencesModule.class,
		TestPrefsModule.class
	};

	BasicModulePack() {
		super(ModuleClasses);
	}

	@Override
	protected void init() {
		//		getModule(PreferencesModule.class).defineGroup("external", new File(Environment.getExternalStorageDirectory(), "external.json"));
	}
}
