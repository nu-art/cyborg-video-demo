package com.nu.art.cyborg.tutorial.stackOverview;

import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.nu.art.core.interfaces.Getter;
import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgAdapter;
import com.nu.art.cyborg.core.CyborgController;
import com.nu.art.cyborg.core.CyborgStackController;
import com.nu.art.cyborg.core.ItemRenderer;
import com.nu.art.cyborg.core.dataModels.DataModel;
import com.nu.art.cyborg.core.dataModels.ListDataModel;

public class Controller_StackConfig
	extends CyborgController {

	@ViewIdentifier(
		viewId = R.id.CB_KeepInStack,
		listeners = {
			ViewListener.OnClick,
		})
	private CheckBox cbkeepInStack;

	@ViewIdentifier(
		viewId = R.id.TV_ClearAll,
		listeners = {
			ViewListener.OnClick,
		})
	private TextView tvClearAll;

	@ViewIdentifier(
		viewId = R.id.TV_ClearToTag,
		listeners = {
			ViewListener.OnClick,
		})
	private TextView tvClearToTag;

	@ViewIdentifier(
			viewId = R.id.TV_StackSize)
	private TextView tvStackSize;

	@ViewIdentifier(
		viewId = R.id.SP_Tags,
		listeners = {
			ViewListener.OnItemSelected,
		})
	private Spinner tags;

	private boolean keepInStack;
	private String tag;

	boolean isKeepInStack() {
		return keepInStack;
	}

	public Controller_StackConfig() {
		super(R.layout.controller__stack_config);
	}

	@Override
	protected void onCreate() {
		super.onCreate();
		keepInStack = cbkeepInStack.isChecked();
	}

	@Override
	protected void onResume() {
		super.onResume();

		tvStackSize.setText("Stack Size: " + getStack().getStackLayersTags().length);
		setSpinner(tags, getStack().getStackLayersTags());
	}

	@Override
	public void onClick(View v) {
		final CyborgStackController stack = getStack();
		switch (v.getId()) {
			case R.id.CB_KeepInStack:
				keepInStack = cbkeepInStack.isChecked();
				break;

			case R.id.TV_ClearAll:
				stack.clear();
				break;

			case R.id.TV_ClearToTag:
				stack.popUntil(tag);
				break;
		}
	}

	@Override
	public void onItemSelected(Object selectedItem, AdapterView<?> parentView, View selectedView, int position, long id) {
		tag = (String) selectedItem;
	}

	private void setSpinner(Spinner spinner, final Object[] values) {
		CyborgAdapter<Object> sourceAdapter = new CyborgAdapter<>(this, Renderer_SourceType.class);
		sourceAdapter.setResolver(new Getter<DataModel<Object>>() {
			ListDataModel<Object> model = new ListDataModel<>(Object.class);

			@Override
			public DataModel<Object> get() {
				model.clear();
				model.add(values);
				return model;
			}
		});

		spinner.setAdapter(sourceAdapter.getArrayAdapter());
	}

	private class Renderer_SourceType
			extends ItemRenderer<Object> {

		@ViewIdentifier(viewId = R.id.ExampleLabel)
		TextView name;

		protected Renderer_SourceType() {
			super(R.layout.item_spinner);
		}

		@Override
		protected void renderItem(Object tagName) {
			name.setText(tagName.toString());
		}
	}
}

