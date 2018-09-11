package com.amarnehsoft.harritask.repos;

import com.amarnehsoft.harritask.model.Weather;
import com.amarnehsoft.harritask.network.NetworkConstants;
import com.amarnehsoft.harritask.network.weatherApi.WeatherApiService;
import com.amarnehsoft.harritask.network.weatherApi.response.Main;
import com.amarnehsoft.harritask.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class WeatherRepo {

    private WeatherApiService apiService;

    @Inject
    WeatherRepo(WeatherApiService apiService){
        this.apiService = apiService;
    }

    public Single<List<Weather>> getWeatherForecast(float lat, float lon){
        //returns list of two weathers, for today and tomorrow.
        return apiService.getWeatherForecast(lat, lon, NetworkConstants.OPEN_WEATHER_KEY)
                .map(response -> {
                    List<Weather> result = new ArrayList<>();
                    boolean todaySetted =false;
                    if (response != null){
                        for (int i=0; i < response.getList().size(); i++){
                            String dat = response.getList().get(i).getDt_txt();
                            if (DateUtils.isToday(dat) && !todaySetted){
                                Weather weather = getWeatherFromResponse(response.getList().get(i).getMain());
                                weather.setDate(DateUtils.getTimeInMilliSecondsFromStringDate(dat));
                                result.add(weather);
                                todaySetted = true;
                            }else if (DateUtils.isTomorrow(dat)){
                                Weather weather = getWeatherFromResponse(response.getList().get(i).getMain());
                                weather.setDate(DateUtils.getTimeInMilliSecondsFromStringDate(dat));
                                result.add(weather);
                                return result;
                            }
                        }
                    }
                    return result;
                });
    }

    private Weather getWeatherFromResponse(Main main){
        Weather weather = new Weather();
        weather.setMaxTemp(main.getTemp_max());
        weather.setMinTemp(main.getTemp_min());
        weather.setHumidity(main.getHumidity());
        weather.setPressure(main.getPressure());
        return weather;
    }
}
