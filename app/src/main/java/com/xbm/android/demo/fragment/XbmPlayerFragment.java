package com.xbm.android.demo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.exoplayer.ExoPlayer;
import com.xbm.android.demo.R;
import com.xbm.android.media.xbmplay.IMediaPlayer;
import com.xbm.android.media.xbmplay.IPlayState;
import com.xbm.android.media.xbmplay.XbmMediaPlayer;

/**
 * 视频点播
 * Created by xiaobaima on 17-9-5.
 */

public class XbmPlayerFragment extends Fragment implements OnClickListener, IPlayState {

	private IMediaPlayer mediaPlayer;
	private String url = "http://enterprise.hisugj.com/smart_file/sys_file/cdnFilesFolder/ftpFiles/heilongjiang/duocaiheilongjiang/006.mp4";
	private static final String TAG = "XbmPlayerFragment";
	private TextView mDuration;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
		@Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.xbm_player_fragment, container, false);
		init(view);
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		mediaPlayer.play(url, this);
	}

	@Override
	public void onStop() {
		super.onStop();
		mediaPlayer.stop();
	}

	private void init(View view){
		SurfaceView surfaceView = (SurfaceView) view.findViewById(R.id.sv_video);
		mediaPlayer = new XbmMediaPlayer(getActivity(), surfaceView);

		view.findViewById(R.id.tui_btn).setOnClickListener(this);
		view.findViewById(R.id.jin_btn).setOnClickListener(this);
		view.findViewById(R.id.pause_btn).setOnClickListener(this);
		view.findViewById(R.id.start_btn).setOnClickListener(this);
		mDuration = (TextView) view.findViewById(R.id.duration_txt);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.tui_btn:
				mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 5000);
				break;
			case R.id.jin_btn:
				mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 5000);
				break;
			case R.id.pause_btn:
				mediaPlayer.pause();
				break;
			case R.id.start_btn:
				mediaPlayer.start();
				break;
		}
	}

	@Override
	public void onPlayState(boolean playWhenReady, int playbackState) {
		switch (playbackState) {
			case ExoPlayer.STATE_BUFFERING: //加载中
				Log.i(TAG, "====STATE_BUFFERING====");
				break;
			case ExoPlayer.STATE_ENDED: //播放完毕
				Log.i(TAG, "====STATE_ENDED====");
				break;
			case ExoPlayer.STATE_IDLE://网络状态差，请检查网络
				Log.i(TAG, "====STATE_IDLE====");
				break;
			case ExoPlayer.STATE_PREPARING: //正在加载
				Log.i(TAG, "====STATE_PREPARING====");
				break;
			case ExoPlayer.STATE_READY:  //加载完毕
				Log.i(TAG, "====STATE_READY====");
				mDuration.setText(mediaPlayer.getDuration()+"");
				break;
			default:
				break;
		}
	}
}
