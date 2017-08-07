package br.com.ddmsoftware.metromaps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmoraes on 07/08/2017.
 */

public class DatabaseController {

    private CreateDatabase database;
    private SQLiteDatabase db;

    public DatabaseController(Context context) {

        database = new CreateDatabase(context);
    }


    public String insertData(String pMaps_id) {

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

    public void deleteData2() {

        SQLiteDatabase db = database.getWritableDatabase();
        db.execSQL("DELETE FROM " + CreateDatabase.MAPS_TABLE);
        db.close();

    }

    public String deleteData(String pMaps_id) {

        ContentValues contentValues;

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

    }

    public Cursor loadData() {

        Cursor resultSet;

        String[] fields = {database.MAPS_ID};

        db = database.getReadableDatabase();

        resultSet = db.query(database.MAPS_TABLE, fields, null,null,null,null,null);

        // Faz a Consulta no Banco -- Modo rawQery -- ANSI-SQL
        //resultSet = db.rawQuery("SELECT * FROM PET_TABLE",null);

        if (resultSet!=null) {
            resultSet.moveToLast();
        }

        db.close();
        return resultSet;

    }

    public List<MapsModel> loadPetDataInList() {

        List<MapsModel> modelList = new ArrayList<MapsModel>();
        Cursor resultSet;

        byte[] fotoArray;

        // Define os campos que serão retornados na consulta
        String[] fields = {database.MAPS_ID};

        // Abre o Banco de Dados no formato Read-Only
        db = database.getReadableDatabase();

        // Faz a Consulta no Banco -- Modo Query
        resultSet = db.query(database.MAPS_TABLE, fields, null,null, null, null, null);


        // Faz a Consulta no Banco -- Modo rawQery -- ANSI-SQL
        //resultSet = db.rawQuery("SELECT * FROM PET_TABLE",null);
        int iCount = resultSet.getCount();
        Log.e("TESTE DE LOG:", String.valueOf(iCount));


        if (resultSet!=null) {
            resultSet.moveToFirst();

            do {

                Log.e("Teste...:::::", resultSet.getString(0));

                MapsModel model = new MapsModel();
                model.setMaps_id(resultSet.getString(0));

                modelList.add(model);
            }while (resultSet.moveToNext());

        }

        db.close();
        return modelList;
    }


}
