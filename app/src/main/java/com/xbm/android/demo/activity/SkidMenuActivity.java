package com.xbm.android.demo.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.xbm.android.demo.R;
import com.xbm.android.widget.DragLayout;

import java.lang.reflect.Field;

/**
 * @描述 侧滑菜单的示例
 * @作者 小白马
 * @邮箱 1923898264@qq.com
 * @qq 1923898264
 * Created by xiaobaima on 17-9-8.
 */

public class SkidMenuActivity extends Activity{

	private static final String TAG = "SkidMenuActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.skid_menu_activity);
		initDragLayout();
		showInfiltrationStatusBar();
	}

	private void initDragLayout() {
		DragLayout dragLayout = (DragLayout) findViewById(R.id.drag_layout);
		dragLayout.setDragListener(new DragLayout.DragListener() {
			//界面打开的时候
			@Override
			public void onOpen() {
			}
			//界面关闭的时候
			@Override
			public void onClose() {
			}

			//界面滑动的时候
			@Override
			public void onDrag(float percent) {
			}
		});
	}

	/**
	 * 显示沉浸式状态栏
	 */
	private void showInfiltrationStatusBar(){
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			//透明状态栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			//透明导航栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}

		DragLayout dragLayout = (DragLayout) findViewById(R.id.drag_layout);
		setStatusBar(R.id.rl_title);
	}

	/**
	 * 设置沉浸式状态栏
	 */
	public void setStatusBar(int res) {

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			final ViewGroup linear_bar = (ViewGroup) findViewById(res);
			final int statusHeight = getStatusBarHeight();
			linear_bar.post(new Runnable() {
				@Override
				public void run() {
					int titleHeight = linear_bar.getHeight();
					android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) linear_bar.getLayoutParams();
					params.height = statusHeight + titleHeight;
					linear_bar.setLayoutParams(params);
				}
			});
		}
	}

	/**
	 * 获取状态栏的高度
	 * @return
	 */
	protected int getStatusBarHeight(){
		try
		{
			Class<?> c= Class.forName("com.android.internal.R$dimen");
			Object obj=c.newInstance();
			Field field=c.getField("status_bar_height");
			int x= Integer.parseInt(field.get(obj).toString());
			return  getResources().getDimensionPixelSize(x);
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
}
