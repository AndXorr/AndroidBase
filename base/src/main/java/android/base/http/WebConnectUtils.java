package android.base.http;

import android.base.R;
import android.base.dialog.BaseProgressDialog;
import android.text.TextUtils;

/**
 * Created by clickapps on 19/11/15.
 */
public class WebConnectUtils {
    private static final int DEFAULT_MESSAGE = R.string.loading;

    public static BaseProgressDialog resolveProgressDialog(WebParam webParam) {
        if (webParam.progressDialog == null) {
            BaseProgressDialog progressDialog = new BaseProgressDialog(webParam.context);
            progressDialog.setMessage(TextUtils.isEmpty(webParam.progressDialogMessage)
                    ? webParam.context.getString(DEFAULT_MESSAGE) : webParam.progressDialogMessage);
            webParam.progressDialog = progressDialog;
            webParam.progressDialog.setCanceledOnTouchOutside(false);
            webParam.progressDialog.setCancelable(true);
        } else {
            webParam.progressDialog.setCanceledOnTouchOutside(false);
            webParam.progressDialog.setCancelable(true);
        }
        return webParam.progressDialog;
    }

}
