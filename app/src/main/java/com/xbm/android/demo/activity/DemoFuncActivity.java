package com.xbm.android.demo.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.xbm.android.demo.R;
import com.xbm.android.demo.fragment.XbmPlayerFragment;
import com.xbm.android.demo.utils.Contact;

import com.xbm.android.demo.fragment.SwipeMenuFragment;
import com.xbm.android.demo.fragment.IndexBarFragment;
import com.xbm.android.demo.fragment.FloatButtonFragment;
import com.xbm.android.demo.fragment.MatisseFragment;
import com.xbm.android.demo.fragment.FiftyshadeFragment;

import static com.xbm.android.demo.utils.Contact.*;

/**
 * Created by xiaobaima on 17-9-5.
 */

public class DemoFuncActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_func_activity);
		int position = getIntent().getIntExtra("position", 0);
		replaceView(position);
	}

	private Fragment mFragment;
	private void replaceView(int position) {
		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
		switch (position){
			case Contact.XbmPlayerFragment:
			fragmentTransaction.replace(R.id.demo_content, new XbmPlayerFragment());
			break;
			case Contact.SkidMenuActivity:
				startActivity(new Intent(DemoFuncActivity.this, SkidMenuActivity.class));
				finish();
				return;
			case SwipeMenuFragment:
				fragmentTransaction.replace(R.id.demo_content, new SwipeMenuFragment());
				break;
			case IndexBarFragment:
				fragmentTransaction.replace(R.id.demo_content, new IndexBarFragment());
				break;
			case FloatButtonFragment:
				fragmentTransaction.replace(R.id.demo_content, new FloatButtonFragment());
				break;
			case MatisseFragment:
				mFragment = new MatisseFragment();
				fragmentTransaction.replace(R.id.demo_content, mFragment);
				break;
			case FiftyshadeFragment:
				fragmentTransaction.replace(R.id.demo_content, new FiftyshadeFragment());
				break;
		}

		fragmentTransaction.commit();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			if (mFragment != null){
				if (mFragment instanceof MatisseFragment){
					mFragment.onActivityResult(requestCode, resultCode, data);
				}
			}
		}
	}

}
