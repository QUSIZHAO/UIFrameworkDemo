package com.xbm.android.demo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.xbm.android.anim.ExpectAnim;
import com.xbm.android.demo.R;

import static com.xbm.android.anim.core.Expectations.*;
import static com.xbm.android.anim.core.Expectations.alignLeft;
import static com.xbm.android.anim.core.Expectations.belowOf;

/**
 * Created by xiaobaima on 17-9-18.
 */

public class EpectEffectThree extends Fragment {

	private View mText1;
	private View mText2;
	private View mText3;
	private View mText4;
	private ExpectAnim mExpectAnimMove;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.effect_three_fragment, container, false);
		initView(view);
		initAnim();
		return view;
	}

	private void initAnim() {
		this.mExpectAnimMove = new ExpectAnim()

			.expect(mText1)
			.toBe(
				topOfParent(),
				leftOfParent(),
				rotated(90)
			)

			.expect(mText2)
			.toBe(
				alignLeft(mText1),
				belowOf(mText1)
			)

			.expect(mText3)
			.toBe(
				alignTop(mText1),
				toRightOf(mText1)
			)

			.expect(mText4)
			.toBe(
				belowOf(mText3),
				alignLeft(mText3)
			)

			.toAnimation()
			.setDuration(1500);
	}

	private void initView(View view) {
		mText1 = view.findViewById(R.id.text1);
		mText2 = view.findViewById(R.id.text2);
		mText3 = view.findViewById(R.id.text3);
		mText4 = view.findViewById(R.id.text4);
		view.findViewById(R.id.content).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mText1.getRotation() == 0) {
					mExpectAnimMove.start();
				} else {
					mExpectAnimMove.reset();
				}
			}
		});
	}
}
