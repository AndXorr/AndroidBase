package android.base.application;

import android.app.Activity;
import android.app.Application;
import android.base.activity.ActivityManagerUtil;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;


/**
 * The type Base application.
 */
public class BaseApplication extends Application implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        // Nothing used
    }

    @Override
    public void onActivityStarted(Activity activity) {
        // Nothing used
    }

    @Override
    public void onActivityResumed(Activity activity) {
        ActivityManagerUtil.setTopActivity(activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        // Nothing used
    }

    @Override
    public void onActivityStopped(Activity activity) {
        // Nothing used
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        // Nothing used
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        // Nothing used
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
