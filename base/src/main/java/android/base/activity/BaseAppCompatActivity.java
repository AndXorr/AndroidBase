package android.base.activity;

import android.base.fragment.BaseFragment;
import android.base.interfaces.OnBackHandler;
import android.base.http.WebHandler;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * This is customized abstract activity class.
 *
 * @author amit.singh
 * @method initUI() method for initialize User Interface widgets
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity implements
        //to identify child tasks and perform on activity itself
        View.OnClickListener, WebHandler.OnWebCallback {

    /**
     * The Tag.
     */
    protected String TAG;
    private OnBackHandler backHandler;
    private BaseFragment fragment = null;

    /**
     * Instantiates a new Base activity app compat.
     */
    protected BaseAppCompatActivity() {
        backHandler = null;
    }

    /**
     * This method is used to initialize UI of the layout. Called in onCreate()
     */
    protected abstract void initUI();


    /**
     * This method is used to show layout.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getLocalClassName();
        initUI();
    }

    protected Bundle getBundle() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            bundle = new Bundle();
        }
        return bundle;

    }

    @Override
    public void onClick(View v) {

    }

    /**
     * Sets back handler.
     *
     * @param backHandler the back handler
     */
    public void setBackHandler(OnBackHandler backHandler) {
        this.backHandler = backHandler;
    }

    /**
     * Gets back handler.
     *
     * @return the back handler
     */
    public OnBackHandler getBackHandler() {
        return backHandler;
    }

    @Override
    public void onBackPressed() {
        if (getBackHandler() != null) {
            getBackHandler().onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Sets fragment when called another activity or application eg camera or gallery
     * After completed operation set this to null.
     *
     * @param fragment the fragment
     */
    public void setOnActivityResultFragment(BaseFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void recreate() {
        startActivity(getIntent());
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public <T> void onSuccess(@Nullable T object, String response, int taskId, int statusCode) {

    }

    @Override
    public <T> void onError(@Nullable T object, String error, int taskId, int statusCode) {

    }
}
