package vdac.prethi.app.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import vdac.prethi.app.models.Geo;

public interface WeatherService {
    String BASE_URL = "https://weatherapi-com.p.rapidapi.com/";

    @GET("current.json")
    @Headers({
            "X-RapidAPI-Key: 6c7254159cmsh689178bd085f8bdp1ff009jsn13eb00d36617",
            "X-RapidAPI-Host: weatherapi-com.p.rapidapi.com"
    })
    Call<Geo> getGeo(@Query("q") String location);
}
