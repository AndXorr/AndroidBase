package android.base.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.base.util.ApplicationUtils;
import android.content.Context;
import android.view.View;
import android.view.ViewAnimationUtils;

/**
 * Created by clickapps on 9/12/15.
 */
public class AnimParam {
    protected View view;
    protected Context context;
    protected Activity activityContext;
    protected int duration = AnimConstant.DURATION_DEFAULT, setX, setY;
    protected float startRadius = 0, endRadius = 0;
    protected TimeInterpolator interpolator = AnimConstant.TIME_INTERPOLATOR;
    protected AnimatorListenerAdapter animatorListenerAdapter;
    protected AnimType type;
    protected AnimPos posX;
    protected AnimPos posY;

    public enum AnimType {
        @SuppressWarnings("BUILD_VERSION_LOLLIPOP")CircularReveal {
            @Override
            public void animate(AnimParam animParam) {
                if (ApplicationUtils.System.isLollipop()) {
                    Animator anim =
                            ViewAnimationUtils.createCircularReveal(animParam.view, animParam.setX,
                                    animParam.setY, animParam.startRadius, animParam.endRadius);
                    anim.setInterpolator(animParam.interpolator);
                    anim.setDuration(animParam.duration);
                    if (animParam.animatorListenerAdapter != null)
                        anim.addListener(animParam.animatorListenerAdapter);
                    anim.start();
                }
            }
        };

        public abstract void animate(AnimParam animParam);
    }

    public enum AnimPos {
        LEFT, RIGHT, TOP, BOTTOM, CENTER
    }
}
