package android.base.alert;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.TextUtils;

/**
 * Created by sahni on 6/6/16.
 */
public class ToastBuilder {

    private AlertParam alertParam;
    private android.widget.Toast toast;

    public ToastBuilder(@NonNull Context context) {
        alertParam = new AlertParam();
        alertParam.context = context;
    }

    public ToastBuilder(@NonNull Context context, @StringRes int resId) {
        alertParam = new AlertParam();
        alertParam.context = context;
        alertParam.messageResId = resId;
    }

    public ToastBuilder message(String message) {
        alertParam.message = message;
        return this;
    }

    public ToastBuilder message(int resId) {
        alertParam.messageResId = resId;
        return this;
    }

    public ToastBuilder duration(int duration) {
        alertParam.duration = duration;
        return this;
    }

    public void show() {
        if (null != toast) {
            toast.cancel();
        }
        if (alertParam.messageResId != 0) {
            toast = android.widget.Toast.makeText(alertParam.context.getApplicationContext(), alertParam.messageResId, alertParam.duration);
        } else if (!TextUtils.isEmpty(alertParam.message)) {
            toast = android.widget.Toast.makeText(alertParam.context.getApplicationContext(), alertParam.message, alertParam.duration);
        }
        if (toast != null) {
            toast.show();
        }
    }
}
