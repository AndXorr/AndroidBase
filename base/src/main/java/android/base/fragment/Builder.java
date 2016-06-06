package android.base.fragment;

import android.app.Fragment;
import android.base.R;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * Created by sahni on 6/6/16.
 */
public class Builder {
    private FragParam fragParam;

    public Builder(@NonNull FragmentActivity context, @IdRes int replaceId) {
        fragParam = new FragParam();
        fragParam.context = context;
        fragParam.replaceId = replaceId;
        defaultAnim();
    }

    public Builder fragment(@NonNull Fragment fragment) {
        fragParam.fragment = fragment;
        return this;
    }

    public Builder tag(@NonNull String tag) {
        fragParam.tag = tag;
        return this;
    }

    public Builder enableAnimation(boolean enableAnimation) {
        fragParam.enableAnimation = enableAnimation;
        return this;
    }

    public Builder type(@NonNull FragParam.FragType fragType) {
        fragParam.fragType = fragType;
        return this;
    }

    public Builder backStack(boolean isBackStack) {
        fragParam.isBackStack = isBackStack;
        return this;
    }

    public Builder animation(int enter, int exit, int popEnter, int popExit) {
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
