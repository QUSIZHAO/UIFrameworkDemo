package com.xbm.android.media.xbmplay;

/**
 * 播放接口
 * Created by xiaobaima on 17-8-23.
 */

public interface IMediaPlayer {
	/**
	 * 播放
	 * @param url
	 */
	void play(String url, IPlayState playState);

	/**
	 * 快进/快退
	 * @param position
	 */
	void seekTo(long position);

	/**
	 * 停止
	 */
	void stop();

	/**
	 * 释放资源
	 */
	void release();

	/**
	 * 总的进度
	 * @return
	 */
	int getDuration();

	/**
	 * 当前的位置
	 * @return
	 */
	int getCurrentPosition();
	/**
	 * 播放
	 */
	void start();
	/**
	 * 暂停
	 */
	void pause();
}
