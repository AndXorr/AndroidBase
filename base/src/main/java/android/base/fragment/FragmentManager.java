package android.base.fragment;

import android.app.Fragment;
import android.base.R;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * Created by clickapps on 20/7/15.
 */
public class FragmentManager {

    private FragmentManager() {
        // private constructor
    }

    public static Builder with(@NonNull FragmentActivity context, @IdRes int replaceId) {
        return new Builder(context, replaceId);
    }
}
