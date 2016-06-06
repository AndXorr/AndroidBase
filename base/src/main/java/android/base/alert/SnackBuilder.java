package android.base.alert;

import android.app.Activity;
import android.base.R;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

/**
 * Created by sahni on 6/6/16.
 */
public class SnackBuilder {

    private AlertParam alertParam;
    private Snackbar snackbar;

    public SnackBuilder(Activity context, @StringRes int resId) {
        alertParam = new AlertParam();
        alertParam.activityContext = context;
        alertParam.messageResId = resId;
    }

    public SnackBuilder(Activity context, String msg) {
        alertParam = new AlertParam();
        alertParam.activityContext = context;
        alertParam.message = msg;
    }

    /*Set ActionMessage for SnackBuilder*/
    public SnackBuilder actionMessage(String actionMessage) {
        alertParam.actionMessage = actionMessage;
        return this;
    }

    /*Set ActionMessage resId for SnackBuilder*/
    public SnackBuilder actionMessage(int resId) {
        alertParam.actionMessageResId = resId;
        return this;
    }

    /*Set View for SnackBuilder*/
    public SnackBuilder view(View view) {
        alertParam.snackBarView = view;
        alertParam.snackBarView.setFocusable(false);
        return this;
    }

    /*Set Listener for SnackBuilder*/
    public SnackBuilder listener(AlertParam.OnSnackBarActionListener l) {
        alertParam.onSnackBarActionListener = l;
        return this;
    }

    /*Set color for SnackBuilder*/
    public SnackBuilder actionColor(int resId) {
        alertParam.actionColorResId = resId;
        return this;
    }

    /*Set color for SnackBuilder*/
    public SnackBuilder textColor(int resId) {
        alertParam.textColor = resId;
        return this;
    }

    public SnackBuilder duration(int duration) {
        alertParam.snackBarDuration = duration;
        return this;
    }

    public SnackBuilder backgroundColor(int color) {
        alertParam.actionBackgroundResId = color;
        return this;
    }

    public SnackBuilder uniqueId(int uniqueId) {
        alertParam.alertTaskId = uniqueId;
        return this;
    }

    public SnackBuilder actionMessageMaxLines(int maxLines) {
        alertParam.actionMessageMaxLine = maxLines;
        return this;
    }

    public void show() {
        if (snackbar != null)
            snackbar.dismiss();
        if (alertParam.activityContext == null) {
            return;
        }
        if (alertParam.snackBarView == null) {
            alertParam.snackBarView = alertParam.activityContext.findViewById(android.R.id.content);

        }
        snackbar = Snackbar.make(alertParam.snackBarView, "", alertParam.snackBarDuration);
        // Checked for Message
        if (alertParam.messageResId != 0) {
            snackbar.setText(alertParam.messageResId);
        } else if (!TextUtils.isEmpty(alertParam.message)) {
            snackbar.setText(alertParam.message);
        } else {
            snackbar.setText("");
        }
        // checked for ActionMessage
        if (alertParam.actionMessageResId != 0) {
            snackbar.setAction(alertParam.actionMessageResId, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (alertParam.onSnackBarActionListener == null) {
                        return;
                    }
                    alertParam.onSnackBarActionListener.onSnackBarActionClicked(alertParam.alertTaskId, v);
                }
            });
        } else if (!TextUtils.isEmpty(alertParam.actionMessage)) {
            snackbar.setAction(alertParam.actionMessage, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (alertParam.onSnackBarActionListener == null) {
                        return;
                    }
                    alertParam.onSnackBarActionListener.onSnackBarActionClicked(alertParam.alertTaskId, v);
                }
            });
        } else {
            snackbar.setAction("", null);
        }
        // checked for actionMessagecolor
        if (alertParam.actionColorResId != 0) {
            snackbar.setActionTextColor(ContextCompat.getColor(alertParam.activityContext, alertParam.actionColorResId));
        }
        TextView tv = (TextView) snackbar.getView().findViewById(R.id.snackbar_text);
        if (alertParam.actionMessageMaxLine != -1)
            tv.setMaxLines(alertParam.actionMessageMaxLine);
        if (alertParam.textColor != 0) {
            tv.setTextColor(ContextCompat.getColorStateList(alertParam.activityContext, alertParam.textColor));
        }
        if (alertParam.actionBackgroundResId != 0)
            snackbar.getView().setBackgroundColor(ContextCompat.getColor(alertParam.activityContext, alertParam.actionBackgroundResId));
        snackbar.show();
    }

}
