package com.nu.art.cyborg.tutorial.sharedPreferences;

import com.nu.art.core.tools.DateTimeTools;
import com.nu.art.cyborg.core.CyborgModule;
import com.nu.art.storage.BooleanPreference;
import com.nu.art.storage.IntegerPreference;
import com.nu.art.storage.PreferencesModule;
import com.nu.art.storage.StringPreference;

/**
 * Created by TacB0sS on 22-May 2017.
 */

public class TestPrefsModule
	extends CyborgModule {

	private IntegerPreference integer = new IntegerPreference("integer", -10);

	private StringPreference string = new StringPreference("string", "we have no value").setStorageGroup("my-new-storage");

	private BooleanPreference bool = new BooleanPreference("bool", false).setExpires(DateTimeTools.Minute);

	@Override
	protected void init() {
		getModule(PreferencesModule.class).dropPreferences("my-new-storage");

		//		string.set(30);
	}

	public void updateBool(boolean bool) {
		this.bool.set(bool);
		this.bool.get();
	}

	public void updateInteger(int integer) {
		this.integer.set(integer);
	}

	public int getInteger() {
		return this.integer.get();
	}
}
