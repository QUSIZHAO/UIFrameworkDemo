package com.xbm.android.demo;

import android.app.Application;
import com.aitangba.swipeback.ActivityLifecycleHelper;

public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(ActivityLifecycleHelper.build());
    }

}
