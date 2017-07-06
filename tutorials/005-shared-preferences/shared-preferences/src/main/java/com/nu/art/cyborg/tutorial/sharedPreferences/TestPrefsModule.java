package com.nu.art.cyborg.tutorial.sharedPreferences;

import com.nu.art.core.tools.DateTimeTools;
import com.nu.art.cyborg.core.CyborgModule;
import com.nu.art.cyborg.core.modules.PreferencesModule;
import com.nu.art.cyborg.core.modules.PreferencesModule.BooleanPreference;
import com.nu.art.cyborg.core.modules.PreferencesModule.IntegerPreference;
import com.nu.art.cyborg.core.modules.PreferencesModule.PreferencesStorage;
import com.nu.art.cyborg.core.modules.PreferencesModule.StringPreference;

/**
 * Created by TacB0sS on 22-May 2017.
 */

public class TestPrefsModule
		extends CyborgModule {

	private IntegerPreference integer;

	private StringPreference string;

	private BooleanPreference bool;

	@Override
	protected void init() {
		integer = getModule(PreferencesModule.class).new IntegerPreference("integer", -10);
		PreferencesStorage newStorage = new PreferencesStorage() {
			@Override
			public String getPreferencesName() {
				return "my-new-storage";
			}

			@Override
			public int getMode() {
				return 0;
			}
		};
		string = getModule(PreferencesModule.class).new StringPreference("string", "we have no value", newStorage);
		bool = getModule(PreferencesModule.class).new BooleanPreference("bool", false, DateTimeTools.Minute);
		getModule(PreferencesModule.class).dropPreferences(newStorage);

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
