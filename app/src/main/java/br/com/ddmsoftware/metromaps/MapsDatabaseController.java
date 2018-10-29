package br.com.ddmsoftware.metromaps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

class MapsDatabaseController {

    private SQLiteDatabase sqLiteDatabase;

    public MapsDatabaseController(Context context, Boolean READ_ONLY) {

        MapsDBHelper mapsDBHelper = new MapsDBHelper(context);

        if(READ_ONLY) {
            sqLiteDatabase = mapsDBHelper.getReadableDatabase();
        }
        else{
            sqLiteDatabase = mapsDBHelper.getWritableDatabase();

        }


    }

    public void insertData(String mMapsId, String mMapsName) {

        ContentValues cv = new ContentValues();

        //sqLiteDatabase = mapsDBHelper.getWritableDatabase();

        cv.put(MapsContract.MapsEntry.COLUMN_MAPS_ID, mMapsId);
        cv.put(MapsContract.MapsEntry.COLUMN_MAPS_NAME, mMapsName);

        long resultado = sqLiteDatabase.insert(MapsContract.MapsEntry.TABLE_NAME, null, cv);


        if (resultado==-1) {
            System.out.println( "***************************** Erro ao inserir registro *********************");
        }
        else {
            System.out.println( "################## REGISTRO INSERIDO COM SUCESSO ############################");
        }
    }

    public Cursor getAllItems(){

        return sqLiteDatabase.query(MapsContract.MapsEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                MapsContract.MapsEntry.COLUMN_MAPS_NAME);
    }

    public void delete(long id) {
        sqLiteDatabase.delete(MapsContract.MapsEntry.TABLE_NAME,
                MapsContract.MapsEntry._ID+ " = " + id,
                null);
    }
}
