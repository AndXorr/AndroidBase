package android.base.alert;

import android.base.dialog.OnDialogProcess;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;


/**
 * The type Dialog builder.
 */
public class DialogBuilder {

    private AlertParam alertParam;

    /**
     * Instantiates a new Dialog builder.
     *
     * @param context the context
     */
    public DialogBuilder(Context context) {
        alertParam = new AlertParam();
        alertParam.context = context;
    }

    /**
     * Instantiates a new Dialog builder.
     *
     * @param context    the context
     * @param dialogType the dialog type
     */
    public DialogBuilder(Context context, AlertParam.DialogType dialogType) {
        alertParam = new AlertParam();
        alertParam.context = context;
        alertParam.dialogType = dialogType;
    }

    /**
     * Message dialog builder.
     *
     * @param message the message
     * @return the dialog builder
     */
/*Set Message for AlertDialog*/
    public DialogBuilder message(String message) {
        alertParam.message = message;
        return this;
    }

    /**
     * Message dialog builder.
     *
     * @param resId the res id
     * @return the dialog builder
     */
/*Set Message resId for AlertDialog*/
    public DialogBuilder message(int resId) {
        alertParam.messageResId = resId;
        return this;
    }

    /**
     * Title dialog builder.
     *
     * @param title the title
     * @return the dialog builder
     */
/*Set Title for AlertDialog*/
    public DialogBuilder title(String title) {
        alertParam.title = title;
        return this;
    }

    /**
     * Title dialog builder.
     *
     * @param resId the res id
     * @return the dialog builder
     */
/*Set Title resId for AlertDialog*/
    public DialogBuilder title(int resId) {
        alertParam.titleResId = resId;
        return this;
    }

    /**
     * Dialog type dialog builder.
     *
     * @param dialogType the dialog type
     * @return the dialog builder
     */
/*Set DialogType for AlertDialog*/
    public DialogBuilder dialogType(AlertParam.DialogType dialogType) {
        alertParam.dialogType = dialogType;
        return this;
    }

    /**
     * Icon dialog builder.
     *
     * @param icon the icon
     * @return the dialog builder
     */
/*Set icon for AlertDialog*/
    public DialogBuilder icon(int icon) {
        alertParam.icon = icon;
        return this;
    }

    /**
     * Icon dialog builder.
     *
     * @param drawable the drawable
     * @return the dialog builder
     */
/*Set Drawable for AlertDialog*/
    public DialogBuilder icon(Drawable drawable) {
        alertParam.drawable = drawable;
        return this;
    }

    /**
     * Positive button dialog builder.
     *
     * @param positiveButton the positive button
     * @return the dialog builder
     */
/*Set PositiveButton Message for AlertDialog*/
    public DialogBuilder positiveButton(String positiveButton) {
        alertParam.positiveButton = positiveButton;
        return this;
    }

    /**
     * Negative button dialog builder.
     *
     * @param negativeButton the negative button
     * @return the dialog builder
     */
/*Set NegativeButton Message for AlertDialog*/
    public DialogBuilder negativeButton(String negativeButton) {
        alertParam.negativeButton = negativeButton;
        return this;
    }

    /**
     * Positive button dialog builder.
     *
     * @param resId the res id
     * @return the dialog builder
     */
/*Set PositiveButton Message resId for AlertDialog*/
    public DialogBuilder positiveButton(int resId) {
        alertParam.positiveButtonResId = resId;
        return this;
    }

    /**
     * Negative button dialog builder.
     *
     * @param resId the res id
     * @return the dialog builder
     */
/*Set NegativeButton Message resId for AlertDialog*/
    public DialogBuilder negativeButton(int resId) {
        alertParam.negativeButtonResId = resId;
        return this;
    }

    /**
     * Listener dialog builder.
     *
     * @param l the l
     * @return the dialog builder
     */
/*Set Listener for AlertDialog*/
    public DialogBuilder listener(OnDialogProcess l) {
        alertParam.listener = l;
        return this;
    }

    /**
     * Bundle dialog builder.
     *
     * @param bundle the bundle
     * @return the dialog builder
     */
/*Set data bundle for AlertDialog*/
    public DialogBuilder bundle(Bundle bundle) {
        alertParam.bundle = bundle;
        return this;
    }

    /**
     * Gets bundle.
     *
     * @return the bundle
     */
/*get data bundle for AlertDialog*/
    public Bundle getBundle() {
        return alertParam.bundle;
    }

    /**
     * Task id dialog builder.
     *
     * @param alertTaskId the alert task id
     * @return the dialog builder
     */
/* Set alertTask Id for Uniqueness */
    public DialogBuilder taskId(int alertTaskId) {
        alertParam.alertTaskId = alertTaskId;
        return this;
    }

    /**
     * Cancelable dialog builder.
     *
     * @param isCancel the is cancel
     * @return the dialog builder
     */
    public DialogBuilder cancelable(boolean isCancel) {
        alertParam.isCancelable = isCancel;
        return this;
    }

    /**
     * List dialog builder.
     *
     * @param list the list
     * @return the dialog builder
     */
    public DialogBuilder list(String[] list) {
        alertParam.list = list;
        return this;
    }

    /**
     * Show.
     */
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

        /**
         * Instantiates a new On dialog click.
         *
         * @param alertParam the alert param
         * @param buttonType the button type
         */
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

