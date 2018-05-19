package com.nu.art.cyborg.tutorial.infraExample;

import com.nu.art.cyborg.core.CyborgController;
import com.nu.art.cyborg.core.CyborgModule;
import com.nu.art.reflection.tools.ReflectiveTools;

/**
 * Created by tacb0ss on 18/05/2018.
 */

public class Module_Examples
	extends CyborgModule {

	public interface UseCaseExample {

		String getName();

		Class<? extends CyborgController> getControllerType();
	}

	@Override
	protected void init() {
	}

	private UseCaseExample[] examples;

	public final <T extends Enum<?> & UseCaseExample> void setExample(Class<T> examplesType) {
		T[] examples = ReflectiveTools.getEnumValues(examplesType);
		this.examples = new UseCaseExample[examples.length];
		System.arraycopy(examples, 0, this.examples, 0, examples.length);
	}

	public UseCaseExample[] getExamples() {
		return examples;
	}
}
