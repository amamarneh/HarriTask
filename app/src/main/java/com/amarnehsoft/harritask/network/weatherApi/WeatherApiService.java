package com.amarnehsoft.harritask.network.weatherApi;

import com.amarnehsoft.harritask.network.weatherApi.response.GetWeatherResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {

    @GET("data/2.5/forecast")
    Single<GetWeatherResponse> getWeatherForecast(@Query("lat") float lat, @Query("lon") float lon, @Query("appid") String appId);

}
