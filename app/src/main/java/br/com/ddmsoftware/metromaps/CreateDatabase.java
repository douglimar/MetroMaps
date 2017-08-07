package br.com.ddmsoftware.metromaps;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



class CreateDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "maps.db";
    public static final Integer DB_VERSION = 1;

    //MAPS TABLE
    public static final String MAPS_TABLE = "Maps";
    public static final String MAPS_ID = "Maps_id";


    public CreateDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql;

        sql =   " CREATE TABLE " + MAPS_TABLE + "(\n" +
                " Maps_id INTEGER NOT NULL " +
                " );";

        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MAPS_TABLE);

        onCreate(sqLiteDatabase);


    }
}
