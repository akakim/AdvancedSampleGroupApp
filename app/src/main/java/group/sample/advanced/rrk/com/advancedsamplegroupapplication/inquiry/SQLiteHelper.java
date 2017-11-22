package group.sample.advanced.rrk.com.advancedsamplegroupapplication.inquiry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-11-16
 * @since 0.0.1
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private String lastTableName;

    SQLiteHelper(Context context, String databaseName, int version) {
        super(context, databaseName == null || databaseName.equals(":memory") ? null : databaseName, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if( lastTableName == null){
            return;
        }


//        try{
//            String column = "";
//        }

    }


    public final Cursor query(String tableName, String[] projection, String selection,
                              String[] selectionArgs, String sortOrder) {
        return getReadableDatabase().query(tableName, projection, selection, selectionArgs, null, null, sortOrder);
    }

    public final long insert(String tableName, ContentValues values) {
        return getWritableDatabase().insert(tableName, null, values);
    }

    public final int delete(String tableName, String selection, String[] selectionArgs) {
        if (selection == null) selection = "1";
        return getWritableDatabase().delete(tableName, selection, selectionArgs);
    }

    public final int update(String tableName, ContentValues values, String selection, String[] selectionArgs) {
        return getWritableDatabase().update(tableName, values, selection, selectionArgs);
    }
}
