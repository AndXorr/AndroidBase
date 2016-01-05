package android.base.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sahni on 19-11-2015.
 */
public class FragUtil {

    /**
     * This method is used to popFragment from stack
     *
     * @Waring This methods runs on UI Thread
     */
    public static final void popFragment(FragmentActivity activity, int replaceId) {
        if (activity == null)
            return;
        if (activity.getSupportFragmentManager().getBackStackEntryCount() > 0) {
            FragmentTransaction fragTrans = activity
                    .getSupportFragmentManager().beginTransaction();
            fragTrans.remove(activity.getSupportFragmentManager()
                    .findFragmentById(replaceId));
            fragTrans.commit();
            activity.getSupportFragmentManager().popBackStackImmediate();
        }
    }

    /**
     * This method is used to popFragment from stack
     *
     * @Waring This methods runs on UI Thread
     */
    public static final void popFragment(FragmentActivity activity, String tag) {
        if (activity == null)
            return;
        if (activity.getSupportFragmentManager().getBackStackEntryCount() > 0) {
            FragmentTransaction fragTrans = activity
                    .getSupportFragmentManager().beginTransaction();
            fragTrans.remove(activity.getSupportFragmentManager()
                    .findFragmentByTag(tag));
            fragTrans.commit();
            activity.getSupportFragmentManager().popBackStackImmediate();
        }
    }

    /**
     * This method is used to clear all the fragments from stack
     */
    public static final void clearBackStack(FragmentActivity activity) {
        try {
            if (activity == null)
                return;
            android.support.v4.app.FragmentManager fm = activity.getSupportFragmentManager();
            for (int i = 0; i < fm.getBackStackEntryCount(); i++) {
                String fragTag = fm.getBackStackEntryAt(i).getName();
                Fragment fragment = fm.findFragmentByTag(fragTag);
                FragmentTransaction fragTrans = activity
                        .getSupportFragmentManager().beginTransaction();
                fragTrans.remove(fragment);
                fragTrans.commit();
                fm.popBackStack();
            }

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to remove all the stack in async
     */
    public static final void clearAllStack(FragmentActivity activity) {
        if (activity == null)
            return;
        android.support.v4.app.FragmentManager fm = activity.getSupportFragmentManager();
        fm.popBackStack(null, android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    /**
     * This method is used to remove all the stack in sync
     *
     * @Waring This methods runs on UI Thread
     */
    public static final void clearAllStackImmediate(FragmentActivity activity) {
        if (activity == null)
            return;
        android.support.v4.app.FragmentManager fm = activity.getSupportFragmentManager();
        fm.popBackStackImmediate(null, android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    /**
     * This method is used to get the top fragmnet on the stack
     *
     * @return {@link Fragment}
     */
    public static final Fragment getTopFragment(FragmentActivity activity) {
        if (activity == null)
            return null;
        android.support.v4.app.FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = null;
        for (int entry = 0; entry < fm.getBackStackEntryCount(); entry++) {
            fragment = fm.findFragmentByTag(fm.getBackStackEntryAt(entry)
                    .getName());
        }
        return fragment;
    }

    /**
     * This method is used to get List of backstack fragments
     *
     * @return {@link List}
     */
    public static final List<String> getStackList(FragmentActivity activity) {
        if (activity == null)
            return null;
        List<String> stackList = new ArrayList<String>();
        stackList.clear();
        android.support.v4.app.FragmentManager fm = activity.getSupportFragmentManager();
        for (int entry = 0; entry < fm.getBackStackEntryCount(); entry++) {
            stackList.add(fm.getBackStackEntryAt(entry).getName());
        }
        return stackList;
    }

    /**
     * This method is used to get the fragment
     *
     * @param id set UniqueId
     * @return {@link Fragment}
     */
    public static Fragment getFragment(FragmentActivity activity, int id) {
        if (activity == null)
            return null;
        return activity.getSupportFragmentManager().findFragmentById(id);
    }

    /**
     * This method is used to get the fragment
     *
     * @param tag set UniqueTag
     * @return {@link Fragment}
     */
    public static Fragment getFragment(FragmentActivity activity, String tag) {
        if (activity == null)
            return null;
        return activity.getSupportFragmentManager().findFragmentByTag(tag);
    }

    /**
     * Functionality to refresh or restart a fragment
     *
     * @param activity
     * @param fragmentId resource id for fragment
     */
    public static void restartFragment(FragmentActivity activity, int fragmentId) {
        try {
            Fragment fragment = getFragment(activity, fragmentId);
            if (fragment != null) {
                FragmentTransaction fragTransaction = activity.getSupportFragmentManager().beginTransaction();
                fragTransaction.detach(fragment);
                fragTransaction.attach(fragment);
                fragTransaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
