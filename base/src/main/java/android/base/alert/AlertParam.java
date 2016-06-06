package android.base.alert;

import android.app.Activity;
import android.base.R;
import android.base.dialog.OnDialogProcess;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;


/**
 * Created by Sahni on 28-07-2015.
 */
public class AlertParam {
    protected String message = "";
    protected Context context;
    protected int messageResId = 0, duration = Toast.LENGTH_SHORT;
    //    Dialog class
    protected static final String ITEM = "item";
    protected String title = "";
    protected int titleResId = 0;
    protected int alertTaskId = 0;
    protected DialogType dialogType = DialogType.SINGLE_OPTION;
    protected OnDialogProcess listener;
    protected int icon = 0;
    protected Drawable drawable;
    protected String positiveButton, negativeButton;
    protected int positiveButtonResId, negativeButtonResId;
    protected Bundle bundle;
    protected boolean isCancelable = true;
    protected String[] list;

    public enum DialogType {
        SINGLE_OPTION, DOUBLE_OPTION, DIALOG_LIST
    }

    //    SnackBuilder class
    protected Activity activityContext;
    protected String actionMessage = "";
    protected int actionMessageResId, actionMessageMaxLine = -1;
    protected View snackBarView;
    protected int actionColorResId = R.color.colorPrimary_base, textColor = R.color.colorPrimary_base;
    protected int actionBackgroundResId = R.color.colorPrimaryDark_base;
    protected int snackBarDuration = Snackbar.LENGTH_LONG;
    protected OnSnackBarActionListener onSnackBarActionListener;

    public interface OnSnackBarActionListener {
        void onSnackBarActionClicked(int uniqueId, View view);
    }
}
