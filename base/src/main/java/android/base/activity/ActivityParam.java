package android.base.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;

/**
 * Created by Sahni on 10-11-2015.
 */
public class ActivityParam {

    protected Activity context;
    protected Class<?> uri;
    protected int enter = 0, exit = 0, requestCode = 0, flag = 0;
    protected ActivityType activityType = ActivityType.START;
    protected Bundle bundle;
    protected ActivityOptionsCompat activityOptionsCompat;

    protected boolean enableAnimation = false, isBackStack = false;

    public enum ActivityType {
        START, START_RESULT, FINISH, START_FINISH, START_RESULT_FINISH
    }
}
