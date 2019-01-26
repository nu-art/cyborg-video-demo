package PLACEHOLDER_PACKAGE_NAME;

import android.widget.TextView;

import com.nu.art.cyborg.annotations.ViewIdentifier;
import com.nu.art.cyborg.common.consts.ViewListener;
import com.nu.art.cyborg.core.CyborgController;

public class Controller_PLACEHOLDER_CLASS_NAME
	extends CyborgController {

	@ViewIdentifier(
		viewId = R.id.TV_State,
		listeners = {
			ViewListener.OnClick,
			ViewListener.OnLongClick
		})
	private TextView state;

	private Module_PLACEHOLDER_CLASS_NAME module;

	public Controller_PLACEHOLDER_CLASS_NAME() {
		super(R.layout.controller_PLACEHOLDER_RESOURCE_NAME);
	}
}

