package android.base.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.base.R;
import android.base.constant.Constant;
import android.base.log.Log;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.inputmethod.InputMethodManager;

import java.io.File;

/**
 * Created by clickapps on 24/11/15.
 */
public final class ApplicationUtils {
    private static final int NONE = 0;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean isLollipop() {
        if (Build.VERSION.SDK_INT >= Constant.BUILD_VERSION_LOLLIPOP) {
            return true;
        }
        return false;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean isJellyBean() {
        if (Build.VERSION.SDK_INT >= Constant.BUILD_VERSION_JELLY_BEAN_MR1) {
            return true;
        }
        return false;
    }

    public static void logEnable(boolean value) {
        Log.setLogEnabled(value);
    }

    public static String getFontName(Context context, int resId) {
        switch (resId) {
            case NONE:
                context.getString(R.string.typeface_default_base);
                break;
            default:
                break;
        }
        return "";
    }

    /**
     * This method is used to show or hide keyboard.
     *
     * @param context      set context of calling class
     * @param isShowOrHide if pass true then show keyboard else hide keyboard
     */
    public static void showHideKeyboard(Activity context, boolean isShowOrHide) {
        try {
            InputMethodManager imm = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            if (isShowOrHide) {
                imm.showSoftInput(context.getCurrentFocus(),
                        InputMethodManager.SHOW_IMPLICIT);
            } else {
                if (imm.isAcceptingText()) {
                    imm.hideSoftInputFromWindow(context
                            .getCurrentFocus().getWindowToken(), 0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void gallery(Activity context, int requestCode) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        context.startActivityForResult(intent, requestCode);
    }

    /**
     * This method is used to call inbuild camera of device
     */
    public static final void camera(Activity context, int requestCode, File imagePath) {
        Intent intent = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        if (imagePath == null)
            imagePath = FileUtils.getStaticFile(context, "");
        Uri mUri = Uri.fromFile(imagePath);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
        intent.putExtra("return-data", true);
        context.startActivityForResult(intent, requestCode);

    }
}
