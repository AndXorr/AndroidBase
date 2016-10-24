package android.base.alert;

import android.app.Activity;
import android.base.R;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;


/**
 * The type Snack builder.
 */
public class SnackBuilder {

    private AlertParam alertParam;
    private Snackbar snackbar;

    /**
     * Instantiates a new Snack builder.
     *
     * @param context the context
     * @param resId   the res id
     */
    public SnackBuilder(Activity context, @StringRes int resId) {
        alertParam = new AlertParam();
        alertParam.activityContext = context;
        alertParam.context = context;
        alertParam.messageResId = resId;
    }

    /**
     * Instantiates a new Snack builder.
     *
     * @param context the context
     * @param msg     the msg
     */
    public SnackBuilder(Activity context, String msg) {
        alertParam = new AlertParam();
        alertParam.activityContext = context;
        alertParam.context = context;
        alertParam.message = msg;
    }

    /**
     * Instantiates a new Snack builder.
     *
     * @param context         the context
     * @param resId           the res id
     * @param backgroundColor background color of snackbar
     */
    public SnackBuilder(Activity context, @StringRes int resId, @ColorRes int backgroundColor) {
        alertParam = new AlertParam();
        alertParam.activityContext = context;
        alertParam.context = context;
        alertParam.messageResId = resId;
        alertParam.actionBackgroundResId = backgroundColor;
    }

    /**
     * Instantiates a new Snack builder.
     *
     * @param context         the context
     * @param msg             the msg
     * @param backgroundColor background color of snackbar
     */
    public SnackBuilder(Activity context, String msg, @ColorRes int backgroundColor) {
        alertParam = new AlertParam();
        alertParam.activityContext = context;
        alertParam.context = context;
        alertParam.message = msg;
        alertParam.actionBackgroundResId = backgroundColor;
    }

    /**
     * Action message snack builder.
     *
     * @param actionMessage the action message
     * @return the snack builder
     */
/*Set ActionMessage for SnackBuilder*/
    public SnackBuilder actionMessage(String actionMessage) {
        alertParam.actionMessage = actionMessage;
        return this;
    }

    /**
     * Action message snack builder.
     *
     * @param resId the res id
     * @return the snack builder
     */
/*Set ActionMessage resId for SnackBuilder*/
    public SnackBuilder actionMessage(int resId) {
        alertParam.actionMessageResId = resId;
        return this;
    }

    /**
     * View snack builder.
     *
     * @param view the view
     * @return the snack builder
     */
/*Set View for SnackBuilder*/
    public SnackBuilder view(View view) {
        alertParam.snackBarView = view;
        alertParam.snackBarView.setFocusable(false);
        return this;
    }

    /**
     * Listener snack builder.
     *
     * @param l the l
     * @return the snack builder
     */
/*Set Listener for SnackBuilder*/
    public SnackBuilder listener(AlertParam.OnSnackBarActionListener l) {
        alertParam.onSnackBarActionListener = l;
        return this;
    }

    /**
     * Action color snack builder.
     *
     * @param resId the res id
     * @return the snack builder
     */
/*Set color for SnackBuilder*/
    public SnackBuilder actionColor(int resId) {
        alertParam.actionColorResId = resId;
        return this;
    }

    /**
     * Text color snack builder.
     *
     * @param resId the res id
     * @return the snack builder
     */
/*Set color for SnackBuilder*/
    public SnackBuilder textColor(int resId) {
        alertParam.textColor = resId;
        return this;
    }

    /**
     * Duration snack builder.
     *
     * @param duration the duration
     * @return the snack builder
     */
    public SnackBuilder duration(int duration) {
        alertParam.snackBarDuration = duration;
        return this;
    }

    /**
     * Background color snack builder.
     *
     * @param color the color
     * @return the snack builder
     */
    public SnackBuilder backgroundColor(int color) {
        alertParam.actionBackgroundResId = color;
        return this;
    }

    /**
     * Unique id snack builder.
     *
     * @param uniqueId the unique id
     * @return the snack builder
     */
    public SnackBuilder uniqueId(int uniqueId) {
        alertParam.alertTaskId = uniqueId;
        return this;
    }

    /**
     * Action message max lines snack builder.
     *
     * @param maxLines the max lines
     * @return the snack builder
     */
    public SnackBuilder actionMessageMaxLines(int maxLines) {
        alertParam.actionMessageMaxLine = maxLines;
        return this;
    }

    /**
     * Set typeface of text snack builder.
     *
     * @param typeface string name of assets typeface
     * @return the snack builder
     */
    @SuppressWarnings("Typeface must be defined in assets")
    public SnackBuilder typeface(String typeface) {
        alertParam.typeface = typeface;
        return this;
    }

    /**
     * Show.
     */
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
        TextView tva = (TextView) snackbar.getView().findViewById(R.id.snackbar_action);
        if (alertParam.actionMessageMaxLine != -1)
            tv.setMaxLines(alertParam.actionMessageMaxLine);
        if (alertParam.textColor != 0) {
            tv.setTextColor(ContextCompat.getColorStateList(alertParam.activityContext, alertParam.textColor));
        }
        if (alertParam.actionBackgroundResId != 0)
            snackbar.getView().setBackgroundColor(ContextCompat.getColor(alertParam.activityContext, alertParam.actionBackgroundResId));
        //check typeface
        String typeface = alertParam.getTypeface();
        if (!TextUtils.isEmpty(typeface)) {
            //set message typeface
            Alert.get().setTypeface(alertParam.context, tv, typeface);
            //set action typeface
            Alert.get().setTypeface(alertParam.context, tva, typeface);
        }

        snackbar.show();
    }


}