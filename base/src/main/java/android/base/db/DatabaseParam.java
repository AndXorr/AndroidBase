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
    protected Context context;
    protected SQLiteOpenHelper sqLiteOpenHelper;
    protected SQLiteDatabase dRead, dWrite;
    protected String table, where, orderBy, grpBy, having, limit, sql;
    protected String[] columns, whereArgs;
    protected Map<String, String> map = new LinkedHashMap<>();
    protected boolean isBulk = false;
    protected CRUD crud;

    public enum CRUD {
        FETCH {
            @Override
            public Object execute(DatabaseParam param) {
                return null;
            }
        }, FETCH_RAW {
            @Override
            public Object execute(DatabaseParam param) {
                return null;
            }
        }, INSERT {
            @Override
            public Object execute(DatabaseParam param) {
                return null;
            }
        }, DELETE {
            @Override
            public Object execute(DatabaseParam param) {
                return null;
            }
        }, UPDATE {
            @Override
            public Object execute(DatabaseParam param) {
                return null;
            }
        };

        public abstract Object execute(DatabaseParam param);
    }
}
