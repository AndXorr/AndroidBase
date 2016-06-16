package android.base.db;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.Map;


/**
 * The type Builder.
 */
public class Builder {
    private DatabaseParam param;

    /**
     * Instantiates a new Builder.
     *
     * @param context the context
     */
    public Builder(@NonNull Context context) {
        param = new DatabaseParam();
        param.context = context;
        defaultDB();
    }

    /**
     * Instantiates a new Builder.
     *
     * @param context          the context
     * @param sqLiteOpenHelper the sq lite open helper
     */
    public Builder(@NonNull Context context, @NonNull SQLiteOpenHelper sqLiteOpenHelper) {
        param = new DatabaseParam();
        param.context = context;
        param.sqLiteOpenHelper = sqLiteOpenHelper;
        param.dRead = param.sqLiteOpenHelper.getReadableDatabase();
        param.dWrite = param.sqLiteOpenHelper.getWritableDatabase();
    }

    private void defaultDB() {
        DatabaseLocale db = new DatabaseLocale(param.context);
        param.dRead = db.getReadableDatabase();
        param.dWrite = db.getWritableDatabase();
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
        param.table = table;
        param.columns = columns;
        param.where = where;
        param.whereArgs = whereArgs;
        param.crud = DatabaseParam.CRUD.FETCH;
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
        param.table = table;
        param.columns = columns;
        param.where = where;
        param.whereArgs = whereArgs;
        param.orderBy = orderBy;
        param.grpBy = grpBy;
        param.having = having;
        param.limit = limit;
        param.crud = DatabaseParam.CRUD.FETCH;
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
        param.sql = sql;
        param.whereArgs = whereArgs;
        param.crud = DatabaseParam.CRUD.FETCH_RAW;
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
        param.table = table;
        param.map = map;
        param.crud = DatabaseParam.CRUD.INSERT;
        return this;
    }

    /**
     * Insert bulk builder.
     *
     * @param isBulk the is bulk
     * @return the builder
     */
    public Builder insertBulk(boolean isBulk) {
        param.isBulk = isBulk;
        param.crud = DatabaseParam.CRUD.INSERT;
        return this;
    }

    /**
     * Delete builder.
     *
     * @param table the table
     * @return the builder
     */
    public Builder delete(String table) {
        param.table = table;
        param.crud = DatabaseParam.CRUD.DELETE;
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
        param.table = table;
        param.where = where;
        param.whereArgs = whereArgs;
        param.crud = DatabaseParam.CRUD.DELETE;
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
        param.table = table;
        param.map = map;
        param.crud = DatabaseParam.CRUD.UPDATE;
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
        param.table = table;
        param.where = where;
        param.map = map;
        param.whereArgs = whereArgs;
        param.crud = DatabaseParam.CRUD.UPDATE;
        return this;
    }

    /**
     * Update bulk builder.
     *
     * @param isBulk the is bulk
     * @return the builder
     */
    public Builder updateBulk(boolean isBulk) {
        param.isBulk = isBulk;
        param.crud = DatabaseParam.CRUD.UPDATE;
        return this;

    }

    /**
     * Build object.
     *
     * @return the object
     */
    public Object build() {
        Object object;
        object = param.crud.execute(param);
        return object;
    }

}