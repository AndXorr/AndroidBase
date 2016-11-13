package android.base.fragment;

import android.app.Activity;
import android.base.activity.BaseAppCompatActivity;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;


/**
 * The type Fragment manager.
 */
public class FragmentManager {
    private static volatile FragmentManager sFragmentManager;

    private FragmentManager() {
        // private constructor
    }

    /**
     * Get fragment manager.
     *
     * @return the fragment manager
     */
    public static FragmentManager get() {
        if (sFragmentManager == null) {
            synchronized (FragmentManager.class) {
                if (sFragmentManager == null) {
                    sFragmentManager = new FragmentManager();
                }
            }
        }
        return sFragmentManager;
    }

    /**
     * With builder.
     *
     * @param context   the context
     * @param replaceId the replace id
     * @return the builder
     */
    public static Builder with(@NonNull FragmentActivity context, @IdRes int replaceId) {
        return new Builder(context, replaceId);
    }
}
