package br.com.ddmsoftware.metromaps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Douglimar Moraes on 06/10/2017.
 * Class of Cidades
 */

class Cidade {

    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Cidade(String cityName) {
        this.cityName = cityName;
    }

    Cidade(){

    }


    public List<String> getAllCities(String pCountryName) {

        List<String> myList = new ArrayList<>();

        switch (pCountryName) {

            case "Brasil":

                myList.add("Brasília");
                myList.add("Belo Horizonte");
                myList.add("Fortaleza");
                myList.add("João Pessoa");
                myList.add("Maceió");
                myList.add("Natal");
                myList.add("Porto Alegre");
                myList.add("Recife");
                myList.add("Rio de Janeiro");
                myList.add("Salvador");
                myList.add("São Paulo");
                myList.add("Teresina");
                break;
            default:
                myList.add("No City");
                break;
        }

        return myList;

    }
}
