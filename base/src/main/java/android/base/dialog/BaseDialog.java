package android.base.dialog;

import android.app.Dialog;
import android.base.R;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


/**
 * This class used for the base class of all dialogs in the application.
 *
 * @author amit.singh
 * @implements OnClickListener
 */
public class BaseDialog extends Dialog implements View.OnClickListener {

    public BaseDialog(Context context) {
        super(context);
        hideTitle();
    }

    public BaseDialog(Context context, int theme) {
        super(context, theme);
        hideTitle();
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * hide the dialog title
     */
    private void hideTitle() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(getContext(), R.color.app_theme_base));
            window.setNavigationBarColor(ContextCompat.getColor(getContext(), R.color.app_theme_base));
        }
    }

}
