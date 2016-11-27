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

    private AnimParam mAnimParam;

    /**
     * Instantiates a new Builder.
     *
     * @param context  the context
     * @param view     the mView
     * @param animType the anim type
     */
    public Builder(@NonNull Context context, @NonNull View view, AnimParam.AnimType animType) {
        mAnimParam = new AnimParam();
        mAnimParam.view = view;
        mAnimParam.context = context;
        mAnimParam.type = animType;
    }

    /**
     * Instantiates a new Builder.
     *
     * @param context  the context
     * @param view     the mView
     * @param animType the anim type
     */
    public Builder(@NonNull Activity context, @NonNull View view, AnimParam.AnimType animType) {
        mAnimParam = new AnimParam();
        mAnimParam.view = view;
        mAnimParam.context = context;
        mAnimParam.activityContext = context;
        mAnimParam.type = animType;
    }

    /**
     * Duration builder.
     *
     * @param duration the duration
     * @return the builder
     */
    public Builder duration(int duration) {
        mAnimParam.duration = duration;
        return this;
    }

    /**
     * Interpolator builder.
     *
     * @param interpolator the interpolator
     * @return the builder
     */
    public Builder interpolator(TimeInterpolator interpolator) {
        mAnimParam.interpolator = interpolator;
        return this;
    }

    /**
     * Listener builder.
     *
     * @param animatorListenerAdapter the animator listener adapter
     * @return the builder
     */
    public Builder listener(AnimatorListenerAdapter animatorListenerAdapter) {
        mAnimParam.animatorListenerAdapter = animatorListenerAdapter;
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
        mAnimParam.setX = x;
        mAnimParam.setY = y;
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
        mAnimParam.posX = x;
        mAnimParam.posY = y;
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
        mAnimParam.startRadius = startRadius;
        mAnimParam.endRadius = endRadius;
        return this;
    }

    /**
     * Start.
     */
    public void start() {
        if (mAnimParam.type == null) {
            Log.e(getClass().getName(), "Please Specify type");
        } else {
            if (mAnimParam.posX != null && mAnimParam.posY != null) {
                AnimUtil.getPos(mAnimParam);
            }
            mAnimParam.type.animate(mAnimParam);
        }
    }
}
