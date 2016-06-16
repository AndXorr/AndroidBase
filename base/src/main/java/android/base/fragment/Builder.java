package android.base.fragment;

import android.app.Fragment;
import android.base.R;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;


/**
 * The type Builder.
 */
public class Builder {
    private FragParam fragParam;

    /**
     * Instantiates a new Builder.
     *
     * @param context   the context
     * @param replaceId the replace id
     */
    public Builder(@NonNull FragmentActivity context, @IdRes int replaceId) {
        fragParam = new FragParam();
        fragParam.context = context;
        fragParam.replaceId = replaceId;
        defaultAnim();
    }

    /**
     * Fragment builder.
     *
     * @param fragment the fragment
     * @return the builder
     */
    public Builder fragment(@NonNull Fragment fragment) {
        fragParam.fragment = fragment;
        return this;
    }

    /**
     * Tag builder.
     *
     * @param tag the tag
     * @return the builder
     */
    public Builder tag(@NonNull String tag) {
        fragParam.tag = tag;
        return this;
    }

    /**
     * Enable animation builder.
     *
     * @param enableAnimation the enable animation
     * @return the builder
     */
    public Builder enableAnimation(boolean enableAnimation) {
        fragParam.enableAnimation = enableAnimation;
        return this;
    }

    /**
     * Type builder.
     *
     * @param fragType the frag type
     * @return the builder
     */
    public Builder type(@NonNull FragParam.FragType fragType) {
        fragParam.fragType = fragType;
        return this;
    }

    /**
     * Back stack builder.
     *
     * @param isBackStack the is back stack
     * @return the builder
     */
    public Builder backStack(boolean isBackStack) {
        fragParam.isBackStack = isBackStack;
        return this;
    }

    /**
     * Animation builder.
     *
     * @param enter    the enter
     * @param exit     the exit
     * @param popEnter the pop enter
     * @param popExit  the pop exit
     * @return the builder
     */
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

    /**
     * Build.
     */
    public void build() {
        if (fragParam.context == null) {
            Log.e(getClass().getSimpleName(), "Context is null");
            return;
        }
        FragmentManagerUtil.performTask(fragParam);
    }
}
