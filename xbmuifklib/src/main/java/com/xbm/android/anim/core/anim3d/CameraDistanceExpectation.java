package com.xbm.android.anim.core.anim3d;

import android.support.annotation.Nullable;
import android.view.View;
import com.xbm.android.anim.core.AnimExpectation;

/**
 * Created by Christian Ringshofer on 17/02/2017.
 */
public abstract class CameraDistanceExpectation extends AnimExpectation {

    @Nullable
    public abstract Float getCalculatedCameraDistance(View viewToMove);

}
