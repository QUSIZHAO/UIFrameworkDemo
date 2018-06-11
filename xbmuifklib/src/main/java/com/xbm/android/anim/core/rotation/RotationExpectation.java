package com.xbm.android.anim.core.rotation;

import android.view.View;
import com.xbm.android.anim.core.AnimExpectation;

/**
 * Created by florentchampigny on 18/02/2017.
 */
public abstract class RotationExpectation extends AnimExpectation {

    public abstract Float getCalculatedRotation(View viewToMove);
    public abstract Float getCalculatedRotationX(View viewToMove);
    public abstract Float getCalculatedRotationY(View viewToMove);
}
