package com.amarnehsoft.harritask.ui.country.countryActivity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.amarnehsoft.harritask.model.Country;
import com.amarnehsoft.harritask.model.Weather;
import com.amarnehsoft.harritask.repos.CountriesRepo;
import com.amarnehsoft.harritask.repos.WeatherRepo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CountryActivityViewModel extends ViewModel {

    private CountriesRepo countriesRepo;
    private WeatherRepo weatherRepo;
    private MutableLiveData<List<Country>> resultLive = null;
    private MutableLiveData<String> errorLive = new MutableLiveData<>();
    private DisposableSingleObserver<List<Country>> disposableSingleObserver;

    @Inject
    CountryActivityViewModel(CountriesRepo countriesRepo, WeatherRepo weatherRepo){
        this.countriesRepo = countriesRepo;
        this.weatherRepo = weatherRepo;
    }

    public void init() {
        if (resultLive != null){
            return;
        }

        resultLive = new MutableLiveData<>();
        disposableSingleObserver = new DisposableSingleObserver<List<Country>>() {
            @Override
            public void onSuccess(List<Country> countries) {
                resultLive.postValue(countries);
            }

            @Override
            public void onError(Throwable e) {
                errorLive.postValue(e.getMessage());
            }
        };

        countriesRepo.getAllCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableSingleObserver);
    }

    public MutableLiveData<List<Country>> getResultLive() {
        return resultLive;
    }

    public MutableLiveData<String> getErrorLive() {
        return errorLive;
    }

    public LiveData<List<Weather>> getWeatherForTodayAndTomorrow(float lat, float lon){
        MutableLiveData<List<Weather>> result = new MutableLiveData<>();
        weatherRepo.getWeatherForecast(lat, lon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Weather>>() {
                    @Override
                    public void onSuccess(List<Weather> weathers) {
                        result.postValue(weathers);
                    }

                    @Override
                    public void onError(Throwable e) {
                        errorLive.postValue(e.getMessage());
                    }
                });
        return result;
    }

    public void clear(){
        disposableSingleObserver.dispose();
    }
}
