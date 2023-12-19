package vdac.prethi.app.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import vdac.prethi.app.models.Mft;

public interface CountryService {
    String BASE_URL = "https://wft-geo-db.p.rapidapi.com/v1/geo/";

    @GET("countries")
    @Headers({
            "X-RapidAPI-Key: 6c7254159cmsh689178bd085f8bdp1ff009jsn13eb00d36617",
            "X-RapidAPI-Host: wft-geo-db.p.rapidapi.com"
    })
    Call<Mft> getCountries();
}
