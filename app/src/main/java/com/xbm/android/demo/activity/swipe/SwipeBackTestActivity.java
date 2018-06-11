package com.xbm.android.demo.activity.swipe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.xbm.android.demo.R;

/**
 * Created by xiaobaima on 17-9-14.
 */

public class SwipeBackTestActivity extends AppCompatActivity {
	public static int Page = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.swipe_back_activity);

		findViewById(R.id.swipe_btn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Page = 1;
				startActivity(new Intent(SwipeBackTestActivity.this, SwipeActivity.class));
			}
		});
	}
}
