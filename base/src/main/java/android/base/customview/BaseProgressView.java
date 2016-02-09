package android.base.customview;

import android.base.R;
import android.base.dialog.BaseProgressDialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Created by Sahni on 08-01-2016.
 */
public class BaseProgressView extends ProgressBar {

    public BaseProgressView(Context context) {
        super(context);
    }

    public BaseProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BaseProgressDialog getDialog() {
        BaseProgressDialog dialog = new BaseProgressDialog(getContext(), R.style.Widget_ProgressDialog);
        if (getIndeterminateDrawable() == null) {
            dialog.setIndeterminateDrawable(ContextCompat.getDrawable(getContext(), R.drawable.dialog_progress_indeterminate));
        } else {
            dialog.setIndeterminateDrawable(getIndeterminateDrawable());
        }
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        return dialog;
    }
}
