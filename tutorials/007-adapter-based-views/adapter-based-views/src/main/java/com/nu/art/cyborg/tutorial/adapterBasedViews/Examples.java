package com.nu.art.cyborg.tutorial.adapterBasedViews;

import com.nu.art.cyborg.core.CyborgController;
import com.nu.art.cyborg.tutorial.adapterBasedViews.controllers.Controller_Recycler_CursorDataModel;
import com.nu.art.cyborg.tutorial.adapterBasedViews.controllers.Controller_Recycler_ListDataModel;
import com.nu.art.cyborg.tutorial.adapterBasedViews.controllers.Controller_ViewPager;
import com.nu.art.cyborg.tutorial.infraExample.Module_Examples.UseCaseExample;

enum Examples
	implements UseCaseExample {
	CursorDataModel(Controller_Recycler_CursorDataModel.class),
	ListDataModel(Controller_Recycler_ListDataModel.class),
	ViewPager(Controller_ViewPager.class),
	//
	;
	final Class<? extends CyborgController> controllerType;

	Examples(Class<? extends CyborgController> controllerType) {this.controllerType = controllerType;}

	@Override
	public String getName() {
		return name();
	}

	@Override
	public Class<? extends CyborgController> getControllerType() {
		return controllerType;
	}

}

