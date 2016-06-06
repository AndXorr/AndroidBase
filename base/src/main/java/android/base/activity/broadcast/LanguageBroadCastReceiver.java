package android.base.activity.broadcast;

import android.app.Activity;
import android.base.activity.BaseActivityAppCompat;
import android.base.interfaces.OnRequestHandleListener;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by clickapps on 24/2/16.
 */
public class LanguageBroadCastReceiver extends BroadcastReceiver {
    private Activity mActivity;
    private OnRequestHandleListener mCallBack;

    public LanguageBroadCastReceiver(Activity activity) {
        this.mActivity = activity;
    }

    public LanguageBroadCastReceiver(Activity activity, OnRequestHandleListener callBack) {
        this.mActivity = activity;
        this.mCallBack = callBack;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (mCallBack != null) {
            mCallBack.onRequest(null, null, 0, android.base.constant.Constant.ACTION_BROADCAST_LANGUAGE_CHANGED);
        }
        if (mActivity instanceof BaseActivityAppCompat) {
            BaseActivityAppCompat activity = (BaseActivityAppCompat) mActivity;
            activity.recreate();
        }
    }
}
