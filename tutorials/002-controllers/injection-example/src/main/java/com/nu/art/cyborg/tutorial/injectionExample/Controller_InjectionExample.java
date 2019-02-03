package com.nu.art.cyborg.tutorial.injectionExample;

import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;

/**
 * Created by TacB0sS on 12-May 2017.
 */

public class Controller_InjectionExample
	extends CyborgController {

	@ViewIdentifier(
		viewId = R.id.View1,
		listeners = {
			ViewListener.OnClick,
			ViewListener.OnLongClick
		})
	private View[] view1;

	// This one is extracted manually which override the annotation injection
	@ViewIdentifier(
		viewId = R.id.View3,
		listeners = {
			ViewListener.OnClick,
			ViewListener.OnLongClick
		})
	private View view4;

	@ViewIdentifier(
		viewId = {
			R.id.View2,
			R.id.View3
		},
		listeners = {
			ViewListener.OnClick,
			ViewListener.OnLongClick
		})
	private View[] views;

	/**
	 * Note the event reaching the <b>afterTextChanged</b> method
	 */
	@ViewIdentifier(viewId = R.id.ET_EditText,
	                listeners = ViewListener.OnTextChangedListener)
	private EditText inputText;

	@ViewIdentifier(viewId = R.id.InjectedController,
	                listeners = ViewListener.OnClick)
	private Controller_InjectedController injectedController;

	/**
	 * Note the event reaching the <b>onClick</b> and <b>onLongClick</b> methods respectively
	 */
	@ViewIdentifier(viewId = R.id.TV_Result)
	private TextView resultTextView;

	private Module_InjectionExample module;

	public Controller_InjectionExample() {
		super(R.layout.controller__injection_example);
	}

	@Override
	protected void extractMembers() {
		super.extractMembers();
		view4 = getViewById(R.id.View4);
	}

	@Override
	public void afterTextChanged(TextView view, Editable editableValue) {
		// <<< ADD A BREAKPOINT AT THE NEXT LINE
		if (view == inputText) {
			resultTextView.setText("Text Changed: " + inputText.getText().toString());
		}
	}

	@Override
	public void onClick(View v) {
		String view = getViewId(v);
		resultTextView.setText(view + " - onClick");
	}

	@Override
	public boolean onLongClick(View v) {
		// <<< ADD A BREAKPOINT AT THE NEXT LINE
		String view = getViewId(v);
		resultTextView.setText(view + " - onLongClick");
		return false;
	}

	private String getViewId(View v) {
		String view;
		switch (v.getId()) {
			case R.id.InjectedController:
				view = "InjectedController";
				break;

			case R.id.View1:
				view = "view1";
				break;

			case R.id.View2:
				view = "view2";
				break;

			case R.id.View3:
				view = "view3";
				break;

			case R.id.View4:
				view = "view4";
				break;

			default:
				view = "???";
		}
		return view;
	}
}

