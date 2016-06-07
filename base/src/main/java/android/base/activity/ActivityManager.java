package android.base.activity;

import android.app.Activity;
import android.base.R;
import android.base.util.ApplicationUtils;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.Log;

/**
 * Created by clickapps on 20/7/15.
 */
public class ActivityManager {
    private static volatile ActivityManager sActivityManager;

    private ActivityManager() {

    }

    public static ActivityManager get() {
        if (sActivityManager == null) {
            synchronized (ActivityManager.class) {
                if (sActivityManager == null) {
                    sActivityManager = new ActivityManager();
                }
            }
        }
        return sActivityManager;
    }

    public static Builder with(@NonNull Activity context) {
        return new Builder(context);
    }


    public static Builder with(@NonNull Activity context, ActivityParam.ActivityType activityType) {
        return new Builder(context, activityType);
    }


}
