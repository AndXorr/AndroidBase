package android.base.activity;

import android.app.Activity;
import android.base.R;
import android.base.util.ApplicationUtils;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.Log;

/**
 * Created by clickapps on 20/7/15.
 */
public class ActivityManager {

    public static class Builder {
        private ActivityParam activityParam;

        public Builder(@NonNull Activity context) {
            activityParam = new ActivityParam();
            activityParam.context = context;
            defaultAnim();
        }

        public Builder(@NonNull Activity context, ActivityParam.ActivityType activityType) {
            activityParam = new ActivityParam();
            activityParam.context = context;
            activityParam.activityType = activityType;
            defaultAnim();
        }

        public Builder bundle(Bundle bundle) {
            activityParam.bundle = bundle;
            return this;
        }

        public Builder setClass(Class<?> uri) {
            activityParam.uri = uri;
            return this;
        }

        public Builder enableAnimation(boolean enableAnimation) {
            activityParam.enableAnimation = enableAnimation;
            return this;
        }

        public Builder setActivityType(@NonNull ActivityParam.ActivityType activityType) {
            activityParam.activityType = activityType;
            return this;
        }

        public Builder requestCode(int requestCode) {
            activityParam.requestCode = requestCode;
            return this;
        }

        /**
         * if application OS is greater than Lollipop then its @TransitionRes
         * otherwise its @AnimRes
         */
        public Builder setStartAnimation(int enter, int exit) {
            activityParam.enter = enter;
            activityParam.exit = exit;
            return this;
        }


        public Builder setFlag(int flag) {
            activityParam.flag = flag;
            return this;
        }

        public Builder setActivityCompactOption(ActivityOptionsCompat activityOptionsCompat) {
            activityParam.activityOptionsCompat = activityOptionsCompat;
            return this;
        }

        private Builder defaultAnim() {
            if (ApplicationUtils.isLollipop()) {
                activityParam.enter = activityParam.exit = R.transition.activity_slide;
            } else {
                activityParam.enter = R.anim.slide_in_left;
                activityParam.exit = R.anim.slide_out_left;
            }
            return this;
        }

        public void build() {
            if (activityParam.context == null) {
                Log.e(getClass().getSimpleName(), "Context is null");
                return;
            }
            ActivityManagerUtil.performTask(activityParam);
        }
    }
}
