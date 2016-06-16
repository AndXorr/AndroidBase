package android.base.anim;

import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.base.log.Log;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;


/**
 * The type Builder.
 */
public class Builder {

    private AnimParam animParam;

    /**
     * Instantiates a new Builder.
     *
     * @param context  the context
     * @param view     the view
     * @param animType the anim type
     */
    public Builder(@NonNull Context context, @NonNull View view, AnimParam.AnimType animType) {
        animParam = new AnimParam();
        animParam.view = view;
        animParam.context = context;
        animParam.type = animType;
    }

    /**
     * Instantiates a new Builder.
     *
     * @param context  the context
     * @param view     the view
     * @param animType the anim type
     */
    public Builder(@NonNull Activity context, @NonNull View view, AnimParam.AnimType animType) {
        animParam = new AnimParam();
        animParam.view = view;
        animParam.context = context;
        animParam.activityContext = context;
        animParam.type = animType;
    }

    /**
     * Duration builder.
     *
     * @param duration the duration
     * @return the builder
     */
    public Builder duration(int duration) {
        animParam.duration = duration;
        return this;
    }

    /**
     * Interpolator builder.
     *
     * @param interpolator the interpolator
     * @return the builder
     */
    public Builder interpolator(TimeInterpolator interpolator) {
        animParam.interpolator = interpolator;
        return this;
    }

    /**
     * Listener builder.
     *
     * @param animatorListenerAdapter the animator listener adapter
     * @return the builder
     */
    public Builder listener(AnimatorListenerAdapter animatorListenerAdapter) {
        animParam.animatorListenerAdapter = animatorListenerAdapter;
        return this;
    }

    /**
     * Pos builder.
     *
     * @param x the x
     * @param y the y
     * @return the builder
     */
    public Builder pos(int x, int y) {
        animParam.setX = x;
        animParam.setY = y;
        return this;
    }

    /**
     * Pos builder.
     *
     * @param x the x
     * @param y the y
     * @return the builder
     */
    public Builder pos(@NonNull AnimParam.AnimPos x, @NonNull AnimParam.AnimPos y) {
        animParam.posX = x;
        animParam.posY = y;
        return this;
    }

    /**
     * Radius builder.
     *
     * @param startRadius the start radius
     * @param endRadius   the end radius
     * @return the builder
     */
    public Builder radius(float startRadius, float endRadius) {
        animParam.startRadius = startRadius;
        animParam.endRadius = endRadius;
        return this;
    }

    /**
     * Start.
     */
    public void start() {
        if (animParam.type == null) {
            Log.e(getClass().getName(), "Please Specify type");
        } else {
            if (animParam.posX != null && animParam.posY != null) {
                AnimUtil.getPos(animParam);
            }
            animParam.type.animate(animParam);
        }
    }
}
