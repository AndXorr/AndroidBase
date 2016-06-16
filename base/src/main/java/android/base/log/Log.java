package android.base.log;


/**
 * The type Log.
 */
public final class Log {
    private static boolean isLog = true;

    /**
     * Sets log enabled.
     *
     * @param isLogEnabled the is log enabled
     */
/* Used to enable/disable the log feature */
    public static void setLogEnabled(boolean isLogEnabled) {
        isLog = isLogEnabled;
    }

    /**
     * Is log enabled boolean.
     *
     * @return the boolean
     */
/* return whether log is enabled or not */
    public static boolean isLogEnabled() {
        return isLog;
    }

    /**
     * .
     *
     * @param tag     the tag
     * @param message the message
     */
    public static void i(String tag, String message) {
        if (isLogEnabled())
            android.util.Log.i(tag, message);
    }

    /**
     * E.
     *
     * @param tag     the tag
     * @param message the message
     */
    public static void e(String tag, String message) {
        if (isLogEnabled())
            android.util.Log.e(tag, message);
    }

    /**
     * V.
     *
     * @param tag     the tag
     * @param message the message
     */
    public static void v(String tag, String message) {
        if (isLogEnabled())
            android.util.Log.v(tag, message);
    }

    /**
     * D.
     *
     * @param tag     the tag
     * @param message the message
     */
    public static void d(String tag, String message) {
        if (isLogEnabled())
            android.util.Log.d(tag, message);
    }
}


