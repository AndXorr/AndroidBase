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
    public String message = "";
    public Context context;
    public int messageResId = 0, duration = Toast.LENGTH_SHORT;
    //    Dialog class
    public static final String ITEM = "item";
    public String title = "";
    public int titleResId = 0;
    public int alertTaskId = 0;
    public DialogType dialogType = DialogType.SINGLE_OPTION;
    public OnDialogProcess listener;
    public int icon = 0;
    public Drawable drawable;
    public String positiveButton, negativeButton;
    public int positiveButtonResId, negativeButtonResId;
    public Bundle bundle;
    public boolean isCancelable = true;
    public String[] list;

    public enum DialogType {
        SINGLE_OPTION, DOUBLE_OPTION, DIALOG_LIST
    }

    //    SnackBar class
    public Activity activityContext;
    public String actionMessage = "";
    public int actionMessageResId, actionMessageMaxLine = -1;
    public View snackBarView;
    public int actionColorResId = R.color.colorPrimary_base, textColor = R.color.colorPrimary_base;
    public int actionBackgroundResId = R.color.colorPrimaryDark_base;
    public int snackBarDuration = Snackbar.LENGTH_LONG;
    public OnSnackBarActionListener onSnackBarActionListener;

    public interface OnSnackBarActionListener {
        void onSnackBarActionClicked(int uniqueId, View view);
    }
}
