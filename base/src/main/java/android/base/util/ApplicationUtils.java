package android.base.util;

import android.app.Activity;
import android.base.alert.Alert;
import android.base.image.ImageUtil;
import android.base.util.categories.*;
import android.base.util.categories.System;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;


/**
 * The type Application utils.
 */
public final class ApplicationUtils {
    private ApplicationUtils() {
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
            Log.e(e.getMessage());
        }
    }


    /**
     * The type Animation util.
     */
// Link with other classes
    public static class AnimationUtil extends android.base.util.categories.AnimationUtil {
    }

    /**
     * The type Color.
     */
    public static class Color extends android.base.util.categories.Color {
    }

    /**
     * The type Date.
     */
    public static class Date extends android.base.util.categories.DateUtil {
    }

    /**
     * The type Image.
     */
    public static class Image extends android.base.util.categories.ImageUtil {
    }

    /**
     * The type Location.
     */
    public static class Location extends android.base.util.categories.LocationUtil {
    }

    /**
     * The type Log.
     */
    public static class Log extends android.base.util.categories.Log {
    }

    /**
     * The type Math.
     */
    public static class Math extends android.base.util.categories.MathUtil {
    }

    /**
     * The type Pref.
     */
    public static class Pref extends android.base.util.categories.Prefs {

    }

    /**
     * The type File util.
     */
    public static class FileUtil extends android.base.util.categories.FileUtil {

    }

    /**
     * The type Security.
     */
    public static class Security extends android.base.util.categories.Security {

    }

    /**
     * The type Validator.
     */
    public static class Validator extends android.base.util.categories.Validator {
    }

    /**
     * The type Voice.
     */
    public static class Voice extends android.base.util.categories.Voice {
    }


    /**
     * The type System.
     */
    public static class System extends android.base.util.categories.System {

    }
}
