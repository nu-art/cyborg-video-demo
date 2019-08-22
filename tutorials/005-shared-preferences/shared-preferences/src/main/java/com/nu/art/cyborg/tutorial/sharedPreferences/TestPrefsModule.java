package com.nu.art.cyborg.tutorial.sharedPreferences;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.nu.art.core.generics.Processor;
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
    private static final String STORAGE_NAME = "my-new-storage";

    interface TestPrefsModuleListener{
        void onStringValueChanged(String updatedValue);
    }

	public enum TestEnum {
		DefaultValue,
		Value1,
		Value2,
		Value3
	}

	private IntegerPreference integer = new IntegerPreference("integer", -10);

	private StringPreference string = new StringPreference("string", "we have no value").setStorageGroup(STORAGE_NAME);

	private BooleanPreference bool = new BooleanPreference("bool", false).setExpires(DateTimeTools.Minute);

	private CustomPreference<HashMap> mapPref = new CustomPreference<>("pref-custom", HashMap.class, new HashMap());

	private EnumPreference<TestEnum> enumPref = new EnumPreference<>("pref-enum", TestEnum.class, TestEnum.DefaultValue).setEnumType(TestEnum.class);

	@Override

	protected void init() {
		//	    clearStorage();
		//		string.set(30);
	}

	public void clearStorage(){
		getModule(PreferencesModule.class).getStorage(STORAGE_NAME).clear();

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				dispatchEvent("String Updated", TestPrefsModuleListener.class, new Processor<TestPrefsModuleListener>() {
					@Override
					public void process(TestPrefsModuleListener listener) {
						listener.onStringValueChanged(getString());
					}
				});
			}
		}, 150);
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

    void updateString(final String value) {
        this.string.set(value);

        dispatchEvent("String Updated", TestPrefsModuleListener.class, new Processor<TestPrefsModuleListener>() {
            @Override
            public void process(TestPrefsModuleListener listener) {
                listener.onStringValueChanged(value);
            }
        });
    }

    @NonNull
    String getString() {
        String returnValue = this.string.get();
        return TextUtils.isEmpty(returnValue) ? "" : returnValue;
    }
}
