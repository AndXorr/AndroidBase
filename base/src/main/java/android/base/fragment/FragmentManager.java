package android.base.fragment;

import android.base.R;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by clickapps on 20/7/15.
 */
public class FragmentManager {

    public static class Builder {
        private FragParam fragParam;

        public Builder(@NonNull FragmentActivity context, int replaceId) {
            fragParam = new FragParam();
            fragParam.context = context;
            fragParam.replaceId = replaceId;
            defaultAnim();
        }

        public Builder setFragment(@NonNull Fragment fragment) {
            fragParam.fragment = fragment;
            return this;
        }

        public Builder setTag(@NonNull String tag) {
            fragParam.tag = tag;
            return this;
        }

        public Builder enableAnimation(boolean enableAnimation) {
            fragParam.enableAnimation = enableAnimation;
            return this;
        }

        public Builder setFragType(@NonNull FragParam.FragType fragType) {
            fragParam.fragType = fragType;
            return this;
        }

        public Builder maintainBackStack(boolean isBackStack) {
            fragParam.isBackStack = isBackStack;
            return this;
        }

        public Builder setAnimation(int enter, int exit, int popEnter, int popExit) {
            fragParam.enter = enter;
            fragParam.exit = exit;
            fragParam.popEnter = popEnter;
            fragParam.popExit = popExit;
            return this;
        }

        private Builder defaultAnim() {
            fragParam.enter = R.anim.slide_in_right;
            fragParam.exit = R.anim.slide_out_right;
            fragParam.popEnter = R.anim.slide_in_left;
            fragParam.popExit = R.anim.slide_out_left;
            return this;
        }

        public void build() {
            if (fragParam.context == null) {
                Log.e(getClass().getSimpleName(), "Context is null");
                return;
            }
            FragmentManagerUtil.performTask(fragParam);
        }
    }
}
