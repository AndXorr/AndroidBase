package android.base.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.app.Activity;
import android.base.log.Log;
import android.base.util.ApplicationUtils;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewAnimationUtils;

/**
 * Created by clickapps on 9/12/15.
 */
public class AnimManager {

    public static class Builder {
        private AnimParam animParam;

        public Builder(@NonNull Context context, @NonNull View view) {
            animParam = new AnimParam();
            animParam.view = view;
            animParam.context = context;
        }

        public Builder(@NonNull Activity context, @NonNull View view) {
            animParam = new AnimParam();
            animParam.view = view;
            animParam.context = context;
            animParam.activityContext = context;
        }

        public Builder setDuration(int duration) {
            animParam.duration = duration;
            return this;
        }

        public Builder setInterpolator(TimeInterpolator interpolator) {
            animParam.interpolator = interpolator;
            return this;
        }

        public Builder addListener(AnimatorListenerAdapter animatorListenerAdapter) {
            animParam.animatorListenerAdapter = animatorListenerAdapter;
            return this;
        }

        public Builder animationType(AnimParam.AnimType animType) {
            animParam.type = animType;
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
                throw new RuntimeException("Please Specify type");
            }
            if (animParam.posX != null && animParam.posY != null)
                AnimUtil.getPos(animParam);
            switch (animParam.type) {
                case CircularReveal:
                    if (ApplicationUtils.isLollipop()) {
                        Animator anim =
                                ViewAnimationUtils.createCircularReveal(animParam.view, animParam.setX,
                                        animParam.setY, animParam.startRadius, animParam.endRadius);
                        anim.setInterpolator(animParam.interpolator);
                        anim.setDuration(animParam.duration);
                        if (animParam.animatorListenerAdapter != null)
                            anim.addListener(animParam.animatorListenerAdapter);
                        anim.start();
                    }
                    break;
            }
        }
    }

}
