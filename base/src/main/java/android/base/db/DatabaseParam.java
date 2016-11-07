package android.base.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * The type Database param.
 */
public final class DatabaseParam {
    /**
     * The Context.
     */
    protected Context context;
    /**
     * The Sq lite open helper.
     */
    protected SQLiteOpenHelper sqLiteOpenHelper;
    /**
     * The D read.
     */
    protected SQLiteDatabase dRead, /**
     * The D write.
     */
    dWrite;
    /**
     * The Table.
     */
    protected String table, /**
     * The Where.
     */
    where, /**
     * The Order by.
     */
    orderBy, /**
     * The Grp by.
     */
    grpBy, /**
     * The Having.
     */
    having, /**
     * The Limit.
     */
    limit, /**
     * The Sql.
     */
    sql;
    /**
     * The Columns.
     */
    protected String[] columns, /**
     * The Where args.
     */
    whereArgs;
    /**
     * The Map.
     */
    protected Map<String, String> map = new LinkedHashMap<>();
    /**
     * The Is bulk.
     */
    protected boolean isBulk = false;
    /**
     * The Crud.
     */
    protected CRUD crud;

    /**
     * The enum Crud.
     */
    public enum CRUD {
        /**
         * The Fetch.
         */
        FETCH {
            @Override
            public Object execute(DatabaseParam param) {
                return null;
            }
        }, /**
         * The Fetch raw.
         */
        FETCH_RAW {
            @Override
            public Object execute(DatabaseParam param) {
                return null;
            }
        }, /**
         * The Insert.
         */
        INSERT {
            @Override
            public Object execute(DatabaseParam param) {
                return null;
            }
        }, /**
         * The Delete.
         */
        DELETE {
            @Override
            public Object execute(DatabaseParam param) {
                return null;
            }
        }, /**
         * The Update.
         */
        UPDATE {
            @Override
            public Object execute(DatabaseParam param) {
                return null;
            }
        };

        /**
         * Execute object.
         *
         * @param param the param
         * @return the object
         */
        public abstract Object execute(DatabaseParam param);
    }
}
