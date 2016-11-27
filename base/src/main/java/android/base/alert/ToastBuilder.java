package android.base.alert;

import android.base.util.ApplicationUtils;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * The type Toast builder.
 */
public class ToastBuilder {

    private AlertParam mAlertParam;
    private android.widget.Toast mToast;

    /**
     * Instantiates a new Toast builder.
     *
     * @param context the context
     */
    public ToastBuilder(@NonNull Context context) {
        mAlertParam = new AlertParam();
        mAlertParam.context = context;
    }

    /**
     * Instantiates a new Toast builder.
     *
     * @param context the context
     * @param resId   the res id
     */
    public ToastBuilder(@NonNull Context context, @StringRes int resId) {
        mAlertParam = new AlertParam();
        mAlertParam.context = context;
        mAlertParam.messageResId = resId;
    }

    /**
     * Message mToast builder.
     *
     * @param message the message
     * @return the mToast builder
     */
    public ToastBuilder message(String message) {
        mAlertParam.message = message;
        return this;
    }

    /**
     * Message mToast builder.
     *
     * @param resId the res id
     * @return the mToast builder
     */
    public ToastBuilder message(int resId) {
        mAlertParam.messageResId = resId;
        return this;
    }

    /**
     * Duration mToast builder.
     *
     * @param duration the duration
     * @return the mToast builder
     */
    public ToastBuilder duration(int duration) {
        mAlertParam.duration = duration;
        return this;
    }

    /**
     * Show.
     */
    public void show() {
        if (null != mToast) {
            mToast.cancel();
        }
        if (mAlertParam.messageResId != 0) {
            mToast = android.widget.Toast.makeText(mAlertParam.context.getApplicationContext(), mAlertParam.messageResId, mAlertParam.duration);
        } else if (!TextUtils.isEmpty(mAlertParam.message)) {
            mToast = android.widget.Toast.makeText(mAlertParam.context.getApplicationContext(), mAlertParam.message, mAlertParam.duration);
        }

        try {
            //find text mView
            TextView textView = (TextView) ((LinearLayout) mToast.getView()).getChildAt(0);
            //check typeface
            String typeface = mAlertParam.getTypeface();
            if (!TextUtils.isEmpty(typeface)) {
                //set message typeface
                Alert.get().setTypeface(mAlertParam.context, textView, typeface);
            }
        } catch (Exception e) {
            ApplicationUtils.Log.e(e.getMessage() + e);
        }

        if (mToast != null) {
            mToast.show();
        }
    }
}
