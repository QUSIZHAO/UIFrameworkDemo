package com.xbm.android.demo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.NestedScrollView.OnScrollChangeListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.xbm.android.anim.ExpectAnim;
import com.xbm.android.demo.R;

import static com.xbm.android.anim.core.Expectations.*;
import static com.xbm.android.anim.core.Expectations.alpha;
import static com.xbm.android.anim.core.Expectations.sameCenterVerticalAs;

/**
 * Created by xiaobaima on 17-9-18.
 */

public class ExpectEffectOne extends Fragment {

	private View mUserName;
	private View mAvatar;
	private View mFollow;
	private View mBackbground;
	private NestedScrollView mNestedScrollView;
	private ExpectAnim expectAnimMove;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view  = inflater.inflate(R.layout.effect_one_fragment, container, false);
		initView(view);
		initAnim();
		return view;
	}

	private void initAnim() {
		int height = (int) getResources().getDimension(R.dimen.dimen_90dp);

		expectAnimMove = new ExpectAnim()
			.expect(mAvatar)
			.toBe(
				topOfParent().withMarginDp(20),
				leftOfParent().withMarginDp(20),
				scale(0.5f, 0.5f)
			)
			.expect(mUserName)
			.toBe(
				toRightOf(mAvatar).withMarginDp(16),
				sameCenterVerticalAs(mAvatar),

				alpha(0.5f)
			)
			.expect(mFollow)
			.toBe(
				rightOfParent().withMarginDp(20),
				sameCenterVerticalAs(mAvatar)
			)
			.expect(mBackbground)
			.toBe(
				height(height).withGravity(Gravity.LEFT, Gravity.TOP)
			).toAnimation();
	}

	private void initView(View view) {
		mUserName = view.findViewById(R.id.username);
		mAvatar = view.findViewById(R.id.avatar);
		mFollow = view.findViewById(R.id.follow);
		mFollow.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				expectAnimMove.start();
			}
		});
		mBackbground = view.findViewById(R.id.background);
		mNestedScrollView = (NestedScrollView) view.findViewById(R.id.scrollview);
		mNestedScrollView.setOnScrollChangeListener(new OnScrollChangeListener() {
			@Override
			public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
				final float percent = (scrollY * 1f) / v.getMaxScrollAmount();
				expectAnimMove.setPercent(percent);
			}
		});
	}
}
