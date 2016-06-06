package android.base.db;

import android.base.log.Log;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.Map;

/**
 * Created by clickapps on 27/11/15.
 */
public final class DatabaseManager {
    private DatabaseManager() {
        // private constructor
    }

    public static Builder with(@NonNull Context context) {
        return new Builder(context);
    }

    public static Builder with(@NonNull Context context, @NonNull SQLiteOpenHelper sqLiteOpenHelper) {
        return new Builder(context);
    }
}
