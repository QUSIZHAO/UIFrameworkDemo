package com.xbm.android.demo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import com.xbm.android.anim.ExpectAnim;
import com.xbm.android.demo.R;

import static com.xbm.android.anim.core.Expectations.*;

/**
 * Created by xiaobaima on 17-9-18.
 */

public class EfectTowActivity extends AppCompatActivity {

	private View name;
	private View avatar;
	private View subname;
	private View follow;
	private View message;

	private View bottomLayout;
	private View content;

	private ExpectAnim expectAnimMove;
	private static final String TAG = "EfectTowActivity";

	private void initView() {
		name = findViewById(R.id.name);
		avatar = findViewById(R.id.avatar);
		subname = findViewById(R.id.subname);
		follow = findViewById(R.id.follow);
		follow.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				expectAnimMove.reset();

			}
		});
		message = findViewById(R.id.message);
		message.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				expectAnimMove.start();
			}
		});
		bottomLayout = findViewById(R.id.bottomLayout);
		content = findViewById(R.id.content);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.effect_two_activity);
		initView();

		Log.i(TAG, "" + this.toString());

		new ExpectAnim()
			.expect(bottomLayout)
			.toBe(
				outOfScreen(Gravity.BOTTOM)
			)
			.expect(content)
			.toBe(
				outOfScreen(Gravity.BOTTOM)
			)
			.toAnimation()
			.setNow();

		this.expectAnimMove = new ExpectAnim()

			.expect(avatar)
			.toBe(
				bottomOfParent().withMarginDp(36),
				leftOfParent().withMarginDp(16),
				width(40).toDp().keepRatio()
			)

			.expect(name)
			.toBe(
				toRightOf(avatar).withMarginDp(16),
				sameCenterVerticalAs(avatar),
				toHaveTextColor(Color.WHITE)
			)

			.expect(subname)
			.toBe(
				toRightOf(name).withMarginDp(5),
				sameCenterVerticalAs(name),
				toHaveTextColor(Color.WHITE)
			)

			.expect(follow)
			.toBe(
				rightOfParent().withMarginDp(4),
				bottomOfParent().withMarginDp(12),
				toHaveBackgroundAlpha(0f)
			)

			.expect(message)
			.toBe(
				aboveOf(follow).withMarginDp(4),
				rightOfParent().withMarginDp(4),
				toHaveBackgroundAlpha(0f)
			)

			.expect(bottomLayout)
			.toBe(
				atItsOriginalPosition()
			)

			.expect(content)
			.toBe(
				atItsOriginalPosition(),
				visible()
			)

			.toAnimation()
			.setDuration(1500);
	}
}
