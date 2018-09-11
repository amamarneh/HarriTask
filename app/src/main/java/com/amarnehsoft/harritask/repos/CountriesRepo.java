package com.amarnehsoft.harritask.repos;

import com.amarnehsoft.harritask.di.CountriesApi;
import com.amarnehsoft.harritask.model.Country;
import com.amarnehsoft.harritask.network.countriesApi.CountriesApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class CountriesRepo {

    private CountriesApiService countriesCountriesApiService;

    @Inject
    CountriesRepo(@CountriesApi CountriesApiService countriesApiService){
        this.countriesCountriesApiService = countriesApiService;
    }

    public Single<List<Country>> getAllCountries(){
        return countriesCountriesApiService.getAllCountries();
    }
}
