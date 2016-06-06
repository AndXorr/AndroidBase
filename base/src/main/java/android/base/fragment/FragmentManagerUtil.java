package android.base.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.text.TextUtils;

/**
 * Created by clickapps on 24/11/15.
 */
public class FragmentManagerUtil {

    public static void performTask(FragParam fragParam) {
        FragmentTransaction ft = fragParam.context.getFragmentManager()
                .beginTransaction();
        if (fragParam.enableAnimation) {
            ft.setCustomAnimations(fragParam.enter, fragParam.exit, fragParam.popEnter, fragParam.popExit);
        }
        fragParam.fragType.execute(fragParam, ft);
    }

    protected static void replace(FragParam fragParam, FragmentTransaction ft) {
        if (!TextUtils.isEmpty(fragParam.tag)) {
            ft.replace(fragParam.replaceId, fragParam.fragment, fragParam.tag);
        } else {
            ft.replace(fragParam.replaceId, fragParam.fragment);
        }
        if (fragParam.isBackStack) {
            ft.addToBackStack(fragParam.tag);
        }
        ft.commit();
    }

    protected static void pop(FragParam fragParam, FragmentTransaction ft) {
        if (fragParam.context.getSupportFragmentManager().getBackStackEntryCount() > 0) {
            ft.remove(fragParam.context.getFragmentManager()
                    .findFragmentById(fragParam.replaceId));
            ft.commit();
            fragParam.context.getSupportFragmentManager().popBackStackImmediate();
        }
    }

    protected static void popTag(FragParam fragParam, FragmentTransaction ft) {
        if (fragParam.context.getSupportFragmentManager().getBackStackEntryCount() > 0) {
            ft.remove(fragParam.context.getFragmentManager()
                    .findFragmentByTag(fragParam.tag));
            ft.commit();
            fragParam.context.getSupportFragmentManager().popBackStackImmediate();
        }
    }

    protected static void restart(FragParam fragParam, FragmentTransaction ft) {
        try {
            Fragment fragment = FragUtil.getFragment(fragParam.context, fragParam.replaceId);
            if (fragment != null) {
                ft.detach(fragment);
                ft.attach(fragment);
                ft.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static void clear(FragParam fragParam) {
        fragParam.context.getSupportFragmentManager()
                .popBackStackImmediate(null, android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
