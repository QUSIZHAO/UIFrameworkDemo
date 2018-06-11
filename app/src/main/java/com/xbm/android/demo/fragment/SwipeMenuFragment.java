package com.xbm.android.demo.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xbm.android.demo.R;
import com.xbm.android.demo.activity.RefreshLoadMoreActivity;
import com.xbm.android.demo.activity.menu.*;
import com.xbm.android.demo.activity.move.*;
import com.xbm.android.demo.adapter.MainItemAdapter;
import com.xbm.android.demo.adapter.OnItemClickListener;

import java.util.Arrays;
import java.util.List;

/**
 * 列表项的侧滑菜单
 * Created by xiaobaima on 17-9-8.
 */

public class SwipeMenuFragment extends Fragment implements OnItemClickListener {

	private AppCompatActivity mCompatActivity;
	@RequiresApi(api = VERSION_CODES.M)
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
		@Nullable Bundle savedInstanceState) {
		mCompatActivity = (AppCompatActivity) getActivity();
		View view = inflater.inflate(R.layout.swipe_menu_fragment, container, false);
		initView(view);
		initData();
		return view;
	}

	private void initData() {
	}


	@RequiresApi(api = VERSION_CODES.M)
	private void initView(View view) {
		Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
		mCompatActivity.setSupportActionBar(toolbar);

		RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
			@Override
			public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
				outRect.set(10, 10, 10, 15);
			}
		});

		List<String> titles = Arrays.asList(getResources().getStringArray(R.array.main_item));
		List<String> descriptions = Arrays.asList(getResources().getStringArray(R.array.main_item_des));
		MainItemAdapter mainItemAdapter = new MainItemAdapter(titles, descriptions);
		mainItemAdapter.setOnItemClickListener(this);
		recyclerView.setAdapter(mainItemAdapter);
	}

	@Override
	public void onItemClick(int position) {
		switch (position) {
			case 0: {
				startActivity(new Intent(getActivity(), MenuHorizontalActivity.class));
				break;
			}
			case 1: {
				startActivity(new Intent(getActivity(), MenuVerticalActivity.class));
				break;
			}
			case 2: {
				startActivity(new Intent(getActivity(), MenuViewTypeActivity.class));
				break;
			}
			case 3: {
				startActivity(new Intent(getActivity(), MenuViewPagerActivity.class));
				break;
			}
			case 4: {
				startActivity(new Intent(getActivity(), MenuDrawerActivity.class));
				break;
			}
			case 5: {
				startActivity(new Intent(getActivity(), MenuCardActivity.class));
				break;
			}
			case 6: {
				startActivity(new Intent(getActivity(), MenuDefineActivity.class));
				break;
			}
			case 7: {
				startActivity(new Intent(getActivity(), RefreshLoadMoreActivity.class));
				break;
			}
			case 8: {
				startActivity(new Intent(getActivity(), DragListMenuActivity.class));
				break;
			}
			case 9: {
				startActivity(new Intent(getActivity(), DragGridMenuActivity.class));
				break;
			}
			case 10: {
				startActivity(new Intent(getActivity(), DragTouchListActivity.class));
				break;
			}
			case 11: {
				startActivity(new Intent(getActivity(), DragSwipeListActivity.class));
				break;
			}
			case 12: {
				startActivity(new Intent(getActivity(), DragSwipeFlagsActivity.class));
				break;
			}
		}
	}
}
