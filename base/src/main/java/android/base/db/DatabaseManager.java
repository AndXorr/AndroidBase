package android.base.db;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;


/**
 * The type Database manager.
 */
public final class DatabaseManager {
    private static volatile DatabaseManager sDatabaseManager;

    private DatabaseManager() {
        // private constructor
    }

    /**
     * Get database manager.
     *
     * @return the database manager
     */
    public static DatabaseManager get() {
        if (sDatabaseManager == null) {
            synchronized (DatabaseManager.class) {
                if (sDatabaseManager == null) {
                    sDatabaseManager = new DatabaseManager();
                }
            }
        }
        return sDatabaseManager;
    }

    /**
     * With builder.
     *
     * @param context the context
     * @return the builder
     */
    public static Builder with(@NonNull Context context) {
        return new Builder(context);
    }

    /**
     * With builder.
     *
     * @param context          the context
     * @param sqLiteOpenHelper the sq lite open helper
     * @return the builder
     */
    public static Builder with(@NonNull Context context, @NonNull SQLiteOpenHelper sqLiteOpenHelper) {
        return new Builder(context);
    }
}
