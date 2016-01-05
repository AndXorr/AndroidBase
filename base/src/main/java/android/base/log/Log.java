package android.base.log;

/**
 * Created by Sahni on 28-07-2015.
 */
public final class Log {
    private static boolean isLog = true;

    /* Used to enable/disable the log feature */
    public static void setLogEnabled(boolean isLogEnabled) {
        isLog = isLogEnabled;
    }

    /* return whether log is enabled or not */
    public static boolean isLogEnabled() {
        return isLog;
    }

    public static void i(String tag, String message) {
        if (isLogEnabled())
            android.util.Log.i(tag, message);
    }

    public static void e(String tag, String message) {
        if (isLogEnabled())
            android.util.Log.e(tag, message);
    }

    public static void v(String tag, String message) {
        if (isLogEnabled())
            android.util.Log.v(tag, message);
    }

    public static void d(String tag, String message) {
        if (isLogEnabled())
            android.util.Log.d(tag, message);
    }
}


