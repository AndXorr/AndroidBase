package android.base.alert;

import android.base.dialog.OnDialogProcess;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

/**
 * Created by sahni on 6/6/16.
 */
public class DialogBuilder {

    private AlertParam alertParam;

    public DialogBuilder(Context context) {
        alertParam = new AlertParam();
        alertParam.context = context;
    }

    public DialogBuilder(Context context, AlertParam.DialogType dialogType) {
        alertParam = new AlertParam();
        alertParam.context = context;
        alertParam.dialogType = dialogType;
    }

    /*Set Message for AlertDialog*/
    public DialogBuilder message(String message) {
        alertParam.message = message;
        return this;
    }

    /*Set Message resId for AlertDialog*/
    public DialogBuilder message(int resId) {
        alertParam.messageResId = resId;
        return this;
    }

    /*Set Title for AlertDialog*/
    public DialogBuilder title(String title) {
        alertParam.title = title;
        return this;
    }

    /*Set Title resId for AlertDialog*/
    public DialogBuilder title(int resId) {
        alertParam.titleResId = resId;
        return this;
    }

    /*Set DialogType for AlertDialog*/
    public DialogBuilder dialogType(AlertParam.DialogType dialogType) {
        alertParam.dialogType = dialogType;
        return this;
    }

    /*Set icon for AlertDialog*/
    public DialogBuilder icon(int icon) {
        alertParam.icon = icon;
        return this;
    }

    /*Set Drawable for AlertDialog*/
    public DialogBuilder icon(Drawable drawable) {
        alertParam.drawable = drawable;
        return this;
    }

    /*Set PositiveButton Message for AlertDialog*/
    public DialogBuilder positiveButton(String positiveButton) {
        alertParam.positiveButton = positiveButton;
        return this;
    }

    /*Set NegativeButton Message for AlertDialog*/
    public DialogBuilder negativeButton(String negativeButton) {
        alertParam.negativeButton = negativeButton;
        return this;
    }

    /*Set PositiveButton Message resId for AlertDialog*/
    public DialogBuilder positiveButton(int resId) {
        alertParam.positiveButtonResId = resId;
        return this;
    }

    /*Set NegativeButton Message resId for AlertDialog*/
    public DialogBuilder negativeButton(int resId) {
        alertParam.negativeButtonResId = resId;
        return this;
    }

    /*Set Listener for AlertDialog*/
    public DialogBuilder listener(OnDialogProcess l) {
        alertParam.listener = l;
        return this;
    }

    /*Set data bundle for AlertDialog*/
    public DialogBuilder bundle(Bundle bundle) {
        alertParam.bundle = bundle;
        return this;
    }

    /*get data bundle for AlertDialog*/
    public Bundle getBundle() {
        return alertParam.bundle;
    }

    /* Set alertTask Id for Uniqueness */
    public DialogBuilder taskId(int alertTaskId) {
        alertParam.alertTaskId = alertTaskId;
        return this;
    }

    public DialogBuilder cancelable(boolean isCancel) {
        alertParam.isCancelable = isCancel;
        return this;
    }

    public DialogBuilder list(String[] list) {
        alertParam.list = list;
        return this;
    }

    public void show() {
        // alert dialog functionality
        AlertDialog.Builder mAlert = new AlertDialog.Builder(alertParam.context);
        // set the message
        if (alertParam.messageResId != 0) {
            mAlert.setMessage(alertParam.messageResId);
        } else if (!TextUtils.isEmpty(alertParam.message)) {
            mAlert.setMessage(alertParam.message);
        }
        // set title
        if (alertParam.titleResId != 0) {
            mAlert.setTitle(alertParam.titleResId);
        } else if (!TextUtils.isEmpty(alertParam.title)) {
            mAlert.setTitle(alertParam.title);
        } else {
            mAlert.setTitle("");
        }
        mAlert.setCancelable(alertParam.isCancelable);
        // set icon
        if (alertParam.icon != 0) {
            mAlert.setIcon(alertParam.icon);
        } else if (alertParam.drawable != null) {
            mAlert.setIcon(alertParam.drawable);
        } else {
            mAlert.setIcon(0);
        }
        // set Positive button functionality
        if (alertParam.dialogType == AlertParam.DialogType.DOUBLE_OPTION ||
                alertParam.dialogType == AlertParam.DialogType.SINGLE_OPTION) {
            if (alertParam.positiveButtonResId != 0) {
                mAlert.setPositiveButton(alertParam.positiveButtonResId,
                        new OnDialogClick(alertParam, 1));
            } else if (!TextUtils.isEmpty(alertParam.positiveButton)) {
                mAlert.setPositiveButton(alertParam.positiveButton,
                        new OnDialogClick(alertParam, 1));
            } else {
                mAlert.setPositiveButton("",
                        new OnDialogClick(alertParam, 1));
            }

        }
        // set Negative button functionality
        if (alertParam.dialogType == AlertParam.DialogType.DOUBLE_OPTION) {
            if (alertParam.negativeButtonResId != 0) {
                mAlert.setNegativeButton(alertParam.negativeButtonResId,
                        new OnDialogClick(alertParam, 2));
            } else if (!TextUtils.isEmpty(alertParam.negativeButton)) {
                mAlert.setNegativeButton(alertParam.negativeButton,
                        new OnDialogClick(alertParam, 2));
            } else {
                mAlert.setNegativeButton("",
                        new OnDialogClick(alertParam, 2));
            }
        }
        // List
        if (alertParam.dialogType == AlertParam.DialogType.DIALOG_LIST) {
            mAlert.setItems(alertParam.list, new OnDialogClick(alertParam, 3));
        }
        mAlert.create().show();

    }

    /* Handle Alert Dialog Button Click */
    private class OnDialogClick implements DialogInterface.OnClickListener {
        private AlertParam alertParam;
        private int buttonType;

        public OnDialogClick(AlertParam alertParam, int buttonType) {
            this.alertParam = alertParam;
            this.buttonType = buttonType;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (alertParam.listener == null)
                return;
            if (buttonType == 3) {
                alertParam.bundle = new Bundle();
                alertParam.bundle.putString(AlertParam.ITEM, alertParam.list[which]);
            }
            alertParam.listener.onDialog(
                    alertParam.alertTaskId, alertParam.bundle,
                    this, buttonType);
        }
    }
}

