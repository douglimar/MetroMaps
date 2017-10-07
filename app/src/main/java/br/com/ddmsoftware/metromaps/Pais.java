package br.com.ddmsoftware.metromaps;

import java.util.List;

/**
 * Created by dmoraes on 06/10/2017.
 */

public class Pais {

    String countryName;
    List<Cidade> cidades;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }


}
