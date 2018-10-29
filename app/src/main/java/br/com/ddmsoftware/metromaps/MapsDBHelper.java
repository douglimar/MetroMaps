package br.com.ddmsoftware.metromaps;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class MapsDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "maps.db";
    private static final int DATABASE_VERSION = 5;

    public MapsDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = " CREATE TABLE " +
                MapsContract.MapsEntry.TABLE_NAME + "(" +
                MapsContract.MapsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                MapsContract.MapsEntry.COLUMN_MAPS_ID + " INTEGER NOT NULL, "+
                MapsContract.MapsEntry.COLUMN_MAPS_NAME + " TEXT NOT NULL );";

        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MapsContract.MapsEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}
