package com.nu.art.cyborg.tutorial.customAttributes;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build.VERSION_CODES;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.nu.art.cyborg.core.CyborgBuilder;
import com.nu.art.cyborg.modules.AttributeModule;
import com.nu.art.cyborg.modules.AttributeModule.AttributesSetter;
import com.nu.art.reflection.annotations.ReflectiveInitialization;

/**
 * Created by TacB0sS on 17-Jun 2017.
 */

public class CustomDottedView
		extends View {

	int dotCount = 2;

	float dotRadius = 50;

	float spaceBetweenDots = 50;

	private Paint paint;

	private float dotsWidth;

	public CustomDottedView(Context context) {
		super(context);
	}

	public CustomDottedView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	public CustomDottedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(attrs);
	}

	@TargetApi(VERSION_CODES.LOLLIPOP)
	public CustomDottedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init(attrs);
	}

	private void init(AttributeSet attrs) {
		CyborgBuilder.getModule(isInEditMode() ? getContext() : null, AttributeModule.class).setAttributes(getContext(), attrs, this);
		dotsWidth = (dotCount * dotRadius * 2 + (dotCount - 1) * spaceBetweenDots);

		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.RED);
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);

		float initX = (getWidth() - dotsWidth) / 2;
		float initY = getHeight() / 2;

		for (int i = 0; i < dotCount; i++) {
			canvas.drawCircle(initX + (dotRadius * 2 * (i) + spaceBetweenDots * i) + dotRadius, initY, dotRadius, paint);
		}
	}

	@ReflectiveInitialization
	public static class CustomDottedViewSetter
			extends AttributesSetter<CustomDottedView> {

		private static int[] ids = {
				R.styleable.DottedView_dotCount,
				R.styleable.DottedView_dotRadius,
				R.styleable.DottedView_dotSpace,
		};

		private CustomDottedViewSetter() {
			super(CustomDottedView.class, R.styleable.DottedView, ids);
		}

		@Override
		protected void setAttribute(CustomDottedView instance, TypedArray a, int attr) {
			if (attr == R.styleable.DottedView_dotCount) {
				instance.dotCount = a.getInt(attr, 0);
				return;
			}
			if (attr == R.styleable.DottedView_dotRadius) {
				instance.dotRadius = a.getFloat(attr, 0);
				return;
			}
			if (attr == R.styleable.DottedView_dotSpace) {
				instance.spaceBetweenDots = a.getFloat(attr, 0);
				return;
			}
		}
	}
}

