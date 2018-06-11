package com.xbm.android.demo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnScrollChangeListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.xbm.android.demo.R;
import com.xbm.android.flobtn.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaobaima on 17-9-19.
 */

public class FloatButtonFragment extends Fragment {

	private int mPreviousVisibleItem;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
		@Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.float_btn_fragment, container, false);
		ListView listView = (ListView) view.findViewById(R.id.list);
		final FloatingActionMenu floatingActionMenu = (FloatingActionMenu) view.findViewById(R.id.menu_labels_right);

		ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,
			getResources().getStringArray(R.array.float_btn_list_test));

		listView.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				Log.i(TAG, "firstVisibleItem====>>>" + firstVisibleItem);
				Log.i(TAG, "mPreviousVisibleItem====>>>" + mPreviousVisibleItem);
				if (firstVisibleItem > mPreviousVisibleItem){
					floatingActionMenu.hideMenu(true);
				}else if (firstVisibleItem < mPreviousVisibleItem){
					floatingActionMenu.showMenu(true);
				}

				mPreviousVisibleItem = firstVisibleItem;
			}
		});
		listView.setAdapter(adapter);
		return view;
	}

	private static final String TAG = FloatButtonFragment.class.getSimpleName();
}
