package android.base.db;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.Map;

/**
 * Created by sahni on 6/6/16.
 */
public class Builder {
    private DatabaseParam param;

    public Builder(@NonNull Context context) {
        param = new DatabaseParam();
        param.context = context;
        defaultDB();
    }

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

    /*************************************************************************************/
    public Builder query(String table, String[] columns, String where,
                         String[] whereArgs) {
        param.table = table;
        param.columns = columns;
        param.where = where;
        param.whereArgs = whereArgs;
        param.crud = DatabaseParam.CRUD.FETCH;
        return this;
    }

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

    public Builder rawQuery(String sql, String[] whereArgs) {
        param.sql = sql;
        param.whereArgs = whereArgs;
        param.crud = DatabaseParam.CRUD.FETCH_RAW;
        return this;
    }

    /*************************************************************************************/
    public Builder insert(String table, Map<String, String> map) {
        param.table = table;
        param.map = map;
        param.crud = DatabaseParam.CRUD.INSERT;
        return this;
    }

    public Builder insertBulk(boolean isBulk) {
        param.isBulk = isBulk;
        param.crud = DatabaseParam.CRUD.INSERT;
        return this;
    }

    /*************************************************************************************/
    public Builder delete(String table) {
        param.table = table;
        param.crud = DatabaseParam.CRUD.DELETE;
        return this;
    }

    public Builder delete(String table, String where, String[] whereArgs) {
        param.table = table;
        param.where = where;
        param.whereArgs = whereArgs;
        param.crud = DatabaseParam.CRUD.DELETE;
        return this;
    }

    /*************************************************************************************/
    public Builder update(String table, Map<String, String> map) {
        param.table = table;
        param.map = map;
        param.crud = DatabaseParam.CRUD.UPDATE;
        return this;
    }

    public Builder update(String table, String where, String[] whereArgs, Map<String, String> map) {
        param.table = table;
        param.where = where;
        param.map = map;
        param.whereArgs = whereArgs;
        param.crud = DatabaseParam.CRUD.UPDATE;
        return this;
    }

    public Builder updateBulk(boolean isBulk) {
        param.isBulk = isBulk;
        param.crud = DatabaseParam.CRUD.UPDATE;
        return this;

    }

    /*************************************************************************************/
    public Object build() {
        Object object;
        object = param.crud.execute(param);
        return object;
    }

}