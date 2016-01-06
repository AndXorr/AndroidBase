package android.base.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

/**
 * Created by clickapps on 24/11/15.
 */
public class FragmentManagerUtil {

    public static void performTask(FragParam fragParam) {
        FragmentTransaction ft = fragParam.context.getSupportFragmentManager()
                .beginTransaction();
        if (fragParam.enableAnimation) {
            ft.setCustomAnimations(fragParam.enter, fragParam.exit, fragParam.popEnter, fragParam.popExit);
        }
        FragParam.FragType fragType = fragParam.fragType;
        switch (fragType) {
            case REPLACE:
                if (!TextUtils.isEmpty(fragParam.tag)) {
                    ft.replace(fragParam.replaceId, fragParam.fragment, fragParam.tag);
                } else {
                    ft.replace(fragParam.replaceId, fragParam.fragment);
                }
                if (fragParam.isBackStack) {
                    ft.addToBackStack(fragParam.tag);
                }
                ft.commit();
                break;
            case POP:
            case POP_TAG:
                if (fragParam.context.getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    if (fragType == FragParam.FragType.POP) {
                        ft.remove(fragParam.context.getSupportFragmentManager()
                                .findFragmentById(fragParam.replaceId));
                    } else if (fragType == FragParam.FragType.POP_TAG) {
                        ft.remove(fragParam.context.getSupportFragmentManager()
                                .findFragmentByTag(fragParam.tag));
                    }
                    ft.commit();
                    fragParam.context.getSupportFragmentManager().popBackStackImmediate();
                }
                break;
            case RESTART:
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
                break;
            case CLEAR:
                fragParam.context.getSupportFragmentManager()
                        .popBackStackImmediate(null, android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
                break;
        }
    }
}