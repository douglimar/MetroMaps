package br.com.ddmsoftware.metromaps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmoraes on 06/10/2017.
 *
 * Pais Model
 */

class Pais {

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

    public List<String> getAllCountries(String continent) {

        List<String> list = new ArrayList<>();

        switch (continent) {

            case "América do Sul": {

                list.add("Argentina");
                list.add("Brasil");
                list.add("Chile");
                list.add("Colômbia");
                list.add("Peru");
                list.add("Venezuela");
                break;
            }

            case "América": {

                list.add("América do Norte");
                list.add("América Central");
                list.add("América do Sul");
                break;
            }
        }

        return list;
    }
}
