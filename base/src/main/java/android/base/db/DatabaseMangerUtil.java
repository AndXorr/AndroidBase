package android.base.db;

import android.base.log.Log;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.Map;

/**
 * Created by clickapps on 27/11/15.
 */
public final class DatabaseMangerUtil {
    private static String TAG = "DatabaseMangerUtil";

    public static long insert(DatabaseParam param) {
        String message = "";
        long i;
        try {
            ContentValues cv = new ContentValues();
            for (Map.Entry<String, String> pair : param.map.entrySet()) {
                cv.put(pair.getKey(), pair.getValue());
            }
            if (param.isBulk) {
                param.dWrite.beginTransaction();
            }
            i = param.dWrite.insert(param.table, null, cv);
            if (param.isBulk) {
                param.dWrite.setTransactionSuccessful();
            }
            message = "Insert Successfully";
            Log.d(TAG, message);

        } catch (Exception e) {
            i = 0;
            message = e.getMessage();
            Log.e(TAG, message);
        } finally {
            if (param.isBulk) {
                param.dWrite.endTransaction();
            }
        }
        return i;
    }

    public static long update(DatabaseParam param) {
        String message = "";
        long i;
        try {
            ContentValues cv = new ContentValues();
            for (Map.Entry<String, String> pair : param.map.entrySet()) {
                cv.put(pair.getKey(), pair.getValue());
            }
            if (param.isBulk) {
                param.dWrite.beginTransaction();
            }
            i = param.dWrite.update(param.table, cv, param.where, param.whereArgs);
            if (param.isBulk) {
                param.dWrite.setTransactionSuccessful();
            }
            message = "Update Successfully";
            Log.d(TAG, message);
        } catch (Exception e) {
            i = 0;
            message = e.getMessage();
            Log.e(TAG, message);
        } finally {
            if (param.isBulk) {
                param.dWrite.endTransaction();
            }
        }
        return i;
    }

    public static long delete(DatabaseParam param) {
        String message = "";
        long i;
        try {
            i = param.dWrite.delete(param.table, param.where, param.whereArgs);
            message = "Delete Successfully";
            Log.d(TAG, message);
        } catch (Exception e) {
            i = 0;
            message = e.getMessage();
            Log.e(TAG, message);
        }
        return i;
    }

    public static Cursor query(DatabaseParam param) {
        String message = "";
        try {
            Cursor cursor = param.dRead.query(param.table, param.columns, param.where,
                    param.whereArgs, param.grpBy, param.having, param.orderBy, param.limit);
            message = "Query";
            Log.d(TAG, message);
            return cursor;
        } catch (Exception e) {
            message = e.getMessage();
            Log.e(TAG, message);
            return null;
        }
    }

    public static Cursor rawQuery(DatabaseParam param) {
        String message = "";
        try {
            Cursor cursor = param.dRead.rawQuery(param.sql, param.whereArgs);
            message = "Query";
            Log.d(TAG, message);
            return cursor;
        } catch (Exception e) {
            message = e.getMessage();
            Log.e(TAG, message);
            return null;
        }
    }
}
