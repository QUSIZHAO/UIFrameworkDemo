package com.xbm.android.media.exoplay;


import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.MediaController;


public class KeyCompatibleMediaController extends MediaController {
    private MediaPlayerControl control;
    public KeyCompatibleMediaController(Context context) {
        super(context);
    }

    @Override
    public void setMediaPlayer(MediaPlayerControl player) {
        super.setMediaPlayer(player);
        control = player;
    }

    private static final String TAG = "KeyCompatibleMediaController";
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (control.canSeekForward() && (keyCode == KeyEvent.KEYCODE_MEDIA_FAST_FORWARD || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)){
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                control.seekTo(control.getCurrentPosition() + 5000); //unit:millsecond
                show();
            }
            return true;
        }else if (control.canSeekBackward() && (keyCode == KeyEvent.KEYCODE_MEDIA_FAST_FORWARD || keyCode == KeyEvent.KEYCODE_DPAD_LEFT)){
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                control.seekTo(control.getCurrentPosition() - 5000);
                show();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
