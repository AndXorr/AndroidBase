package android.base.util.categories;

import android.base.preferences.SharedPreferenceApp;
import android.base.util.ApplicationUtils;
import android.content.Context;

import java.util.Map;

/**
 * Created by cesarferreira on 9/26/14.
 */
public class Prefs {

    private static SharedPreferenceApp sharedPreferenceApp;


    public Prefs() {
        if (sharedPreferenceApp == null) {
            ApplicationUtils.Log.e("Context can't be null. Use with(Context context)");
        }
    }

    public static Prefs with(Context context) {
        sharedPreferenceApp = new SharedPreferenceApp(context);
        return new Prefs();
    }

    public static void save(String key, boolean value) {
        sharedPreferenceApp.putBoolean(key, value);
    }

    public static void save(String key, String value) {
        sharedPreferenceApp.putString(key, value);
    }

    public static void save(String key, int value) {
        sharedPreferenceApp.putInt(key, value);
    }

    public static void save(String key, float value) {
        sharedPreferenceApp.putFloat(key, value);
    }

    public static void save(String key, long value) {
        sharedPreferenceApp.putLong(key, value);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferenceApp.getBoolean(key, defaultValue);
    }

    public static String getString(String key, String defaultValue) {
        return sharedPreferenceApp.getString(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue) {
        return sharedPreferenceApp.getInt(key, defaultValue);
    }

    public static float getFloat(String key, float defaultValue) {
        return sharedPreferenceApp.getFloat(key, defaultValue);
    }

    public static long getLong(String key, long defaultValue) {
        return sharedPreferenceApp.getLong(key, defaultValue);
    }

    public static void remove(String key) {
        sharedPreferenceApp.remove(key);
    }

    public boolean hasPreference(String key) {
        return sharedPreferenceApp.hasPreference(key);
    }


    public static Map<String, ?> getAll() {
        return sharedPreferenceApp.getAll();
    }

}

