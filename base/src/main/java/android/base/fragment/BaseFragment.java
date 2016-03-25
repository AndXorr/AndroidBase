package android.base.fragment;

import android.app.Fragment;
import android.base.activity.BaseActivityAppCompat;
import android.base.http.WebParam;
import android.base.interfaces.OnBackHandler;
import android.base.interfaces.WebHandler;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * This is customized abstract Fragment class. This will be the base fragment class for all the fragment classes within the
 * application that will have common functionality define below :
 *
 * @author amit.singh
 * @method initUI method for initialize User Interface widgets
 * more.
 * @implements onClick listener for click event with in the class components
 */
public abstract class BaseFragment extends Fragment implements
        //click event listener
        View.OnClickListener,
        // Back press handle on Fragment
        OnBackHandler,
        // Web service response handler
        WebHandler.OnWebCallback {


    protected View view;
    private boolean enableBackHandle = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return initUI(inflater, container);
    }

    public void setEnableBackHandle(boolean enableBackHandle) {
        this.enableBackHandle = enableBackHandle;
    }

    @Override
    public void onResume() {
        /**
         * Handle BackPress on Fragment.
         */
        if (getActivity() instanceof BaseActivityAppCompat) {
            if (enableBackHandle)
                ((BaseActivityAppCompat) getActivity()).backHandler = this;
        }
        super.onResume();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public <T> void onSuccess(@Nullable T object, String response, int taskId, int statusCode) {

    }

    @Override
    public <T> void onError(@Nullable T object, String error, int taskId, int statusCode) {

    }

    /**
     * Inflate View in this method back end called
     * {@link BaseFragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}
     */
    protected abstract View initUI(LayoutInflater inflater, ViewGroup container);

}
