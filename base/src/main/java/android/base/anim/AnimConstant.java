package android.base.anim;

import android.animation.TimeInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;


/**
 * The type Anim constant.
 */
public class AnimConstant {
    /**
     * The constant DURATION_DEFAULT.
     */
// Duration
    public static final int DURATION_DEFAULT = 300; // 300 ms
    /**
     * The constant DURATION_SHORT.
     */
    public static final int DURATION_SHORT = 100;    // 100 ms
    /**
     * The constant DURATION_LONG.
     */
    public static final int DURATION_LONG = 500;    // 500 ms

    /**
     * The constant TIME_INTERPOLATOR.
     */
// Interpolator
    public static final TimeInterpolator TIME_INTERPOLATOR = new AccelerateDecelerateInterpolator();
}
