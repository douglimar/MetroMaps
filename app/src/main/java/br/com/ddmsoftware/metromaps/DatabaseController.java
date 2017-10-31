package br.com.ddmsoftware.metromaps;

import android.content.ContentValues;
import android.content.Context;
//import android.database.Cursor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by dmoraes on 07/08/2017.
 *
 * DatabaseControler para acesso à base de dados
 */

class DatabaseController {

    private final CreateDatabase database;
    private SQLiteDatabase db;

    public DatabaseController(Context context) {

        database = new CreateDatabase(context);
    }


    public void insertData2(String pMaps_id) {

        SQLiteDatabase db;

        ContentValues contentValues;

        db = database.getWritableDatabase();

        contentValues = new ContentValues();
        contentValues.put(CreateDatabase.MAPS_ID, pMaps_id);

        db.insert(CreateDatabase.MAPS_TABLE,null, contentValues);
    }

    /*
    public String insertData(String pMaps_id) {

        SQLiteDatabase db;

        ContentValues contentValues;

        long resultado;

        db = database.getWritableDatabase();

        contentValues = new ContentValues();
        contentValues.put(CreateDatabase.MAPS_ID, pMaps_id);

        resultado = db.insert(CreateDatabase.MAPS_TABLE,null, contentValues);

        if (resultado==-1)
            return "Erro ao inserir registro";
        else
            return "Registro inserido com sucesso.";

    }
*/
    public void deleteData2() {

        SQLiteDatabase db = database.getWritableDatabase();
        db.execSQL("DELETE FROM " + CreateDatabase.MAPS_TABLE);
        db.close();

    }

    /*
    public String deleteData(String pMaps_id) {

        //ContentValues contentValues;

        long resultado;

        db = database.getWritableDatabase();

        //contentValues = new ContentValues();
        //contentValues.put(CreateDatabase.MAPS_ID, pMaps_id);

        //return db.delete(DATABASE_TABLE, KEY_NAME + "=" + name, null) > 0;

        String table = CreateDatabase.MAPS_TABLE;
        String whereClause = "Maps_id="+pMaps_id;

        resultado = db.delete(table,whereClause, null);

        if (resultado==-1)
            return "Erro ao excluir registro";
        else
            return "Registro excluido com sucesso.";

    } */

    public Cursor loadData() {

        Cursor resultSet;

        String[] fields = {CreateDatabase.MAPS_ID};

        db = database.getReadableDatabase();

        resultSet = db.query(CreateDatabase.MAPS_TABLE, fields, null,null,null,null,null);

        // Faz a Consulta no Banco -- Modo rawQuery -- ANSI-SQL
        //resultSet = db.rawQuery("SELECT * FROM MAPS_TABLE",null);

        if (resultSet!=null) {
            resultSet.moveToLast();
        }

        db.close();
        return resultSet;

    }
}
