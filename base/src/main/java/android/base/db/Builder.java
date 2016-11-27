package android.base.db;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.Map;


/**
 * The type Builder.
 */
public class Builder {
    private DatabaseParam mParam;

    /**
     * Instantiates a new Builder.
     *
     * @param context the context
     */
    public Builder(@NonNull Context context) {
        mParam = new DatabaseParam();
        mParam.context = context;
        defaultDB();
    }

    /**
     * Instantiates a new Builder.
     *
     * @param context          the context
     * @param sqLiteOpenHelper the sq lite open helper
     */
    public Builder(@NonNull Context context, @NonNull SQLiteOpenHelper sqLiteOpenHelper) {
        mParam = new DatabaseParam();
        mParam.context = context;
        mParam.sqLiteOpenHelper = sqLiteOpenHelper;
        mParam.dRead = mParam.sqLiteOpenHelper.getReadableDatabase();
        mParam.dWrite = mParam.sqLiteOpenHelper.getWritableDatabase();
    }

    private void defaultDB() {
        DatabaseLocale db = new DatabaseLocale(mParam.context);
        mParam.dRead = db.getReadableDatabase();
        mParam.dWrite = db.getWritableDatabase();
    }

    /**
     * Query builder.
     *
     * @param table     the table
     * @param columns   the columns
     * @param where     the where
     * @param whereArgs the where args
     * @return the builder
     */
    public Builder query(String table, String[] columns, String where,
                         String[] whereArgs) {
        mParam.table = table;
        mParam.columns = columns;
        mParam.where = where;
        mParam.whereArgs = whereArgs;
        mParam.crud = DatabaseParam.CRUD.FETCH;
        return this;
    }

    /**
     * Query builder.
     *
     * @param table     the table
     * @param columns   the columns
     * @param where     the where
     * @param whereArgs the where args
     * @param grpBy     the grp by
     * @param having    the having
     * @param orderBy   the order by
     * @param limit     the limit
     * @return the builder
     */
    public Builder query(String table, String[] columns, String where,
                         String[] whereArgs, String grpBy, String having, String orderBy, String limit) {
        mParam.table = table;
        mParam.columns = columns;
        mParam.where = where;
        mParam.whereArgs = whereArgs;
        mParam.orderBy = orderBy;
        mParam.grpBy = grpBy;
        mParam.having = having;
        mParam.limit = limit;
        mParam.crud = DatabaseParam.CRUD.FETCH;
        return this;
    }

    /**
     * Raw query builder.
     *
     * @param sql       the sql
     * @param whereArgs the where args
     * @return the builder
     */
    public Builder rawQuery(String sql, String[] whereArgs) {
        mParam.sql = sql;
        mParam.whereArgs = whereArgs;
        mParam.crud = DatabaseParam.CRUD.FETCH_RAW;
        return this;
    }

    /**
     * Insert builder.
     *
     * @param table the table
     * @param map   the map
     * @return the builder
     */
    public Builder insert(String table, Map<String, String> map) {
        mParam.table = table;
        mParam.map = map;
        mParam.crud = DatabaseParam.CRUD.INSERT;
        return this;
    }

    /**
     * Insert bulk builder.
     *
     * @param isBulk the is bulk
     * @return the builder
     */
    public Builder insertBulk(boolean isBulk) {
        mParam.isBulk = isBulk;
        mParam.crud = DatabaseParam.CRUD.INSERT;
        return this;
    }

    /**
     * Delete builder.
     *
     * @param table the table
     * @return the builder
     */
    public Builder delete(String table) {
        mParam.table = table;
        mParam.crud = DatabaseParam.CRUD.DELETE;
        return this;
    }

    /**
     * Delete builder.
     *
     * @param table     the table
     * @param where     the where
     * @param whereArgs the where args
     * @return the builder
     */
    public Builder delete(String table, String where, String[] whereArgs) {
        mParam.table = table;
        mParam.where = where;
        mParam.whereArgs = whereArgs;
        mParam.crud = DatabaseParam.CRUD.DELETE;
        return this;
    }

    /**
     * Update builder.
     *
     * @param table the table
     * @param map   the map
     * @return the builder
     */
    public Builder update(String table, Map<String, String> map) {
        mParam.table = table;
        mParam.map = map;
        mParam.crud = DatabaseParam.CRUD.UPDATE;
        return this;
    }

    /**
     * Update builder.
     *
     * @param table     the table
     * @param where     the where
     * @param whereArgs the where args
     * @param map       the map
     * @return the builder
     */
    public Builder update(String table, String where, String[] whereArgs, Map<String, String> map) {
        mParam.table = table;
        mParam.where = where;
        mParam.map = map;
        mParam.whereArgs = whereArgs;
        mParam.crud = DatabaseParam.CRUD.UPDATE;
        return this;
    }

    /**
     * Update bulk builder.
     *
     * @param isBulk the is bulk
     * @return the builder
     */
    public Builder updateBulk(boolean isBulk) {
        mParam.isBulk = isBulk;
        mParam.crud = DatabaseParam.CRUD.UPDATE;
        return this;

    }

    /**
     * Build object.
     *
     * @return the object
     */
    public Object build() {
        Object object;
        object = mParam.crud.execute(mParam);
        return object;
    }

}