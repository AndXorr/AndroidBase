package android.base.alert;


import android.app.Activity;
import android.base.R;
import android.base.dialog.OnDialogProcess;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;


/**
 * The type Alert.
 */
public class Alert {
    private static volatile Alert sAlert;

    private Alert() {
        // private constructor
    }

    /**
     * Get alert.
     *
     * @return the alert
     */
    public static Alert get() {
        if (sAlert == null) {
            synchronized (Alert.class) {
                if (sAlert == null) {
                    sAlert = new Alert();
                }
            }
        }
        return sAlert;
    }

    /**
     * *******************************************************************************************************************
     *
     * @param context the context
     * @return the toast builder
     */
    public static ToastBuilder with(@NonNull Context context) {
        return new ToastBuilder(context);
    }

    /**
     * With toast builder.
     *
     * @param context the context
     * @param resId   the res id
     * @return the toast builder
     */
    public static ToastBuilder with(@NonNull Context context, @StringRes int resId) {
        return new ToastBuilder(context, resId);
    }

    /**
     * *******************************************************************************************************************
     *
     * @param context    the context
     * @param dialogType the dialog type
     * @return the dialog builder
     */
    public static DialogBuilder with(@NonNull Context context, AlertParam.DialogType dialogType) {
        return new DialogBuilder(context, dialogType);
    }


    /**
     * *******************************************************************************************************************
     *
     * @param context the context
     * @param resId   the res id
     * @return the snack builder
     */
/* SnackBuilder class for showing Material SnackBuilder on Screen. To use this need to compile Design support lib */
    public static SnackBuilder with(Activity context, @StringRes int resId) {
        return new SnackBuilder(context, resId);
    }

    /**
     * With snack builder.
     *
     * @param context the context
     * @param msg     the msg
     * @return the snack builder
     */
    public static SnackBuilder with(Activity context, String msg) {
        return new SnackBuilder(context, msg);
    }

}
