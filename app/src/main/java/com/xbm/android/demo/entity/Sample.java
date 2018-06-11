package com.xbm.android.demo.entity;

import android.databinding.BindingAdapter;
import android.support.annotation.ColorRes;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by lgvalle on 04/09/15.
 */
public class Sample implements Serializable {

    public final int color;
    private final String name;
    private static final String TAG = "Sample";

    public Sample(@ColorRes int color, String name) {
        this.color = color;
        this.name = name;
    }

    @BindingAdapter("colorTint")
    public static void setColorTint(ImageView view, @ColorRes int color) {
        Log.i(TAG, "setColorTint===>>" + "color=" + color);
        DrawableCompat.setTint(view.getDrawable(), color);
        //view.setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }


}
