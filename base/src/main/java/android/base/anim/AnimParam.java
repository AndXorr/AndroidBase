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
 * The type Anim param.
 */
public class AnimParam {
    /**
     * The View.
     */
    protected View view;
    /**
     * The Context.
     */
    protected Context context;
    /**
     * The Activity context.
     */
    protected Activity activityContext;
    /**
     * The Duration.
     */
    protected int duration = AnimConstant.sDURATION_DEFAULT, /**
     * The Set x.
     */
    setX, /**
     * The Set y.
     */
    setY;
    /**
     * The Start radius.
     */
    protected float startRadius = 0, /**
     * The End radius.
     */
    endRadius = 0;
    /**
     * The Interpolator.
     */
    protected TimeInterpolator interpolator = AnimConstant.sTIME_INTERPOLATOR;
    /**
     * The Animator listener adapter.
     */
    protected AnimatorListenerAdapter animatorListenerAdapter;
    /**
     * The Type.
     */
    protected AnimType type;
    /**
     * The Pos x.
     */
    protected AnimPos posX;
    /**
     * The Pos y.
     */
    protected AnimPos posY;

    /**
     * The enum Anim type.
     */
    public enum AnimType {
        /**
         * The Circular reveal.
         */
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

        /**
         * Animate.
         *
         * @param animParam the anim param
         */
        public abstract void animate(AnimParam animParam);
    }

    /**
     * The enum Anim pos.
     */
    public enum AnimPos {
        /**
         * Left anim pos.
         */
        LEFT, /**
         * Right anim pos.
         */
        RIGHT, /**
         * Top anim pos.
         */
        TOP, /**
         * Bottom anim pos.
         */
        BOTTOM, /**
         * Center anim pos.
         */
        CENTER
    }
}
