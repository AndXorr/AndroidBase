package android.base.util;

import android.app.Activity;
import android.base.alert.Alert;
import android.base.image.ImageUtil;
import android.base.util.categories.*;
import android.base.util.categories.System;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by clickapps on 24/11/15.
 */
public final class ApplicationUtils {


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


    // Link with other classes
    public static class AnimationUtil extends android.base.util.categories.AnimationUtil {
    }

    public static class Color extends android.base.util.categories.Color {
    }

    public static class Date extends android.base.util.categories.DateUtil {
    }

    public static class Image extends android.base.util.categories.ImageUtil {
    }

    public static class Location extends android.base.util.categories.LocationUtil {
    }

    public static class Log extends android.base.util.categories.Log {
    }

    public static class Math extends android.base.util.categories.MathUtil {
    }

    public static class Pref extends android.base.util.categories.Prefs {

    }

    public static class FileUtil extends android.base.util.categories.FileUtil {

    }

    public static class Security extends android.base.util.categories.Security {

    }

    public static class Validator extends android.base.util.categories.Validator {
    }

    public static class Voice extends android.base.util.categories.Voice {
    }


    public static class System extends android.base.util.categories.System {

    }
}
