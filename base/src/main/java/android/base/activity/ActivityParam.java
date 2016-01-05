package android.base.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;

/**
 * Created by Sahni on 10-11-2015.
 */
public class ActivityParam {

    public Activity context;
    public Class<?> uri;
    public int enter = 0, exit = 0, requestCode, flag = 0;
    public ActivityType activityType = ActivityType.START;
    public Bundle bundle;
    public ActivityOptionsCompat activityOptionsCompat;

    public boolean enableAnimation = false, isBackStack = false;

    public enum ActivityType {
        START, START_RESULT, FINISH, START_FINISH, START_RESULT_FINISH
    }
}
