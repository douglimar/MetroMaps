package br.com.ddmsoftware.metromaps;

import java.util.List;

/**
 * Created by dmoraes on 06/10/2017.
 */

public class Continent {

    String continentName;
    List<Pais> paises;

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

}
