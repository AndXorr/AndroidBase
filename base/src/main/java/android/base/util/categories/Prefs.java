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


    /**
     * Instantiates a new Prefs.
     */
    public Prefs() {
        if (sharedPreferenceApp == null) {
            ApplicationUtils.Log.e("Context can't be null. Use with(Context context)");
        }
    }

    /**
     * With prefs.
     *
     * @param context the context
     * @return the prefs
     */
    public static Prefs with(Context context) {
        sharedPreferenceApp = new SharedPreferenceApp(context);
        return new Prefs();
    }

    /**
     * Save.
     *
     * @param key   the key
     * @param value the value
     */
    public static void save(String key, boolean value) {
        sharedPreferenceApp.putBoolean(key, value);
    }

    /**
     * Save.
     *
     * @param key   the key
     * @param value the value
     */
    public static void save(String key, String value) {
        sharedPreferenceApp.putString(key, value);
    }

    /**
     * Save.
     *
     * @param key   the key
     * @param value the value
     */
    public static void save(String key, int value) {
        sharedPreferenceApp.putInt(key, value);
    }

    /**
     * Save.
     *
     * @param key   the key
     * @param value the value
     */
    public static void save(String key, float value) {
        sharedPreferenceApp.putFloat(key, value);
    }

    /**
     * Save.
     *
     * @param key   the key
     * @param value the value
     */
    public static void save(String key, long value) {
        sharedPreferenceApp.putLong(key, value);
    }

    /**
     * Gets boolean.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the boolean
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferenceApp.getBoolean(key, defaultValue);
    }

    /**
     * Gets string.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the string
     */
    public static String getString(String key, String defaultValue) {
        return sharedPreferenceApp.getString(key, defaultValue);
    }

    /**
     * Gets int.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the int
     */
    public static int getInt(String key, int defaultValue) {
        return sharedPreferenceApp.getInt(key, defaultValue);
    }

    /**
     * Gets float.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the float
     */
    public static float getFloat(String key, float defaultValue) {
        return sharedPreferenceApp.getFloat(key, defaultValue);
    }

    /**
     * Gets long.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the long
     */
    public static long getLong(String key, long defaultValue) {
        return sharedPreferenceApp.getLong(key, defaultValue);
    }

    /**
     * Remove.
     *
     * @param key the key
     */
    public static void remove(String key) {
        sharedPreferenceApp.remove(key);
    }

    /**
     * Has preference boolean.
     *
     * @param key the key
     * @return the boolean
     */
    public boolean hasPreference(String key) {
        return sharedPreferenceApp.hasPreference(key);
    }


    /**
     * Gets all.
     *
     * @return the all
     */
    public static Map<String, ?> getAll() {
        return sharedPreferenceApp.getAll();
    }

}

