package android.base.alert;

import android.app.Activity;
import android.base.R;
import android.base.dialog.OnDialogProcess;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;


/**
 * The type Alert param.
 */
public class AlertParam {

    protected static final int red = 0xfff44336;
    protected static final int green = 0xff4caf50;
    protected static final int blue = 0xff2195f3;
    protected static final int orange = 0xffffc107;
    /**
     * The Message.
     */
    protected String message = "";
    /**
     * The Context.
     */
    protected Context context;
    /**
     * The Message res id.
     */
    protected int messageResId = 0;
    /**
     * The Duration.
     */
    protected int duration = Toast.LENGTH_SHORT;
    /**
     * The constant ITEM.
     */
//    Dialog class
    protected static final String ITEM = "item";
    /**
     * The Title.
     */
    protected String title = "";
    /**
     * The Title res id.
     */
    protected int titleResId = 0;
    /**
     * The Alert task id.
     */
    protected int alertTaskId = 0;
    /**
     * The Dialog type.
     */
    protected DialogType dialogType = DialogType.SINGLE_OPTION;
    /**
     * The Listener.
     */
    protected OnDialogProcess listener;
    /**
     * The Icon.
     */
    protected int icon = 0;
    /**
     * The Drawable.
     */
    protected Drawable drawable;
    /**
     * The Positive button.
     */
    protected String positiveButton, /**
     * The Negative button.
     */
    negativeButton;
    /**
     * The Positive button res id.
     */
    protected int positiveButtonResId, /**
     * The Negative button res id.
     */
    negativeButtonResId;
    /**
     * The Bundle.
     */
    protected Bundle bundle;
    /**
     * The Is cancelable.
     */
    protected boolean isCancelable = true;
    /**
     * The List.
     */
    protected String[] list;

    /**
     * The enum Dialog type.
     */
    public enum DialogType {
        /**
         * Single option dialog type.
         */
        SINGLE_OPTION, /**
         * Double option dialog type.
         */
        DOUBLE_OPTION, /**
         * Dialog list dialog type.
         */
        DIALOG_LIST
    }

    /**
     * The Activity context.
     */
//    SnackBuilder class
    protected Activity activityContext;
    /**
     * The Action message.
     */
    protected String actionMessage = "";
    /**
     * The Action message res id.
     */
    protected int actionMessageResId, /**
     * The Action message max line.
     */
    actionMessageMaxLine = -1;
    /**
     * The Snack bar mView.
     */
    protected View snackBarView;
    /**
     * The Action color res id.
     */
    protected int actionColorResId = R.color.colorPrimary_base, /**
     * The Text color.
     */
    textColor = R.color.colorPrimary_base;
    /**
     * The Action background res id.
     */
    protected int actionBackgroundResId = R.color.colorPrimaryDark_base;
    /**
     * The Snack bar duration.
     */
    protected int snackBarDuration = Snackbar.LENGTH_LONG;
    /**
     * The On snack bar action listener.
     */
    protected OnSnackBarActionListener onSnackBarActionListener;

    /**
     * Type face for all the alerts defined for whole the application's alerts
     */
    private static String typefaceAllAlerts;
    /*Typeface for the specific alert*/
    protected String typeface;

    /**
     * The interface On snack bar action listener.
     */
    public interface OnSnackBarActionListener {
        /**
         * On snack bar action clicked.
         *
         * @param uniqueId the unique id
         * @param view     the mView
         */
        void onSnackBarActionClicked(int uniqueId, View view);
    }

    /**
     * Text font for the alerts
     *
     * @param typeface String typeface
     */
    @SuppressWarnings("Typeface must be defined in assets folder")
    public static void setTextTypeface(String typeface) {
        typefaceAllAlerts = typeface;
    }

    public String getTypeface() {
        return TextUtils.isEmpty(typeface)
                ? typefaceAllAlerts
                : typeface;
    }
}
