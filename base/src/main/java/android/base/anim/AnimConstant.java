package android.base.anim;

import android.animation.TimeInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;


/**
 * The type Anim constant.
 */
public class AnimConstant {
    /**
     * The constant sDURATION_DEFAULT.
     */
// Duration
    public static final int sDURATION_DEFAULT = 300; // 300 ms
    /**
     * The constant sDURATION_SHORT.
     */
    public static final int sDURATION_SHORT = 100;    // 100 ms
    /**
     * The constant sDURATION_LONG.
     */
    public static final int sDURATION_LONG = 500;    // 500 ms

    /**
     * The constant sTIME_INTERPOLATOR.
     */
// Interpolator
    public static final TimeInterpolator sTIME_INTERPOLATOR = new AccelerateDecelerateInterpolator();
}
