package android.base.util.categories;

import android.support.annotation.AnimRes;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by cesarferreira on 28/05/14.
 */
public class AnimationUtil {


    protected AnimationUtil() {
    }

    /**
     * Make a View Blink for a desired duration
     *
     * @param view     view that will be animated
     * @param duration for how long in ms will it blink
     * @param offset   start offset of the animation
     * @return returns the same view with animation properties
     */
    public static View blink(View view, int duration, int offset) {
        android.view.animation.Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(duration);
        anim.setStartOffset(offset);
        anim.setRepeatMode(android.view.animation.Animation.REVERSE);
        anim.setRepeatCount(android.view.animation.Animation.INFINITE);
        view.startAnimation(anim);
        return view;
    }

    public static void animate(View view, @AnimRes int anim) {
        android.view.animation.Animation animation =
                AnimationUtils.loadAnimation(view.getContext(), anim);
        view.startAnimation(animation);
    }
}
