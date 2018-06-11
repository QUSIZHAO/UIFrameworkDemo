package com.xbm.android.media.xbmplay;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import com.google.android.exoplayer.ExoPlaybackException;
import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.MediaCodecTrackRenderer;
import com.google.android.exoplayer.MediaCodecUtil;
import com.google.android.exoplayer.drm.UnsupportedDrmException;
import com.google.android.exoplayer.util.Util;
import com.xbm.android.R;
import com.xbm.android.media.exoplay.*;

/**
 * 视频播放接口的实现
 * Created by xiaobaima on 17-8-23.
 */
public class XbmMediaPlayer implements IMediaPlayer,
	CustomPlayer.Listener, SurfaceHolder.Callback{
	
	private static final String TAG = "HisuSuperMediaPlayer";
	private MediaController mMediaController;
	private SurfaceView mSurfaceView;
	private Context mContext;
	private Uri mUri;
	private CustomPlayer mCustomPlayer;
	private long mPlayerPosition;
	private boolean mPlayerNeedsPrepare;
	public IPlayState mPlayState = null;
	
	public XbmMediaPlayer(Activity context, SurfaceView surfaceView){
		mContext = context;
		mSurfaceView = surfaceView;
		mSurfaceView.getHolder().addCallback(this);
		mMediaController = new KeyCompatibleMediaController(context);
	}

	public XbmMediaPlayer(Activity context, View view, SurfaceView surfaceView){
		mContext = context;
		mSurfaceView = surfaceView;
		mSurfaceView.getHolder().addCallback(this);
		mMediaController = new KeyCompatibleMediaController(context);
		mMediaController.setAnchorView(view);

		view.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_ESCAPE
					|| keyCode == KeyEvent.KEYCODE_MENU)
					return false;
				return mMediaController.dispatchKeyEvent(event);
			}
		});
	}

	
	private void releasePlayer() {
		if (mCustomPlayer != null) {
			mCustomPlayer.seekTo(0);
			mPlayerPosition = mCustomPlayer.getCurrentPosition();
			mCustomPlayer.release();
			mCustomPlayer = null;
			Log.i(TAG, "====releasePlayer====");
		}
	}
	private int contentType;//流媒体传输协议类型
	private CustomPlayer.RendererBuilder getRendererBuilder() {
		String userAgent = Util.getUserAgent(mContext, "ExoPlayerTest");
		switch (contentType) {
			case Util.TYPE_SS:
				return new SmoothStreamingRendererBuilder(mContext, userAgent, mUri.toString(),
					new SmoothStreamingTestMediaDrmCallback());
			case Util.TYPE_DASH:
				return new DashRendererBuilder(mContext, userAgent, mUri.toString(),
					new WidevineTestMediaDrmCallback(null, null));
			//new WidevineTestMediaDrmCallback(contentId, provider));
			case Util.TYPE_HLS:
				return new HlsRendererBuilder(mContext, userAgent, mUri.toString());
			case Util.TYPE_OTHER:
				return new ExtractorRendererBuilder(mContext, userAgent, mUri);
			default:
				throw new IllegalStateException("Unsupported type: " + contentType);
		}
	}


	/**
	 * 根据uri判断出媒体类型,//根据uri获取流媒体传输协议
	 * Makes a best guess to infer the type from a media {@link Uri} and an optional overriding file
	 * extension.使最佳猜测推断出从一个媒体类型{ @link Uri }和一个可选的最重要的文件扩展。
	 *
	 * @return The inferred type.推断出来的视频类型
	 */
	private static int inferContentType(Uri uri) {
		String u = uri.toString();
		String lastPathSegment = u.substring(u.lastIndexOf("."));
		return Util.inferContentType(lastPathSegment);
	}

	@Override
	public void play(String url, IPlayState playState) {
		mPlayState = playState;
		mUri = Uri.parse(url);
		contentType = inferContentType(mUri);
		if (mCustomPlayer == null) {
			Log.i(TAG, "====play====");
			preparePlayer(true);
		} else {
			mCustomPlayer.setBackgrounded(false);
		}
	}

	private void preparePlayer(boolean playWhenReady) {
		if (mCustomPlayer == null) {
			mCustomPlayer = new CustomPlayer(getRendererBuilder());
			mCustomPlayer.addListener(this);
			mCustomPlayer.seekTo(mPlayerPosition);
			mPlayerNeedsPrepare = true; 
		}
		if (mPlayerNeedsPrepare) {
			mCustomPlayer.prepare();
			mPlayerNeedsPrepare = false;
		}
		mCustomPlayer.setSurface(mSurfaceView.getHolder().getSurface());
		mCustomPlayer.setPlayWhenReady(playWhenReady);

	}

	@Override
	public void seekTo(long position) {
		Log.i(TAG, "====seekTo====");
		mCustomPlayer.seekTo(position);
	}

	@Override
	public void stop() {
		Log.i(TAG, "====stop====");
		release();
	}

	@Override
	public void release() {
		releasePlayer();
	}

	@Override
	public int getDuration() {
		return (int) mCustomPlayer.getDuration();
	}

	@Override
	public int getCurrentPosition() {
		return (int) mCustomPlayer.getCurrentPosition();
	}

	@Override
	public void start() {
		Log.i(TAG, "====start====");
		mCustomPlayer.getPlayerControl().start();
	}

	@Override
	public void pause() {
		Log.i(TAG, "====pause====");
		mCustomPlayer.getPlayerControl().pause();
	}
	
	@Override
	public void onStateChanged(boolean playWhenReady, int playbackState) {
		
		if(mPlayState == null)
			return;
		
		switch (playbackState) {
		case ExoPlayer.STATE_BUFFERING: //加载中
			Log.i(TAG, "====STATE_BUFFERING====");
			mPlayState.onPlayState(playWhenReady, playbackState);
			break;
		case ExoPlayer.STATE_ENDED:
			Log.i(TAG, "====STATE_ENDED====");
			mPlayState.onPlayState(playWhenReady, playbackState);
			break;
		case ExoPlayer.STATE_IDLE://网络状态差，请检查网络
			Log.i(TAG, "====STATE_IDLE====");
			mPlayState.onPlayState(playWhenReady, playbackState);
			break;
		case ExoPlayer.STATE_PREPARING: //正在加载
			Log.i(TAG, "====STATE_PREPARING====");
			mPlayState.onPlayState(playWhenReady, playbackState);
			break;
		case ExoPlayer.STATE_READY:
			Log.i(TAG, "====STATE_READY====");
			mPlayState.onPlayState(playWhenReady, playbackState);
			break;
		default:
			break;
	}
	}

	@Override
	public void onError(Exception e) {
		String errorString = null;
		if (e instanceof UnsupportedDrmException) {
			// 鐗规畩鎯呭喌DRM鐨勫け璐�
			UnsupportedDrmException unsupportedDrmException = (UnsupportedDrmException) e;
			errorString = mContext.getString(Util.SDK_INT < 18 ? R.string.error_drm_not_supported
				: unsupportedDrmException.reason == UnsupportedDrmException.REASON_UNSUPPORTED_SCHEME
				? R.string.error_drm_unsupported_scheme : R.string.error_drm_unknown);
		} else if (e instanceof ExoPlaybackException
			&& e.getCause() instanceof MediaCodecTrackRenderer.DecoderInitializationException) {
			// Special case for decoder initialization failures.
			MediaCodecTrackRenderer.DecoderInitializationException decoderInitializationException =
				(MediaCodecTrackRenderer.DecoderInitializationException) e.getCause();
			if (decoderInitializationException.decoderName == null) {
				if (decoderInitializationException.getCause() instanceof MediaCodecUtil.DecoderQueryException) {
					errorString = mContext.getString(R.string.error_querying_decoders);
				} else if (decoderInitializationException.secureDecoderRequired) {
					errorString = mContext.getString(R.string.error_no_secure_decoder,
						decoderInitializationException.mimeType);
				} else {
					errorString = mContext.getString(R.string.error_no_decoder,
						decoderInitializationException.mimeType);
				}
			} else {
				errorString = mContext.getString(R.string.error_instantiating_decoder,
					decoderInitializationException.decoderName);
			}
		}
		if (errorString != null) {
			Toast.makeText(mContext.getApplicationContext(), errorString, Toast.LENGTH_LONG).show();
		}
		mPlayerNeedsPrepare = true;
	}

	@Override
	public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (mCustomPlayer != null)
			mCustomPlayer.setSurface(holder.getSurface());
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		if (mCustomPlayer !=null)
			mCustomPlayer.blockingClearSurface();
	}
}
