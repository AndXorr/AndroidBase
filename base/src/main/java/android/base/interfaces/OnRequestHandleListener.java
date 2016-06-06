package android.base.interfaces;

import android.os.Bundle;
import android.view.View;

/**
 * This interface used as a callback for the:
 * 1. fragments
 * 2. List view of fragments
 * 3. Any other activity on fragment
 */
public interface OnRequestHandleListener {
    /**
     * call back for the list item click
     *
     * @param <T>    object of generic type
     * @param view   List row/item view/any other view
     * @param bundle bundle containing data to transfer via callback
     * @param id     integer value to identify task to be performed
     */
    <T> void onRequest(View view, Bundle bundle, int id, T t);
}

