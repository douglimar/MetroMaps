package br.com.ddmsoftware.metromaps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmoraes on 06/10/2017.
 * Continent Model
 */

class Continent {

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


    public List<String> getAllContinents(){

        List<String> myContinentList = new ArrayList<>();

        myContinentList.add("Ásia");
        myContinentList.add("América");
        myContinentList.add("Europa");
        myContinentList.add("África");
        myContinentList.add("Oceania");

        return  myContinentList;

    }

}
