package android.base.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by clickapps on 2/7/15.
 */
public class DatabaseLocale extends SQLiteOpenHelper {
    // Table Names
    private final String TABLE_LOCALE = "locale";
    // Common column names
    private final String KEY_ID = "string_key";
    private final String LANG_CODE = "lang_code";
    // TABLE_LOCAL table create statement
    private final String CREATE_TABLE_LOCALE = "CREATE TABLE "
            + TABLE_LOCALE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + LANG_CODE
            + " TEXT" + ")";

    public DatabaseLocale(Context context) {
        super(context, "Locale", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_LOCALE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_LOCALE);
        onCreate(db);
    }
}
