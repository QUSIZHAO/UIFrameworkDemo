package com.xbm.android.demo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.florent37.fiftyshadesof.FiftyShadesOf;
import com.xbm.android.demo.R;

/**
 * Created by xiaobaima on 17-10-12.
 */

public class FiftyshadeFragment extends Fragment {

	private View mView;
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fiftyshade_fragment, container, false);
		initView();
		return mView;
	}

	private void initView() {
		getActivity().findViewById(android.R.id.content).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				load();
			}
		});
	}

	void load(){
		final FiftyShadesOf fiftyShadesOf = FiftyShadesOf.with(getActivity())
		                                                 .on(mView.getId())
		                                                 .start();

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				fiftyShadesOf.stop();
			}
		}, 2000);
	}
}
