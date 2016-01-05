package android.base.anim;

import android.animation.TimeInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by clickapps on 9/12/15.
 */
public class AnimConstant {
    // Duration
    public static final int DURATION_DEFAULT = 300; // 300 ms
    public static final int DURATION_SHORT = 100;    // 100 ms
    public static final int DURATION_LONG = 500;    // 500 ms

    // Interpolator
    public static final TimeInterpolator TIME_INTERPOLATOR = new AccelerateDecelerateInterpolator();
}
