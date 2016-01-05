package android.base.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by clickapps on 27/11/15.
 */
public final class DatabaseParam {
    public Context context;
    public SQLiteOpenHelper sqLiteOpenHelper;
    public SQLiteDatabase dRead, dWrite;
    public String table, where, orderBy, grpBy, having, limit, sql;
    public String[] columns, whereArgs;
    public Map<String, String> map = new LinkedHashMap<>();
    public boolean isBulk = false;
    public CRUD crud;

    public enum CRUD {
        FETCH, FETCH_RAW, INSERT, DELETE, UPDATE;
    }
}
