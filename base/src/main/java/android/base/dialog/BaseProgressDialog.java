package android.base.dialog;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Sahni on 26-11-2015.
 */
public class BaseProgressDialog extends ProgressDialog {
    public BaseProgressDialog(Context context) {
        super(context);
    }

    public BaseProgressDialog(Context context, int theme) {
        super(context, theme);
    }
}
