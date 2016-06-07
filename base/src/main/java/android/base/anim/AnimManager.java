package android.base.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.base.log.Log;
import android.base.util.ApplicationUtils;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewAnimationUtils;

/**
 * Created by clickapps on 9/12/15.
 */
public class AnimManager {
    private static volatile AnimManager sAnimManager;

    private AnimManager() {
        // private constructor
    }

    public static AnimManager get() {
        if (sAnimManager == null) {
            synchronized (AnimManager.class) {
                if (sAnimManager == null) {
                    sAnimManager = new AnimManager();
                }
            }
        }
        return sAnimManager;
    }

    public static Builder with(@NonNull Context context, @NonNull View view, AnimParam.AnimType animType) {
        return new Builder(context, view, animType);
    }

    public static Builder with(@NonNull Activity context, @NonNull View view, AnimParam.AnimType animType) {
        return new Builder(context, view, animType);
    }
}
