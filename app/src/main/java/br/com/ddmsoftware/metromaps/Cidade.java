package br.com.ddmsoftware.metromaps;

/**
 * Created by dmoraes on 06/10/2017.
 */

public class Cidade {

    String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Cidade(String cityName) {
        this.cityName = cityName;
    }
}
