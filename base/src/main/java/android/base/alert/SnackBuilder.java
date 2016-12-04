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

    private AlertParam mAlertParam;
    private Snackbar mSnackbar;

    /**
     * Instantiates a new Snack builder.
     *
     * @param context the context
     * @param resId   the res id
     */
    public SnackBuilder(Activity context, @StringRes int resId) {
        mAlertParam = new AlertParam();
        mAlertParam.activityContext = context;
        mAlertParam.context = context;
        mAlertParam.messageResId = resId;
    }

    /**
     * Instantiates a new Snack builder.
     *
     * @param context the context
     * @param msg     the msg
     */
    public SnackBuilder(Activity context, String msg) {
        mAlertParam = new AlertParam();
        mAlertParam.activityContext = context;
        mAlertParam.context = context;
        mAlertParam.message = msg;
    }

    /**
     * Instantiates a new Snack builder.
     *
     * @param context         the context
     * @param resId           the res id
     * @param backgroundColor background color of mSnackbar
     */
    public SnackBuilder(Activity context, @StringRes int resId, @ColorRes int backgroundColor) {
        mAlertParam = new AlertParam();
        mAlertParam.activityContext = context;
        mAlertParam.context = context;
        mAlertParam.messageResId = resId;
        mAlertParam.actionBackgroundResId = backgroundColor;
    }

    /**
     * Instantiates a new Snack builder.
     *
     * @param context         the context
     * @param msg             the msg
     * @param backgroundColor background color of mSnackbar
     */
    public SnackBuilder(Activity context, String msg, @ColorRes int backgroundColor) {
        mAlertParam = new AlertParam();
        mAlertParam.activityContext = context;
        mAlertParam.context = context;
        mAlertParam.message = msg;
        mAlertParam.actionBackgroundResId = backgroundColor;
    }

    /**
     * Action message snack builder.
     *
     * @param actionMessage the action message
     * @return the snack builder
     */
/*Set ActionMessage for SnackBuilder*/
    public SnackBuilder actionMessage(String actionMessage) {
        mAlertParam.actionMessage = actionMessage;
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
        mAlertParam.actionMessageResId = resId;
        return this;
    }

    /**
     * View snack builder.
     *
     * @param view the mView
     * @return the snack builder
     */
/*Set View for SnackBuilder*/
    public SnackBuilder view(View view) {
        mAlertParam.snackBarView = view;
        mAlertParam.snackBarView.setFocusable(false);
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
        mAlertParam.onSnackBarActionListener = l;
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
        mAlertParam.actionColorResId = resId;
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
        mAlertParam.textColor = resId;
        return this;
    }

    /**
     * Duration snack builder.
     *
     * @param duration the duration
     * @return the snack builder
     */
    public SnackBuilder duration(int duration) {
        mAlertParam.snackBarDuration = duration;
        return this;
    }

    /**
     * Background color snack builder.
     *
     * @param color the color
     * @return the snack builder
     */
    public SnackBuilder backgroundColor(int color) {
        mAlertParam.actionBackgroundResId = color;
        return this;
    }

    /**
     * Background color snack builder during information
     *
     * @return the snack builder
     */
    public SnackBuilder info() {
        mAlertParam.actionBackgroundResId = AlertParam.blue;
        return this;
    }

    /**
     * Background color snack builder during confirmation
     *
     * @return the snack builder
     */
    public SnackBuilder confirm() {
        mAlertParam.actionBackgroundResId = AlertParam.green;
        return this;
    }

    /**
     * Background color snack builder during warning
     *
     * @return the snack builder
     */
    public SnackBuilder warning() {
        mAlertParam.actionBackgroundResId = AlertParam.orange;
        return this;
    }

    /**
     * Background color snack builder during warning
     *
     * @return the snack builder
     */
    public SnackBuilder alert() {
        mAlertParam.actionBackgroundResId = AlertParam.red;
        return this;
    }

    /**
     * Unique id snack builder.
     *
     * @param uniqueId the unique id
     * @return the snack builder
     */
    public SnackBuilder uniqueId(int uniqueId) {
        mAlertParam.alertTaskId = uniqueId;
        return this;
    }

    /**
     * Action message max lines snack builder.
     *
     * @param maxLines the max lines
     * @return the snack builder
     */
    public SnackBuilder actionMessageMaxLines(int maxLines) {
        mAlertParam.actionMessageMaxLine = maxLines;
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
        mAlertParam.typeface = typeface;
        return this;
    }

    /**
     * Show.
     */
    public void show() {
        if (mSnackbar != null)
            mSnackbar.dismiss();
        if (mAlertParam.activityContext == null) {
            return;
        }
        if (mAlertParam.snackBarView == null) {
            mAlertParam.snackBarView = mAlertParam.activityContext.findViewById(android.R.id.content);

        }
        mSnackbar = Snackbar.make(mAlertParam.snackBarView, "", mAlertParam.snackBarDuration);
        // Checked for Message
        if (mAlertParam.messageResId != 0) {
            mSnackbar.setText(mAlertParam.messageResId);
        } else if (!TextUtils.isEmpty(mAlertParam.message)) {
            mSnackbar.setText(mAlertParam.message);
        } else {
            mSnackbar.setText("");
        }
        // checked for ActionMessage
        if (mAlertParam.actionMessageResId != 0) {
            mSnackbar.setAction(mAlertParam.actionMessageResId, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mAlertParam.onSnackBarActionListener == null) {
                        return;
                    }
                    mAlertParam.onSnackBarActionListener.onSnackBarActionClicked(mAlertParam.alertTaskId, v);
                }
            });
        } else if (!TextUtils.isEmpty(mAlertParam.actionMessage)) {
            mSnackbar.setAction(mAlertParam.actionMessage, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mAlertParam.onSnackBarActionListener == null) {
                        return;
                    }
                    mAlertParam.onSnackBarActionListener.onSnackBarActionClicked(mAlertParam.alertTaskId, v);
                }
            });
        } else {
            mSnackbar.setAction("", null);
        }
        // checked for actionMessagecolor
        if (mAlertParam.actionColorResId != 0) {
            mSnackbar.setActionTextColor(ContextCompat.getColor(mAlertParam.activityContext, mAlertParam.actionColorResId));
        }
        TextView tv = (TextView) mSnackbar.getView().findViewById(R.id.snackbar_text);
        TextView tva = (TextView) mSnackbar.getView().findViewById(R.id.snackbar_action);
        if (mAlertParam.actionMessageMaxLine != -1)
            tv.setMaxLines(mAlertParam.actionMessageMaxLine);
        if (mAlertParam.textColor != 0) {
            tv.setTextColor(ContextCompat.getColorStateList(mAlertParam.activityContext, mAlertParam.textColor));
        }
        if (mAlertParam.actionBackgroundResId != 0)
            mSnackbar.getView().setBackgroundColor(ContextCompat.getColor(mAlertParam.activityContext, mAlertParam.actionBackgroundResId));
        //check typeface
        String typeface = mAlertParam.getTypeface();
        if (!TextUtils.isEmpty(typeface)) {
            //set message typeface
            Alert.get().setTypeface(mAlertParam.context, tv, typeface);
            //set action typeface
            Alert.get().setTypeface(mAlertParam.context, tva, typeface);
        }

        mSnackbar.show();
    }


}