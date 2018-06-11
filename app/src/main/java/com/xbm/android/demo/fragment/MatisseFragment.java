package com.xbm.android.demo.fragment;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xbm.android.demo.R;
import com.xbm.android.matisse.Matisse;
import com.xbm.android.matisse.MimeType;
import com.xbm.android.matisse.engine.impl.GlideEngine;
import com.xbm.android.matisse.engine.impl.PicassoEngine;
import com.xbm.android.matisse.filter.Filter;
import com.xbm.android.matisse.filter.GifSizeFilter;
import com.xbm.android.matisse.internal.entity.CaptureStrategy;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by xiaobaima on 17-9-19.
 */

public class MatisseFragment extends Fragment implements OnClickListener{

	private static final int REQUEST_CODE_CHOOSE = 23;
	private TextView mImageDataTextView;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
		@Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.matisse_fragment, container, false);

		view.findViewById(R.id.zhihu_style_btn).setOnClickListener(this);

		view.findViewById(R.id.other_style_btn).setOnClickListener(this);
		mImageDataTextView = (TextView) view.findViewById(R.id.img_data);
		return view;
	}

	@Override
	public void onClick(final View v) {
		RxPermissions rxPermissions = new RxPermissions(getActivity());
		rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
		             .subscribe(new Observer<Boolean>() {
			             @Override
			             public void onSubscribe(Disposable d) {

			             }

			             @Override
			             public void onNext(Boolean aBoolean) {
				             if (aBoolean) {
					             switch (v.getId()) {
						             case R.id.zhihu_style_btn:
							             Matisse.from(getActivity())
							                    .choose(MimeType.ofAll(), false)
							                    .countable(true)
							                    .capture(true)
							                    .captureStrategy(
								                    new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider"))
							                    .maxSelectable(9)
							                    .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
							                    .gridExpectedSize(
								                    getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
							                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
							                    .thumbnailScale(0.85f)
							                    .imageEngine(new GlideEngine())
							                    .forResult(REQUEST_CODE_CHOOSE);
							             break;
						             case R.id.other_style_btn:
							             Matisse.from(getActivity())
							                    .choose(MimeType.ofImage())
							                    .theme(R.style.Matisse_Dracula)
							                    .countable(false)
							                    .maxSelectable(9)
							                    .imageEngine(new PicassoEngine())
							                    .forResult(REQUEST_CODE_CHOOSE);
							             break;
					             }
				             } else {
					             Toast.makeText(getActivity(),
						             R.string.permission_request_denied, Toast.LENGTH_LONG)
					                  .show();
				             }
			             }

			             @Override
			             public void onError(Throwable e) {

			             }

			             @Override
			             public void onComplete() {

			             }
		             });
	}

	private static final String TAG = MatisseFragment.class.getSimpleName();
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.i(TAG, TAG + "===onActivityResult===");
		if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
			StringBuilder stringBuilder = new StringBuilder();
			List<Uri> uriList = Matisse.obtainResult(data);
			List<String> pathList = Matisse.obtainPathResult(data);
			int size = pathList.size();
			for (int i=0; i < size; i++){
				stringBuilder.append("\nuri: " + uriList.get(i).toString());
				stringBuilder.append("\npath: " + pathList.get(i));
			}

			mImageDataTextView.setText(stringBuilder.toString());
		}
	}
}
