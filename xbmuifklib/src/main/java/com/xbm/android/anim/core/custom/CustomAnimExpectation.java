package com.xbm.android.anim.core.custom;

import android.animation.Animator;
import android.view.View;
import com.xbm.android.anim.core.AnimExpectation;

/**
 * Created by florentchampigny on 21/02/2017.
 */

public abstract class CustomAnimExpectation extends AnimExpectation {
    public abstract Animator getAnimator(View viewToMove);
}
