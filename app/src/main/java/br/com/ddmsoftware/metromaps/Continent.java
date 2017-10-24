package br.com.ddmsoftware.metromaps;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmoraes on 06/10/2017.
 * Continent Model
 */

class Continent {

    /*
    private String continentName;
    private List<Pais> paises;

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public void setPaises(List<Pais> paises) {
        this.paises = paises;
    }
    */


    public List<String> getAllContinents(Context context){

        List<String> myContinentList = new ArrayList<>();

        myContinentList.add(context.getString(R.string.Africa_button));
        myContinentList.add(context.getString(R.string.America_button));
        myContinentList.add(context.getString(R.string.Asia_button));
        myContinentList.add(context.getString(R.string.Europe_button));
        myContinentList.add(context.getString(R.string.Oceania_button));

        return  myContinentList;

    }

}
