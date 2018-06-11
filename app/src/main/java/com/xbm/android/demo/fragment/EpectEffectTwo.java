package com.xbm.android.demo.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.xbm.android.demo.R;
import com.xbm.android.demo.activity.EfectTowActivity;


/**
 * Created by xiaobaima on 17-9-18.
 */

public class EpectEffectTwo extends Fragment {



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.effect_two_fragment, container, false);
		view.findViewById(R.id.expect_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(), EfectTowActivity.class));
			}
		});
		return view;
	}




}
