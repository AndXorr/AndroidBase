package android.base.activity;

import android.base.activity.broadcast.LanguageBroadCastReceiver;
import android.base.fragment.BaseFragment;
import android.base.interfaces.OnBackHandler;
import android.base.http.WebHandler;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
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
    private OnBackHandler mBackHandler;
    private BaseFragment mFragment = null;

    private LanguageBroadCastReceiver languageBroadCastReceiver;
    private IntentFilter filter = new IntentFilter(Intent.ACTION_LOCALE_CHANGED);

    /**
     * Instantiates a new Base activity app compat.
     */
    protected BaseAppCompatActivity() {
        mBackHandler = null;
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
        TAG = getLocalClassName();
        initUI();
        languageBroadCastReceiver = new LanguageBroadCastReceiver(this);
        LocalBroadcastManager.getInstance(this).registerReceiver(languageBroadCastReceiver, filter);
    }

    protected final Bundle getBundle() {
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
     * @param mBackHandler the back handler
     */
    public void setmBackHandler(OnBackHandler mBackHandler) {
        this.mBackHandler = mBackHandler;
    }

    /**
     * Gets back handler.
     *
     * @return the back handler
     */
    public OnBackHandler getmBackHandler() {
        return mBackHandler;
    }

    @Override
    public void onBackPressed() {
        if (getmBackHandler() != null) {
            getmBackHandler().onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Sets mFragment when called another activity or application eg camera or gallery
     * After completed operation set this to null.
     *
     * @param fragment the mFragment
     */
    public void setOnActivityResultFragment(BaseFragment fragment) {
        this.mFragment = fragment;
    }

    @Override
    public void recreate() {
        if (android.os.Build.VERSION.SDK_INT >= 11) {
            super.recreate();
        } else {
            startActivity(getIntent());
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mFragment != null) {
            mFragment.onActivityResult(requestCode, resultCode, data);
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
