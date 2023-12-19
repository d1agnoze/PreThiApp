package vdac.prethi.app.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroContri {
    private static RetroContri instance = null;
    private CountryService wftapi;

    private RetroContri() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(CountryService.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        wftapi = retrofit.create(CountryService.class);
    }

    public static synchronized RetroContri getInstance() {
        if (instance == null) {
            instance = new RetroContri();
        }
        return instance;
    }

    public CountryService getWft() {
        return wftapi;
    }
}