package android.base.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.base.R;
import android.base.alert.Alert;
import android.base.constant.Constant;
import android.base.image.ImageUtil;
import android.base.log.Log;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.inputmethod.InputMethodManager;

import java.io.File;

/**
 * Created by clickapps on 24/11/15.
 */
public final class ApplicationUtils {
    private static final int PROXIMANOVA = 0;
    private static final int ROBOTIC_REGULAR = 1;
    private static final int ROBOTIC_LIGHT = 2;
    private static final int ROBOTIC_THIN = 3;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean isLollipop() {
        return Build.VERSION.SDK_INT >= Constant.BUILD_VERSION_LOLLIPOP;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean isLollipopOrBelow() {
        return Build.VERSION.SDK_INT <= Constant.BUILD_VERSION_LOLLIPOP;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean isJellyBean() {
        return Build.VERSION.SDK_INT >= Constant.BUILD_VERSION_JELLY_BEAN_MR1;
    }

    public static void logEnable(boolean value) {
        Log.setLogEnabled(value);
    }

    public static String getFontName(Context context, int resId, @StringRes int stringRes) {
        String fontName;
        switch (resId) {
            case PROXIMANOVA:
                fontName = getFont(context, R.string.typeface_proximanova_regular);
                break;
            case ROBOTIC_REGULAR:
                fontName = getFont(context, R.string.typeface_roboto_regular);
                break;
            case ROBOTIC_LIGHT:
                fontName = getFont(context, R.string.typeface_roboto_light);
                break;
            case ROBOTIC_THIN:
                fontName = getFont(context, R.string.typeface_roboto_thin);
                break;
            default:
                fontName = getFont(context, stringRes);
                break;
        }
        return fontName;
    }

    public static String getFont(Context context, @StringRes int resId) {
        if (resId != -1)
            return context.getString(resId);
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
    public static void camera(Activity context, int requestCode, File imagePath) {
        Intent intent = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        if (imagePath == null)
            imagePath = FileUtil.getStaticFile(context, "");
        Uri mUri = Uri.fromFile(imagePath);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
        intent.putExtra("return-data", true);
        context.startActivityForResult(intent, requestCode);
    }

    public static String getCurrentMethodName() {
        return Thread.currentThread().getStackTrace()[3].getMethodName();
    }

    public static Drawable getTintDrawable(@NonNull Context context, @DrawableRes int drawable, int resId) {
        Drawable d = ContextCompat.getDrawable(context, drawable);
        DrawableCompat.wrap(d);
        DrawableCompat.setTintList(d, ContextCompat.getColorStateList(context, resId));
        return d;
    }

    // Link with other classes
    public static class animation extends android.base.util.categories.animation {
    }

    public static class color extends android.base.util.categories.color {
    }

    public static class date extends android.base.util.categories.date {
    }

    public static class image extends android.base.util.categories.image {
    }

    public static class location extends android.base.util.categories.location {
    }

    public static class log extends android.base.util.categories.log {
    }

    public static class math extends android.base.util.categories.math {
    }

    public static class pref extends android.base.util.categories.prefs {

        protected pref(Context context) {
            super(context);
        }
    }

    public static class sdcard extends android.base.util.categories.sdcard {

    }

    public static class security extends android.base.util.categories.security {

    }

    public static class validator extends android.base.util.categories.validator {
    }

    public static class voice extends android.base.util.categories.voice {
    }

    public static class toast extends Alert {
    }

    public static class dialog extends Alert {
    }

    public static class snakebar extends Alert {
    }

    public static class imageUtil extends ImageUtil {
    }
}
