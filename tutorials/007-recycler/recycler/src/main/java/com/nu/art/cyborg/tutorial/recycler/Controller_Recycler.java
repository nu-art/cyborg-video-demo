package com.nu.art.cyborg.tutorial.recycler;

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

/**
 * Created by TacB0sS on 20-May 2017.
 */

public class Controller_Recycler
		extends CyborgController {

	@SuppressWarnings("unchecked")
	private Class<? extends ItemRenderer<? extends Number>>[] RendererTypes = new Class[]{
			Renderer_Integer.class,
			Renderer_Float.class,
			Renderer_Double.class
	};

	@SuppressWarnings("unchecked")
	private Class<? extends Number>[] ItemTypes = new Class[]{
			Integer.class,
			Float.class,
			Double.class
	};

	@ViewIdentifier(viewId = R.id.RV_Example,
									listeners = {
											ViewListener.OnRecyclerItemClicked,
											ViewListener.OnRecyclerItemLongClicked
									})
	private CyborgRecycler recycler;

	private DataModelGetter resolver;

	private CyborgAdapter<Number> adapter;

	public Controller_Recycler() {
		super(R.layout.controller__recycler);
	}

	@Override
	protected void onCreate() {
		adapter = new CyborgAdapter<Number>(this, RendererTypes);
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
	public boolean onRecyclerItemLongClicked(RecyclerView parentView, View view, int position) {
		logInfo("long clicked: " + resolver.dataModel.getItemForPosition(position));
		return super.onRecyclerItemLongClicked(parentView, view, position);
	}

	@Override
	public void onRecyclerItemClicked(RecyclerView parentView, View view, int position) {
		logInfo("clicked: " + resolver.dataModel.getItemForPosition(position));
	}

	@Override
	public boolean onLongClick(View v) {
		return true;
	}

	private class DataModelGetter
			implements Getter<DataModel<Number>> {

		int startingIndex = 0;

		private ListDataModel<Number> dataModel = new ListDataModel<Number>(ItemTypes);

		@Override
		public DataModel<Number> get() {
			dataModel.clear();

			for (int i = startingIndex; i < 100; i++) {
				if (i % 5 == 0)
					dataModel.add(i * 1.1);
				else if (i % 2 == 0)
					dataModel.add(i * 1.1f);
				else
					dataModel.add(i);
			}
			return dataModel;
		}
	}

	private class Renderer_Integer
			extends ItemRenderer<Integer> {

		@ViewIdentifier(viewId = R.id.TV_Number,
										listeners = {})
		private TextView number;

		Renderer_Integer() {
			super(R.layout.renderer__number);
		}

		@Override
		protected void renderItem(Integer item) {
			number.setText("int: " + item);
		}
	}

	private class Renderer_Float
			extends ItemRenderer<Float> {

		@ViewIdentifier(viewId = R.id.TV_Number,
										listeners = {})
		private TextView number;

		Renderer_Float() {
			super(R.layout.renderer__number);
		}

		@Override
		protected void renderItem(Float item) {
			number.setText("float: " + item);
		}
	}

	private class Renderer_Double
			extends ItemRenderer<Double> {

		@ViewIdentifier(viewId = R.id.TV_Number,
										listeners = {})
		private TextView number;

		Renderer_Double() {
			super(R.layout.renderer__number);
		}

		@Override
		protected void renderItem(Double item) {
			number.setText("double: " + item);
		}
	}
}
