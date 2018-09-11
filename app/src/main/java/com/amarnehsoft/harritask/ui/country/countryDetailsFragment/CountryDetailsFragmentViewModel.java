package com.amarnehsoft.harritask.ui.country.countryDetailsFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.amarnehsoft.harritask.model.Country;

import javax.inject.Inject;

public class CountryDetailsFragmentViewModel extends ViewModel {

    //for dataBinding
    public MutableLiveData<String> txtName = new MutableLiveData<>();
    public MutableLiveData<String> txtRegion = new MutableLiveData<>();
    public MutableLiveData<String> txtCapital = new MutableLiveData<>();
    public MutableLiveData<Long> txtPopulation = new MutableLiveData<>();
    public MutableLiveData<String> countryFlag = new MutableLiveData<>();

    @Inject
    public CountryDetailsFragmentViewModel(){

    }

    public void loadCountry(Country country) {
        txtName.postValue(country.getName());
        txtCapital.postValue(country.getCapital());
        txtRegion.postValue(country.getRegion());
        txtPopulation.postValue(country.getPopulation());
        countryFlag.postValue(country.getAlpha2Code());
    }
}
