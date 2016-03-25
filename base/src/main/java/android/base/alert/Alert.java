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
 * Created by Sahni on 28-07-2015.
 */
public final class Alert {
    /**
     * *******************************************************************************************************************
     */
    /*Toast class for showing Toast on Screen*/
    public static class Toast {
        private AlertParam alertParam;
        private android.widget.Toast toast;

        /**
         * @param context context
         */
        public Toast(@NonNull Context context) {
            alertParam = new AlertParam();
            alertParam.context = context;
        }

        /**
         * @param context context
         * @param resId
         */
        public Toast(@NonNull Context context, @StringRes int resId) {
            alertParam = new AlertParam();
            alertParam.context = context;
            alertParam.messageResId = resId;
        }

        /*Set Message for Toast*/
        public Toast setMessage(String message) {
            alertParam.message = message;
            return this;
        }

        /*Set Message resId for Toast*/
        public Toast setMessage(int resId) {
            alertParam.messageResId = resId;
            return this;
        }

        /*Set Duration for Toast*/
        public Toast setDuration(int duration) {
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
            toast.show();
        }
    }

    /**
     * *******************************************************************************************************************
     */
    /*AlertDialog class for showing AlertDialog on Screen*/

    public static class Dialog {
        private AlertParam alertParam;

        public Dialog(Context context) {
            alertParam = new AlertParam();
            alertParam.context = context;
        }

        public Dialog(Context context, AlertParam.DialogType dialogType) {
            alertParam = new AlertParam();
            alertParam.context = context;
            alertParam.dialogType = dialogType;
        }

        /*Set Message for AlertDialog*/
        public Dialog setMessage(String message) {
            alertParam.message = message;
            return this;
        }

        /*Set Message resId for AlertDialog*/
        public Dialog setMessage(int resId) {
            alertParam.messageResId = resId;
            return this;
        }

        /*Set Title for AlertDialog*/
        public Dialog setTitle(String title) {
            alertParam.title = title;
            return this;
        }

        /*Set Title resId for AlertDialog*/
        public Dialog setTitle(int resId) {
            alertParam.titleResId = resId;
            return this;
        }

        /*Set DialogType for AlertDialog*/
        public Dialog setDialogType(AlertParam.DialogType dialogType) {
            alertParam.dialogType = dialogType;
            return this;
        }

        /*Set icon for AlertDialog*/
        public Dialog setIcon(int icon) {
            alertParam.icon = icon;
            return this;
        }

        /*Set Drawable for AlertDialog*/
        public Dialog setIcon(Drawable drawable) {
            alertParam.drawable = drawable;
            return this;
        }

        /*Set PositiveButton Message for AlertDialog*/
        public Dialog setPositiveButton(String positiveButton) {
            alertParam.positiveButton = positiveButton;
            return this;
        }

        /*Set NegativeButton Message for AlertDialog*/
        public Dialog setNegativeButton(String negativeButton) {
            alertParam.negativeButton = negativeButton;
            return this;
        }

        /*Set PositiveButton Message resId for AlertDialog*/
        public Dialog setPositiveButton(int resId) {
            alertParam.positiveButtonResId = resId;
            return this;
        }

        /*Set NegativeButton Message resId for AlertDialog*/
        public Dialog setNegativeButton(int resId) {
            alertParam.negativeButtonResId = resId;
            return this;
        }

        /*Set Listener for AlertDialog*/
        public Dialog setListener(OnDialogProcess l) {
            alertParam.listener = l;
            return this;
        }

        /*Set data bundle for AlertDialog*/
        public Dialog setBundle(Bundle bundle) {
            alertParam.bundle = bundle;
            return this;
        }

        /*get data bundle for AlertDialog*/
        public Bundle getBundle() {
            return alertParam.bundle;
        }

        /* Set alertTask Id for Uniqueness */
        public Dialog setAlertTaskId(int alertTaskId) {
            alertParam.alertTaskId = alertTaskId;
            return this;
        }

        public Dialog setCancelable(boolean isCancel) {
            alertParam.isCancelable = isCancel;
            return this;
        }

        public Dialog setList(String[] list) {
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


    /**
     * *******************************************************************************************************************
     */
     /* SnackBar class for showing Material SnackBar on Screen. To use this need to compile Design support lib */
    public static class SnackBar {
        private AlertParam alertParam;
        private Snackbar snackbar;

        /*Set context*/
        public SnackBar(Activity context) {
            alertParam = new AlertParam();
            alertParam.activityContext = context;
        }

        public SnackBar(Activity context, @StringRes int resId) {
            alertParam = new AlertParam();
            alertParam.activityContext = context;
            alertParam.messageResId = resId;
        }

        /*Set Message for SnackBar*/
        public SnackBar setMessage(String message) {
            alertParam.message = message;
            return this;
        }

        /*Set Message resId for SnackBar*/
        public SnackBar setMessage(int resId) {
            alertParam.messageResId = resId;
            return this;
        }

        /*Set ActionMessage for SnackBar*/
        public SnackBar setActionMessage(String actionMessage) {
            alertParam.actionMessage = actionMessage;
            return this;
        }

        /*Set ActionMessage resId for SnackBar*/
        public SnackBar setActionMessage(int resId) {
            alertParam.actionMessageResId = resId;
            return this;
        }

        /*Set View for SnackBar*/
        public SnackBar setView(View view) {
            alertParam.snackBarView = view;
            alertParam.snackBarView.setFocusable(false);
            return this;
        }

        /*Set Listener for SnackBar*/
        public SnackBar setListener(AlertParam.OnSnackBarActionListener l) {
            alertParam.onSnackBarActionListener = l;
            return this;
        }

        /*Set color for SnackBar*/
        public SnackBar setActionColor(int resId) {
            alertParam.actionColorResId = resId;
            return this;
        }

        /*Set color for SnackBar*/
        public SnackBar setTextColor(int resId) {
            alertParam.textColor = resId;
            return this;
        }

        public SnackBar setDuration(int duration) {
            alertParam.snackBarDuration = duration;
            return this;
        }

        public SnackBar setBackgroundColor(int color) {
            alertParam.actionBackgroundResId = color;
            return this;
        }

        public SnackBar setUniqueId(int uniqueId) {
            alertParam.alertTaskId = uniqueId;
            return this;
        }

        public SnackBar setActionMessageMaxLines(int maxLines) {
            alertParam.actionMessageMaxLine = maxLines;
            return this;
        }

        public void show() {
            if (snackbar != null)
                snackbar.dismiss();
            if (alertParam.activityContext == null) return;
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
                        if (alertParam.onSnackBarActionListener == null) return;
                        alertParam.onSnackBarActionListener.onSnackBarActionClicked(alertParam.alertTaskId, v);
                    }
                });
            } else if (!TextUtils.isEmpty(alertParam.actionMessage)) {
                snackbar.setAction(alertParam.actionMessage, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (alertParam.onSnackBarActionListener == null) return;
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


}
