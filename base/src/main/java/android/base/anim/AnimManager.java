package android.base.anim;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;


/**
 * The type Anim manager.
 */
public class AnimManager {
    private static volatile AnimManager sAnimManager;

    private AnimManager() {
        // private constructor
    }

    /**
     * Get anim manager.
     *
     * @return the anim manager
     */
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

    /**
     * With builder.
     *
     * @param context  the context
     * @param view     the view
     * @param animType the anim type
     * @return the builder
     */
    public static Builder with(@NonNull Context context, @NonNull View view, AnimParam.AnimType animType) {
        return new Builder(context, view, animType);
    }

    /**
     * With builder.
     *
     * @param context  the context
     * @param view     the view
     * @param animType the anim type
     * @return the builder
     */
    public static Builder with(@NonNull Activity context, @NonNull View view, AnimParam.AnimType animType) {
        return new Builder(context, view, animType);
    }
}
