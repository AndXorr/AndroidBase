package android.base.activity.broadcast;

import android.app.Activity;
import android.base.activity.BaseAppCompatActivity;
import android.base.interfaces.OnRequestHandleListener;
import android.base.util.ApplicationUtils;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.Locale;

/**
 * Created by clickapps on 24/2/16.
 * <p class="note">Language change Broadcast is included in this {@link LanguageBroadCastReceiver}.
 * Whenever language changes this broadcast hits and result will pass on to listener {@link OnRequestHandleListener} if having
 * and also to {@link BaseAppCompatActivity}
 * </p>
 */
public class LanguageBroadCastReceiver extends BroadcastReceiver {
    private Activity mActivity;
    private OnRequestHandleListener mCallBack;

    /**
     * Instantiates a new Language broad cast receiver.
     *
     * @param activity the activity
     */
    public LanguageBroadCastReceiver(Activity activity) {
        this.mActivity = activity;
    }

    /**
     * Instantiates a new Language broad cast receiver.
     *
     * @param activity the activity
     * @param callBack the call back
     */
    public LanguageBroadCastReceiver(Activity activity, OnRequestHandleListener callBack) {
        this.mActivity = activity;
        this.mCallBack = callBack;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ApplicationUtils.Log.i(context.getPackageName());
        ApplicationUtils.Log.i(mActivity.getPackageName());
        if (mCallBack != null) {
            mCallBack.onRequest(null, null, 0, android.base.constant.Constant.ACTION_BROADCAST_LANGUAGE_CHANGED);
        }
        if (mActivity instanceof BaseAppCompatActivity) {
            BaseAppCompatActivity activity = (BaseAppCompatActivity) mActivity;
            //activity.recreate();
            Locale locale = ApplicationUtils.Locale.getLocaleCurrentApp(mActivity);
            if (ApplicationUtils.Validator.isMatches(locale.getLanguage(), "ar")) {
                mActivity.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            } else {
                mActivity.getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            }
        }
    }
}
