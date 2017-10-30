package br.com.ddmsoftware.metromaps;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Douglimar Moraes on 06/10/2017.
 * Class of Cidades
 */

class Cidade {

    /*private String cityName;

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

    } */


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

            myList.add(context.getString(R.string.cidade_do_panama));

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

        } else if (pCountryName.equals(context.getString(R.string.emirados_arabes))) {

            myList.add(context.getString(R.string.dubai));
            myList.add(context.getString(R.string.abu_dhabi));

        }  else if (pCountryName.equals(context.getString(R.string.india))) {

            myList.add(context.getString(R.string.calcuta));
            myList.add(context.getString(R.string.delhi));
            myList.add(context.getString(R.string.mumbai));

        } else if (pCountryName.equals(context.getString(R.string.inglaterra))) {

            myList.add(context.getString(R.string.londres));
            myList.add(context.getString(R.string.manchester));

        } else if (pCountryName.equals(context.getString(R.string.espanha))) {

            myList.add(context.getString(R.string.barcelona));
            myList.add(context.getString(R.string.madrid));
            myList.add(context.getString(R.string.sevilha));
            myList.add(context.getString(R.string.valencia));

        } else if (pCountryName.equals(context.getString(R.string.portugal))) {

            myList.add(context.getString(R.string.lisboa));

        } else if (pCountryName.equals(context.getString(R.string.holanda))) {

            myList.add(context.getString(R.string.amsterdam));
            myList.add(context.getString(R.string.rotterdam));

        } else if (pCountryName.equals(context.getString(R.string.franca))) {

            myList.add(context.getString(R.string.marselha));
            myList.add(context.getString(R.string.paris));

        } else if (pCountryName.equals(context.getString(R.string.australia))) {

            myList.add(context.getString(R.string.melbourne));
            myList.add(context.getString(R.string.sydney));

        }  else if (pCountryName.equals(context.getString(R.string.suica))) {

            myList.add(context.getString(R.string.zurich));

        }  else if (pCountryName.equals(context.getString(R.string.russia))) {

            myList.add(context.getString(R.string.moscow));

        } else if (pCountryName.equals(context.getString(R.string.ukrania))) {

            myList.add(context.getString(R.string.kiev));

        } else if (pCountryName.equals(context.getString(R.string.alemanha))) {

            myList.add(context.getString(R.string.berlim));

        } else if (pCountryName.equals(context.getString(R.string.austria))) {

            myList.add(context.getString(R.string.viena));
        } else if (pCountryName.equals(context.getString(R.string.dinamarca))) {

            myList.add(context.getString(R.string.copenhague));
        } else if (pCountryName.equals(context.getString(R.string.finlandia))) {

            myList.add(context.getString(R.string.helsinki));
        } else if (pCountryName.equals(context.getString(R.string.italia))) {

            myList.add(context.getString(R.string.milao));
            myList.add(context.getString(R.string.roma));
        } else if (pCountryName.equals(context.getString(R.string.suecia))) {

            myList.add(context.getString(R.string.estocolmo));

        } else if (pCountryName.equals(context.getString(R.string.nova_zelandia))) {

            myList.add(context.getString(R.string.auckland));
        } else if (pCountryName.equals(context.getString(R.string.estados_unidos))){

            myList.add(context.getString(R.string.atlanta));
            myList.add(context.getString(R.string.baltimore));
            myList.add(context.getString(R.string.boston));
            myList.add(context.getString(R.string.chicago));
            myList.add(context.getString(R.string.detroit));
            myList.add(context.getString(R.string.houston));
            myList.add(context.getString(R.string.las_vegas));
            myList.add(context.getString(R.string.los_angeles));
            myList.add(context.getString(R.string.miami));
            myList.add(context.getString(R.string.nova_york));
            myList.add(context.getString(R.string.sao_francisco));
            myList.add(context.getString(R.string.seattle));
            myList.add(context.getString(R.string.washington));

        } else if (pCountryName.equals(context.getString(R.string.china))) {

            myList.add(context.getString(R.string.beijing));
            myList.add(context.getString(R.string.guangzhou));
            myList.add(context.getString(R.string.hongkong));
            myList.add(context.getString(R.string.shanghai));
            myList.add(context.getString(R.string.shenzhen));

        } else if (pCountryName.equals(context.getString(R.string.arabia_saudita))) {

            myList.add(context.getString(R.string.mecca));

        }  else if (pCountryName.equals(context.getString(R.string.japao))) {

            myList.add(context.getString(R.string.nagoya));
            myList.add(context.getString(R.string.tokyo));
            myList.add(context.getString(R.string.yokohama));

        }  else if (pCountryName.equals(context.getString(R.string.turquia))) {

            myList.add(context.getString(R.string.ankara));
            myList.add(context.getString(R.string.istambul));

        }  else if (pCountryName.equals(context.getString(R.string.southkorea))) {

            myList.add(context.getString(R.string.seoul));

        } else if (pCountryName.equals(context.getString(R.string.singapura))) {

            myList.add(context.getString(R.string.singapura));

        } else if (pCountryName.equals(context.getString(R.string.taiwan))) {

            myList.add(context.getString(R.string.taipei));

        }

        return myList;
    }
}
