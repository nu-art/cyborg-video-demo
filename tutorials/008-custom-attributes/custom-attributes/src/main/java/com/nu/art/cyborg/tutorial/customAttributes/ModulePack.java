package com.nu.art.cyborg.tutorial.customAttributes;

import com.nu.art.cyborg.modules.AttributeModule;
import com.nu.art.cyborg.tutorial.customAttributes.CustomDottedView.CustomDottedViewSetter;
import com.nu.art.modular.core.ModulesPack;

/**
 * Created by TacB0sS on 15-May 2017.
 */

@SuppressWarnings("unchecked")
public class ModulePack
		extends ModulesPack {

	private static final Class[] ModuleClasses = {
			AttributeModule.class
	};

	ModulePack() {
		super(ModuleClasses);
	}

	@Override
	protected void init() {
		getModule(AttributeModule.class).registerAttributesSetter(CustomDottedViewSetter.class);
	}
}
