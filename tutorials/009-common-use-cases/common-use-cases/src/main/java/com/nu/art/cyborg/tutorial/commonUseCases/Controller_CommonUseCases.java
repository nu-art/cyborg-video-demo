package com.nu.art.cyborg.tutorial.commonUseCases;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nu.art.core.interfaces.Getter;
import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgAdapter;
import com.nu.art.cyborg.core.CyborgController;
import com.nu.art.cyborg.core.CyborgRecycler;
import com.nu.art.cyborg.core.ItemRenderer;
import com.nu.art.cyborg.core.dataModels.DataModel;
import com.nu.art.cyborg.core.dataModels.ListDataModel;
import com.nu.art.cyborg.tutorial.commonUseCases.controllers.Controller_ActivityForActivityResult;
import com.nu.art.cyborg.tutorial.commonUseCases.controllers.Controller_BroadcastReceiver;
import com.nu.art.cyborg.tutorial.commonUseCases.controllers.Controller_GetResources;
import com.nu.art.cyborg.tutorial.commonUseCases.controllers.Controller_HandlersAndThreads;
import com.nu.art.cyborg.tutorial.commonUseCases.controllers.Controller_Keyboard;
import com.nu.art.cyborg.tutorial.commonUseCases.controllers.Controller_Permissions;
import com.nu.art.cyborg.tutorial.commonUseCases.controllers.Controller_StartService;
import com.nu.art.cyborg.tutorial.commonUseCases.controllers.Controller_SystemService;

/**
 * Created by TacB0sS on 20-May 2017.
 */

public class Controller_CommonUseCases
		extends CyborgController {

	enum UseCase {
		Permissions(Controller_Permissions.class),
		ActivityForResult(Controller_ActivityForActivityResult.class),
		SystemService(Controller_SystemService.class),
		HandlersAndThreads(Controller_HandlersAndThreads.class),
		RegisterBroadcastReceiver(Controller_BroadcastReceiver.class),
		StartService(Controller_StartService.class),
		GetResources(Controller_GetResources.class),
		Keyboard(Controller_Keyboard.class),;

		final Class<? extends CyborgController> controllerType;

		UseCase(Class<? extends CyborgController> controllerType) {this.controllerType = controllerType;}
	}

	@SuppressWarnings("unchecked")
	private Class<? extends ItemRenderer<? extends UseCase>>[] RendererTypes = new Class[]{
			Renderer_UseCase.class,
	};

	@SuppressWarnings("unchecked")
	private Class<? extends UseCase>[] ItemTypes = new Class[]{
			UseCase.class,
	};

	@ViewIdentifier(viewId = R.id.RV_Example,
									listeners = {
											ViewListener.OnRecyclerItemClicked,
									})
	private CyborgRecycler recycler;

	private DataModelGetter resolver;

	private CyborgAdapter<UseCase> adapter;

	public Controller_CommonUseCases() {
		super(R.layout.controller__use_cases);
	}

	@Override
	protected void onCreate() {
		adapter = new CyborgAdapter<>(this, RendererTypes);
		resolver = new DataModelGetter();
		adapter.setResolver(resolver);
		recycler.setAdapter(adapter);
	}

	@Override
	protected void onResume() {
		adapter.invalidateDataModel();
	}

	@Override
	protected void onPause() {
		super.onPause();
		resolver.startingIndex += 1;
	}

	@Override
	public void onRecyclerItemClicked(RecyclerView parentView, View view, int position) {
		UseCase itemForPosition = resolver.dataModel.getItemForPosition(position);
		logInfo("clicked: " + itemForPosition);
		createNewLayerBuilder().setControllerType(itemForPosition.controllerType).build();
	}

	@Override
	public boolean onLongClick(View v) {
		return true;
	}

	private class DataModelGetter
			implements Getter<DataModel<UseCase>> {

		int startingIndex = 0;

		private ListDataModel<UseCase> dataModel = new ListDataModel<>(ItemTypes);

		@Override
		public DataModel<UseCase> get() {
			dataModel.clear();
			dataModel.add(UseCase.values());
			return dataModel;
		}
	}

	private class Renderer_UseCase
			extends ItemRenderer<UseCase> {

		@ViewIdentifier(viewId = R.id.TV_Label,
										listeners = {})
		private TextView label;

		Renderer_UseCase() {
			super(R.layout.renderer__use_case);
		}

		@Override
		protected void renderItem(UseCase item) {
			label.setText(item.name());
		}
	}
}
