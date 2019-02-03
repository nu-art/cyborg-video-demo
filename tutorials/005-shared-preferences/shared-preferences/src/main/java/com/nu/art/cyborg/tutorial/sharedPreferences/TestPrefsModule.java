package com.nu.art.cyborg.tutorial.sharedPreferences;

import com.nu.art.core.tools.DateTimeTools;
import com.nu.art.cyborg.core.CyborgModule;
import com.nu.art.storage.BooleanPreference;
import com.nu.art.storage.CustomPreference;
import com.nu.art.storage.EnumPreference;
import com.nu.art.storage.IntegerPreference;
import com.nu.art.storage.PreferencesModule;
import com.nu.art.storage.StringPreference;

import java.util.HashMap;

/**
 * Created by TacB0sS on 22-May 2017.
 */

public class TestPrefsModule
	extends CyborgModule {

	public enum TestEnum {
		DefaultValue,
		Value1,
		Value2,
		Value3
	}

	private IntegerPreference integer = new IntegerPreference("integer", -10);

	private StringPreference string = new StringPreference("string", "we have no value").setStorageGroup("my-new-storage");

	private BooleanPreference bool = new BooleanPreference("bool", false).setExpires(DateTimeTools.Minute);

	private CustomPreference<HashMap> mapPref = new CustomPreference<>("pref-custom", new HashMap()).setItemType(HashMap.class);

	private EnumPreference<TestEnum> enumPref = new EnumPreference<>("pref-enum", TestEnum.class, TestEnum.DefaultValue).setEnumType(TestEnum.class);

	@Override

	protected void init() {

		getModule(PreferencesModule.class).getStorage("my-new-storage").clear();

		//		string.set(30);
	}

	public void addToMap(String key, String value) {
		HashMap map = this.mapPref.get();
		map.put(key, value);
		this.mapPref.set(map);
	}

	public void updateEnum(TestEnum value) {
		this.enumPref.set(value);
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
