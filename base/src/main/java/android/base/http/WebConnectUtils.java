package android.base.http;

import android.base.R;
import android.base.ui.custom.ProgressViewApplication;
import android.base.dialog.BaseProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;


/**
 * The type Web connect utils.
 */
public class WebConnectUtils {
    private static final int DEFAULT_MESSAGE = R.string.loading;

    /**
     * Resolve progress dialog base progress dialog.
     *
     * @param webParam the web param
     * @return the base progress dialog
     */
    public static BaseProgressDialog resolveProgressDialog(WebParam webParam) {
        if (webParam.progressDialog == null) {
            BaseProgressDialog progressDialog = new ProgressViewApplication(webParam.context).getProgressDialog();
            progressDialog.setMessage(TextUtils.isEmpty(webParam.progressDialogMessage)
                    ? webParam.context.getString(DEFAULT_MESSAGE) : webParam.progressDialogMessage);
            webParam.progressDialog = progressDialog;
            webParam.progressDialog.setCanceledOnTouchOutside(false);
            webParam.progressDialog.setCancelable(true);
        } else {
            webParam.progressDialog.setCanceledOnTouchOutside(false);
            webParam.progressDialog.setCancelable(true);
        }
        if (webParam.progressDialogColor != -1) {
            ColorDrawable colorDrawable = new ColorDrawable(ContextCompat.getColor(webParam.context, webParam.progressDialogColor));
            webParam.progressDialog.setIndeterminateDrawable(colorDrawable);
        }
        return webParam.progressDialog;
    }

    public static BaseProgressDialog defaultProgressDialog(@NonNull Context context) {
        BaseProgressDialog dialog = new BaseProgressDialog(context, R.style.Widget_ProgressDialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        return dialog;
    }


}
