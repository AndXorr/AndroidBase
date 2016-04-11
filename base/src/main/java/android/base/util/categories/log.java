package android.base.util.categories;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class log {
    public static final int VERBOSE = android.util.Log.VERBOSE;
    public static final int DEBUG = android.util.Log.DEBUG;
    public static final int INFO = android.util.Log.INFO;
    public static final int WARN = android.util.Log.WARN;
    public static final int ERROR = android.util.Log.ERROR;
    public static final String TAG = "log";
    /**
     * private constructor
     */
//	private log() {
//	}

    /**
     * Sends an ERROR log message
     *
     * @param message The message you would like logged.
     */
    public static int e(String message) {
        return logger(ERROR, message);
    }

    /**
     * Sends an ERROR log message
     *
     * @param message   The message you would like logged.
     * @param throwable An exception to log
     */
    public static int e(String message, Throwable throwable) {
        return logger(ERROR, message, throwable);
    }

    /**
     * Sends an INFO log message.
     *
     * @param message The message you would like logged.
     */
    public static int i(String message) {
        return logger(INFO, message);
    }

    /**
     * Sends an INFO log message.
     *
     * @param message   The message you would like logged.
     * @param throwable An exception to log
     */
    public static int i(String message, Throwable throwable) {
        return logger(INFO, message, throwable);
    }

    /**
     * Sends a VERBBOSE log message.
     *
     * @param message The message you would like logged.
     */
    public static int v(String message) {
        return logger(VERBOSE, message);
    }

    /**
     * Sends a VERBBOSE log message.
     *
     * @param message   The message you would like logged.
     * @param throwable An exception to log
     */
    public static int v(String message, Throwable throwable) {
        return logger(VERBOSE, message, throwable);
    }

    /**
     * Sends a WARNING log message.
     *
     * @param message The message you would like logged.
     */
    public static int w(String message) {
        return logger(WARN, message);
    }

    /**
     * Sends a WARNING log message.
     *
     * @param message   The message you would like logged.
     * @param throwable An exception to log
     */
    public static int w(String message, Throwable throwable) {
        return logger(WARN, message, throwable);
    }

    /**
     * Sends a DEBUG log message.
     *
     * @param message The message you would like logged.
     */
    public static int d(String message) {
        return logger(DEBUG, message);
    }

    /**
     * Sends a DEBUG log message and log the exception.
     *
     * @param message   The message you would like logged.
     * @param throwable An exception to log
     */
    public static int d(String message, Throwable throwable) {
        return logger(DEBUG, message, throwable);

    }

    /**
     * Private Logger function to handle Log calls
     *
     * @param level     level of the log message
     * @param message   log output
     * @param throwable
     */
    private static int logger(int level, String message, Throwable throwable) {

//        if (shouldShowLogs()) {
            switch (level) {

                case DEBUG:
                    return android.util.Log.d(TAG, message, throwable);
                case VERBOSE:
                    return android.util.Log.v(TAG, message, throwable);
                case INFO:
                    return android.util.Log.i(TAG, message, throwable);
                case WARN:
                    return android.util.Log.w(TAG, message, throwable);
                case ERROR:
                    return android.util.Log.e(TAG, message, throwable);
                default:
                    break;
            }
//        }

        return -1;
    }

    /**
     * Private Logger function to handle Log calls
     *
     * @param level   level of the log message
     * @param message log output
     */
    private static int logger(int level, String message) {

//        if (shouldShowLogs()) {
            switch (level) {

                case DEBUG:
                    return android.util.Log.d(TAG, message);
                case VERBOSE:
                    return android.util.Log.v(TAG, message);
                case INFO:
                    return android.util.Log.i(TAG, message);
                case WARN:
                    return android.util.Log.w(TAG, message);
                case ERROR:
                    return android.util.Log.e(TAG, message);
                default:
                    break;
            }
//        }

        return -1;
    }

    public static void staticFields(Class<?> clazz) throws IllegalAccessException {
        for (Field f : clazz.getDeclaredFields()) {
            if (Modifier.isStatic(f.getModifiers())) {
                boolean wasAccessible = f.isAccessible();
                f.setAccessible(true);
                log.i(f.getName() + ": " + f.get(null));
                f.setAccessible(wasAccessible);
            }
        }
    }

    public static void privateFields(Class<?> clazz) throws IllegalAccessException {
        for (Field f : clazz.getDeclaredFields()) {
            if (Modifier.isPrivate(f.getModifiers())) {
                boolean wasAccessible = f.isAccessible();
                f.setAccessible(true);
                log.i(f.getName() + ": " + f.get(null));
                f.setAccessible(wasAccessible);
            }
        }
    }
}
