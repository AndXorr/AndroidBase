package android.base.anim;

import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.content.Context;
import android.view.View;

/**
 * Created by clickapps on 9/12/15.
 */
public class AnimParam {
    public View view;
    public Context context;
    public Activity activityContext;
    public int duration = AnimConstant.DURATION_DEFAULT, setX, setY;
    public float startRadius = 0, endRadius = 0;
    public TimeInterpolator interpolator = AnimConstant.TIME_INTERPOLATOR;
    public AnimatorListenerAdapter animatorListenerAdapter;
    public AnimType type;
    public AnimPos posX;
    public AnimPos posY;

    public enum AnimType {
        @SuppressWarnings("BUILD_VERSION_LOLLIPOP")CircularReveal
    }

    public enum AnimPos {
        LEFT, RIGHT, TOP, BOTTOM, CENTER
    }
}
