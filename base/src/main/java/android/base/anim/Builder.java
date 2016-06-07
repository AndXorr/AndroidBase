package android.base.anim;

import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.base.log.Log;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by sahni on 6/6/16.
 */
public class Builder {

    private AnimParam animParam;

    public Builder(@NonNull Context context, @NonNull View view, AnimParam.AnimType animType) {
        animParam = new AnimParam();
        animParam.view = view;
        animParam.context = context;
    }

    public Builder(@NonNull Activity context, @NonNull View view, AnimParam.AnimType animType) {
        animParam = new AnimParam();
        animParam.view = view;
        animParam.context = context;
        animParam.activityContext = context;
    }

    public Builder duration(int duration) {
        animParam.duration = duration;
        return this;
    }

    public Builder interpolator(TimeInterpolator interpolator) {
        animParam.interpolator = interpolator;
        return this;
    }

    public Builder listener(AnimatorListenerAdapter animatorListenerAdapter) {
        animParam.animatorListenerAdapter = animatorListenerAdapter;
        return this;
    }

    public Builder pos(int x, int y) {
        animParam.setX = x;
        animParam.setY = y;
        return this;
    }

    public Builder pos(@NonNull AnimParam.AnimPos x, @NonNull AnimParam.AnimPos y) {
        animParam.posX = x;
        animParam.posY = y;
        return this;
    }

    public Builder radius(float startRadius, float endRadius) {
        animParam.startRadius = startRadius;
        animParam.endRadius = endRadius;
        return this;
    }

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
