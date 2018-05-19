package com.nu.art.cyborg.tutorial.infraExample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nu.art.core.interfaces.Getter;
import com.nu.art.cyborg.core.CyborgAdapter;
import com.nu.art.cyborg.core.CyborgController;
import com.nu.art.cyborg.core.CyborgRecycler;
import com.nu.art.cyborg.core.ItemRenderer;
import com.nu.art.cyborg.core.dataModels.DataModel;
import com.nu.art.cyborg.core.dataModels.ListDataModel;
import com.nu.art.cyborg.tutorial.infraExample.Module_Examples.UseCaseExample;

/**
 * Created by TacB0sS on 20-May 2017.
 */

public class Controller_Examples
	extends CyborgController {

	@SuppressWarnings("unchecked")
	private Class<? extends ItemRenderer<? extends UseCaseExample>>[] RendererTypes = new Class[]{
		Renderer_Example.class,
	};

	@SuppressWarnings("unchecked")
	private Class<? extends UseCaseExample>[] ItemTypes = new Class[]{
		UseCaseExample.class,
	};

	//  till version 0.9.59 is out
	//	@ViewIdentifier(listeners = {
	//		ViewListener.OnRecyclerItemClicked,
	//	})
	private CyborgRecycler recycler;

	private DataModelGetter resolver;

	private CyborgAdapter<UseCaseExample> adapter;

	public Controller_Examples() {
		super(R.layout.controller__example_list);
	}

	@Override
	protected void extractMembers() {
		recycler = getViewById(R.id.RV_Example);
		recycler.setRecyclerItemClickListener(this);
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
		UseCaseExample itemForPosition = resolver.dataModel.getItemForPosition(position);
		logInfo("clicked: " + itemForPosition);
		createNewLayerBuilder().setControllerType(itemForPosition.getControllerType()).build();
	}

	@Override
	public boolean onLongClick(View v) {
		return true;
	}

	private class DataModelGetter
		implements Getter<DataModel<UseCaseExample>> {

		int startingIndex = 0;

		private ListDataModel<UseCaseExample> dataModel = new ListDataModel<>(ItemTypes);

		@Override
		public DataModel<UseCaseExample> get() {
			dataModel.clear();
			dataModel.add(getModule(Module_Examples.class).getExamples());
			return dataModel;
		}
	}

	private class Renderer_Example
		extends ItemRenderer<UseCaseExample> {

		private TextView label;

		Renderer_Example() {
			super(R.layout.renderer__example);
		}

		@Override
		protected void extractMembers() {
			label = getViewById(R.id.TV_Label);
		}

		@Override
		protected void renderItem(UseCaseExample item) {
			label.setText(item.getName());
		}
	}
}
