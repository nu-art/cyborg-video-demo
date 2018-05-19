package com.nu.art.cyborg.tutorial.commonUseCases;

import com.nu.art.cyborg.core.CyborgController;
import com.nu.art.cyborg.tutorial.commonUseCases.controllers.Controller_ActivityForActivityResult;
import com.nu.art.cyborg.tutorial.commonUseCases.controllers.Controller_BroadcastReceiver;
import com.nu.art.cyborg.tutorial.commonUseCases.controllers.Controller_GetResources;
import com.nu.art.cyborg.tutorial.commonUseCases.controllers.Controller_HandlersAndThreads;
import com.nu.art.cyborg.tutorial.commonUseCases.controllers.Controller_Keyboard;
import com.nu.art.cyborg.tutorial.commonUseCases.controllers.Controller_Permissions;
import com.nu.art.cyborg.tutorial.commonUseCases.controllers.Controller_StartService;
import com.nu.art.cyborg.tutorial.commonUseCases.controllers.Controller_SystemService;
import com.nu.art.cyborg.tutorial.infraExample.Module_Examples.UseCaseExample;

enum Examples
	implements UseCaseExample {
	Permissions(Controller_Permissions.class),
	ActivityForResult(Controller_ActivityForActivityResult.class),
	SystemService(Controller_SystemService.class),
	HandlersAndThreads(Controller_HandlersAndThreads.class),
	RegisterBroadcastReceiver(Controller_BroadcastReceiver.class),
	StartService(Controller_StartService.class),
	GetResources(Controller_GetResources.class),
	Keyboard(Controller_Keyboard.class),;

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

