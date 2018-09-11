package com.amarnehsoft.harritask.network;

import com.amarnehsoft.harritask.di.CountriesApi;
import com.amarnehsoft.harritask.di.WeatherApi;
import com.amarnehsoft.harritask.network.countriesApi.CountriesApiService;
import com.amarnehsoft.harritask.network.weatherApi.WeatherApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        return httpClient.build();
    }

    @Provides
    @Singleton
    @CountriesApi
    public Retrofit provideCountriesRetrofit(OkHttpClient httpClient, @CountriesApi String baseUrl){
        return createRetrofit(httpClient, baseUrl);
    }

    @Provides
    @Singleton
    @WeatherApi
    public Retrofit provideWeatherRetrofit(OkHttpClient httpClient, @WeatherApi String baseUrl){
        return createRetrofit(httpClient, baseUrl);
    }

    private Retrofit createRetrofit(OkHttpClient httpClient, String baseUrl){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    @CountriesApi
    public CountriesApiService provideCountriesApiService(@CountriesApi Retrofit retrofit){
        return retrofit.create(CountriesApiService.class);
    }

    @Provides
    @Singleton
    public WeatherApiService provideWeatherApiService(@WeatherApi Retrofit retrofit){
        return retrofit.create(WeatherApiService.class);
    }


    @Provides
    @CountriesApi
    public String provideCountriesBaseUrl(){
        return NetworkConstants.COUNTRIES_END_POINT;
    }

    @Provides
    @WeatherApi
    public String provideWeatherBaseUrl(){
        return NetworkConstants.WETHER_END_POINT;
    }

}
