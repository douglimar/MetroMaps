package br.com.ddmsoftware.metromaps;

import android.content.Context;

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


    public List<String> getAllCities(Context context, String pCountryName) {

        List<String> myList = new ArrayList<>();

        if (pCountryName.equals(context.getString(R.string.brazil))) {

            myList.add(context.getString(R.string.brasilia));
            myList.add(context.getString(R.string.belo_horizonte));
            myList.add(context.getString(R.string.fortaleza));
            myList.add(context.getString(R.string.jo_o_pessoa));
            myList.add(context.getString(R.string.maceio));
            myList.add(context.getString(R.string.natal));
            myList.add(context.getString(R.string.porto_alegre));
            myList.add(context.getString(R.string.recife));
            myList.add(context.getString(R.string.rio_de_janeiro));
            myList.add(context.getString(R.string.salvador));
            myList.add(context.getString(R.string.sao_paulo));
            myList.add(context.getString(R.string.teresina));

        } else if (pCountryName.equals(context.getString(R.string.argentina))) {

            myList.add(context.getString(R.string.buenos_aires));

        } else if (pCountryName.equals(context.getString(R.string.chile))) {

            myList.add(context.getString(R.string.santiago));
            myList.add(context.getString(R.string.valparaiso));

        } else if (pCountryName.equals(context.getString(R.string.peru))) {

            myList.add(context.getString(R.string.lima));
        } else if (pCountryName.equals(context.getString(R.string.colombia))) {

            myList.add(context.getString(R.string.medelin));

        } else if (pCountryName.equals(context.getString(R.string.venezuela))) {

            myList.add(context.getString(R.string.caracas));
            myList.add(context.getString(R.string.maracaibo));
            myList.add(context.getString(R.string.valencia));

        } else if (pCountryName.equals(context.getString(R.string.porto_rico))) {

            myList.add(context.getString(R.string.san_juan));

        } else if (pCountryName.equals(context.getString(R.string.republica_dominicana))) {

            myList.add(context.getString(R.string.santo_domingo));

        } else if (pCountryName.equals(context.getString(R.string.panama))) {

            myList.add(context.getString(R.string.panama));

        } else if (pCountryName.equals(context.getString(R.string.canada))) {

            myList.add(context.getString(R.string.calgary));
            myList.add(context.getString(R.string.edmonton));
            myList.add(context.getString(R.string.montreal));
            myList.add(context.getString(R.string.ottawa));
            myList.add(context.getString(R.string.toronto));
            myList.add(context.getString(R.string.vancouver));

        } else if (pCountryName.equals(context.getString(R.string.mexico))) {

            myList.add(context.getString(R.string.cidade_do_mexico));
            myList.add(context.getString(R.string.guadalajara));
            myList.add(context.getString(R.string.monterrey));

        } else if (pCountryName.equals(context.getString(R.string.africa_do_sul))) {

            myList.add(context.getString(R.string.cidade_do_cabo));
            myList.add(context.getString(R.string.durban));
            myList.add(context.getString(R.string.east_london_port_elisabeth));
            myList.add(context.getString(R.string.johannesburg_pretoria));

        } else if (pCountryName.equals(context.getString(R.string.tunisia))) {

            myList.add(context.getString(R.string.tunis));

        } else if (pCountryName.equals(context.getString(R.string.egito))) {

            myList.add(context.getString(R.string.cairo));

        }



            return myList;
    }
}
