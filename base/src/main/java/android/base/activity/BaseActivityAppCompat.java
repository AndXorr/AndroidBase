package android.base.activity;

import android.base.fragment.BaseFragment;
import android.base.http.WebParam;
import android.base.interfaces.OnBackHandler;
import android.base.interfaces.WebHandler;
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
public abstract class BaseActivityAppCompat extends AppCompatActivity implements
        //to identify child tasks and perform on activity itself
        View.OnClickListener, WebHandler.OnWebCallback {


    /**
     * This method is used to initialize UI of the layout. Called in onCreate()
     */
    protected abstract void initUI();


    protected String TAG;
    public OnBackHandler backHandler = null;
    private BaseFragment fragment = null;

    /**
     * This method is used to show layout.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getLocalClassName();
        initUI();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressed() {
        if (backHandler != null) {
            backHandler.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    public void setFragment(BaseFragment fragment) {
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
