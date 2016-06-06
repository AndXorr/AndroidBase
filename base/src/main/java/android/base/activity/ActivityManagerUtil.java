package android.base.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.base.R;
import android.base.constant.Constant;
import android.base.util.ApplicationUtils;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.TransitionRes;
import android.support.v4.app.ActivityCompat;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.transition.TransitionSet;

/**
 * Created by clickapps on 24/11/15.
 */
public class ActivityManagerUtil {

    private ActivityManagerUtil() {
        // Nothing
    }

    private static Activity sCurrentActivity;

    protected static void performTask(ActivityParam activityParam) {
        ActivityParam.ActivityType activityType = activityParam.activityType;
        switch (activityType) {
            case START:
            case START_FINISH:
                start(activityParam);
                break;
            case START_RESULT:
            case START_RESULT_FINISH:

                break;
            case FINISH:
                finish(activityParam);
                break;
            default:
                break;
        }
    }

    private static void start(ActivityParam activityParam) {
        Intent intent = new Intent(activityParam.context, activityParam.uri);
        if (activityParam.flag == 0) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_NEW_TASK);
        } else {
            intent.setFlags(activityParam.flag);
        }
        if (activityParam.bundle != null) {
            intent.putExtras(activityParam.bundle);
        }
        if (activityParam.activityOptionsCompat != null && ApplicationUtils.System.isLollipop()) {
            activityParam.context.startActivity(intent, activityParam.activityOptionsCompat.toBundle());
        } else {
            activityParam.context.startActivity(intent);
            if (activityParam.enableAnimation
                    && activityParam.enter > 0
                    && activityParam.exit > 0)
                activityParam.context.overridePendingTransition(activityParam.enter, activityParam.exit);
        }
        if (activityParam.activityType == ActivityParam.ActivityType.START_FINISH) {
            finish(activityParam);
        }
    }

    private static void startResult(ActivityParam activityParam) {
        Intent intent = new Intent(activityParam.context, activityParam.uri);
        if (activityParam.flag == 0) {
        } else {
            intent.setFlags(activityParam.flag);
        }
        if (activityParam.bundle != null) {
            intent.putExtras(activityParam.bundle);
        }
        if (activityParam.activityOptionsCompat != null && ApplicationUtils.System.isLollipop()) {
            activityParam.context.startActivityForResult(intent, activityParam.requestCode, activityParam.activityOptionsCompat.toBundle());
        } else {
            activityParam.context.startActivityForResult(intent, activityParam.requestCode);
            if (activityParam.enableAnimation
                    && activityParam.enter > 0
                    && activityParam.exit > 0)
                activityParam.context.overridePendingTransition(activityParam.enter, activityParam.exit);
        }
        if (activityParam.activityType == ActivityParam.ActivityType.START_RESULT_FINISH) {
            finish(activityParam);
        }
    }

    private static void finish(ActivityParam activityParam) {
        ActivityCompat.finishAfterTransition(activityParam.context);
        if (!ApplicationUtils.System.isLollipop()) {
            if (activityParam.enableAnimation && activityParam.enter > 0 && activityParam.exit > 0)
                activityParam.context.overridePendingTransition(activityParam.enter, activityParam.exit);
        }
    }

    @TargetApi(Constant.BUILD_VERSION_KITKAT)
    public static TransitionManager getTransitionManager() {
        return new TransitionManager();
    }

    @TargetApi(Constant.BUILD_VERSION_KITKAT)
    public static Slide getSlide() {
        Slide slide = new Slide();
        slide.setDuration(1000);
        return slide;
    }

    @TargetApi(Constant.BUILD_VERSION_KITKAT)
    public static Slide getSlide(Context context) {
        Transition transition = TransitionInflater.from(context).inflateTransition(R.transition.activity_slide);
        return (Slide) transition;
    }

    @TargetApi(Constant.BUILD_VERSION_KITKAT)
    public static Transition getTransition(Context context, @TransitionRes int resId) {
        return TransitionInflater.from(context).inflateTransition(resId);
    }

    @TargetApi(Constant.BUILD_VERSION_KITKAT)
    public static TransitionSet getTransitionSet(Transition... transition) {
        TransitionSet transitionSet = new TransitionSet();
        for (Transition t : transition) {
            transitionSet.addTransition(t);
        }
        return transitionSet;
    }

    public static Activity getTopActivity() {
        return sCurrentActivity;
    }

    /**
     * Only set from BaseApplication
     *
     * @param currentActivity
     */
    @TargetApi(Constant.BUILD_VERSION_ICE_CREAM_SANDWICH)
    public static void setTopActivity(@NonNull Activity currentActivity) {
        ActivityManagerUtil.sCurrentActivity = currentActivity;
    }
}
