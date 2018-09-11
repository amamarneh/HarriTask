package com.amarnehsoft.harritask.network.countriesApi;

import com.amarnehsoft.harritask.model.Country;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CountriesApiService {

    @GET("rest/v1/all")
    Single<List<Country>> getAllCountries();

}
