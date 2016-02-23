package android.base.application;

import android.app.Activity;
import android.app.Application;
import android.base.activity.ActivityManagerUtil;
import android.base.constant.Constant;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by clickapps on 9/2/16.
 */
public class BaseApplication extends Application implements Application.ActivityLifecycleCallbacks {
    private Receiver receiver;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        ActivityManagerUtil.setTopActivity(activity);
        receiver = new Receiver(activity);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter(Constant.ACTION_BROADCAST_LANGUAGE_CHANGED));
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(
                receiver);
    }

    public class Receiver extends BroadcastReceiver {
        private Activity activity;

        public Receiver(Activity activity) {
            this.activity = activity;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (activity != null)
                activity.recreate();
        }
    }
}
