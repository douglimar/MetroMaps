package br.com.ddmsoftware.metromaps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class MapsDatabaseController {

    private final MapsDBHelper mapsDBHelper;
    private SQLiteDatabase sqLiteDatabase;

    public MapsDatabaseController(Context context, Boolean READ_ONLY) {

        mapsDBHelper = new MapsDBHelper(context);

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


    public Cursor loadData() {

        Cursor resultSet;

        String[] fields = {CreateDatabase.MAPS_ID, CreateDatabase.MAPS_NAME};

        sqLiteDatabase = mapsDBHelper.getReadableDatabase();

        resultSet = sqLiteDatabase.query(CreateDatabase.MAPS_TABLE, fields, null,null,null,null,null);

        // Faz a Consulta no Banco -- Modo rawQuery -- ANSI-SQL
        //resultSet = db.rawQuery("SELECT * FROM MAPS_TABLE",null);

        if (resultSet!=null) {
            resultSet.moveToFirst();
        }

        sqLiteDatabase.close();
        return resultSet;

    }
}
