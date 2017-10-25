package br.com.ddmsoftware.metromaps;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmoraes on 06/10/2017.
 *
 * Pais Model
 */

class Pais {

    /*
    private String countryName;
    private List<Cidade> cidades;

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
*/
    public List<String> getAllCountries(Context context, String continent) {

        String AMERICA = context.getString(R.string.America_button);
        String SOUTH_AMERICA = context.getString(R.string.SouthAmerica_List);
        String NORTH_AMERICA = context.getString(R.string.NorthAmerica_List);
        String CENTRAL_AMERICA = context.getString(R.string.CentralAmerica_List);
        String AFRICA = context.getString(R.string.Africa_button);
        String ASIA = context.getString(R.string.Asia_button);
        String EUROPE = context.getString(R.string.Europe_button);
        String OCEANIA = context.getString(R.string.Oceania_button);

        List<String> list = new ArrayList<>();

        if (continent.equals(AMERICA)) {

            list.add(context.getString(R.string.NorthAmerica_List));
            list.add(context.getString(R.string.CentralAmerica_List));
            list.add(context.getString(R.string.SouthAmerica_List));

        } else
            if (continent.equals(SOUTH_AMERICA)) {

                list.add(context.getString(R.string.argentina));
                list.add(context.getString(R.string.brazil));
                list.add(context.getString(R.string.chile));
                list.add(context.getString(R.string.colombia));
                list.add(context.getString(R.string.peru));
                list.add(context.getString(R.string.venezuela));

        } else
            if (continent.equals(NORTH_AMERICA)) {

                list.add(context.getString(R.string.canada));
                list.add(context.getString(R.string.estados_unidos));
                list.add(context.getString(R.string.mexico));

            } else
                if (continent.equals(CENTRAL_AMERICA)) {

                    list.add(context.getString(R.string.panama));
                    list.add(context.getString(R.string.porto_rico));
                    list.add(context.getString(R.string.republica_dominicana));

                } else
                    if (continent.equals(AFRICA)) {

                        list.add(context.getString(R.string.africa_do_sul));
                        list.add(context.getString(R.string.egito));
                        list.add(context.getString(R.string.tunisia));

                    } else
                        if (continent.equals(ASIA)) {

                            list.add(context.getString(R.string.emirados_arabes));
                            list.add(context.getString(R.string.india));
                    } else
                        if (continent.equals(EUROPE)) {

                            list.add(context.getString(R.string.alemanha));
                            list.add(context.getString(R.string.austria));
                            list.add(context.getString(R.string.dinamarca));
                            list.add(context.getString(R.string.espanha));
                            list.add(context.getString(R.string.finlandia));
                            list.add(context.getString(R.string.franca));
                            list.add(context.getString(R.string.holanda));
                            list.add(context.getString(R.string.inglaterra));
                            list.add(context.getString(R.string.italia));
                            list.add(context.getString(R.string.portugal));
                            list.add(context.getString(R.string.suecia));
                            list.add(context.getString(R.string.suica));
                            list.add(context.getString(R.string.russia));
                            list.add(context.getString(R.string.ukrania));

                        } else
                        if (continent.equals(OCEANIA)) {

                            list.add(context.getString(R.string.australia));

                        }
        return list;
    }
}
