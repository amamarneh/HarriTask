package com.amarnehsoft.harritask.ui.country.countryWeatherFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.amarnehsoft.harritask.model.Weather;

import javax.inject.Inject;

public class CountryWeatherFragmentViewModel extends ViewModel {
    //for dataBinding
    public MutableLiveData<Long> date = new MutableLiveData<>();
    public MutableLiveData<Float> maxTemp = new MutableLiveData<>();
    public MutableLiveData<Float> minTemp = new MutableLiveData<>();
    public MutableLiveData<Float> pressure = new MutableLiveData<>();
    public MutableLiveData<Integer> humidity = new MutableLiveData<>();


    @Inject
    public CountryWeatherFragmentViewModel(){

    }

    public void loadWeather(Weather weather) {
        date.postValue(weather.getDate());
        maxTemp.postValue(weather.getMaxTemp());
        minTemp.postValue(weather.getMinTemp());
        pressure.postValue(weather.getPressure());
        humidity.postValue(weather.getHumidity());
    }
}
