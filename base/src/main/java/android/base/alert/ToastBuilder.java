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

    private AlertParam alertParam;
    private android.widget.Toast toast;

    /**
     * Instantiates a new Toast builder.
     *
     * @param context the context
     */
    public ToastBuilder(@NonNull Context context) {
        alertParam = new AlertParam();
        alertParam.context = context;
    }

    /**
     * Instantiates a new Toast builder.
     *
     * @param context the context
     * @param resId   the res id
     */
    public ToastBuilder(@NonNull Context context, @StringRes int resId) {
        alertParam = new AlertParam();
        alertParam.context = context;
        alertParam.messageResId = resId;
    }

    /**
     * Message toast builder.
     *
     * @param message the message
     * @return the toast builder
     */
    public ToastBuilder message(String message) {
        alertParam.message = message;
        return this;
    }

    /**
     * Message toast builder.
     *
     * @param resId the res id
     * @return the toast builder
     */
    public ToastBuilder message(int resId) {
        alertParam.messageResId = resId;
        return this;
    }

    /**
     * Duration toast builder.
     *
     * @param duration the duration
     * @return the toast builder
     */
    public ToastBuilder duration(int duration) {
        alertParam.duration = duration;
        return this;
    }

    /**
     * Show.
     */
    public void show() {
        if (null != toast) {
            toast.cancel();
        }
        if (alertParam.messageResId != 0) {
            toast = android.widget.Toast.makeText(alertParam.context.getApplicationContext(), alertParam.messageResId, alertParam.duration);
        } else if (!TextUtils.isEmpty(alertParam.message)) {
            toast = android.widget.Toast.makeText(alertParam.context.getApplicationContext(), alertParam.message, alertParam.duration);
        }

        try {
            //find text view
            TextView textView = (TextView) ((LinearLayout) toast.getView()).getChildAt(0);
            //check typeface
            String typeface = alertParam.getTypeface();
            if (!TextUtils.isEmpty(typeface)) {
                //set message typeface
                Alert.get().setTypeface(alertParam.context, textView, typeface);
            }
        } catch (Exception e) {
            ApplicationUtils.Log.e(e.getMessage() + e);
        }

        if (toast != null) {
            toast.show();
        }
    }
}
