package com.amarnehsoft.harritask.network;

public class NetworkConstants {
    public static final String COUNTRIES_END_POINT = "https://restcountries.eu/";
    public static final String WETHER_END_POINT = "http://api.openweathermap.org/";
    public static final String OPEN_WEATHER_KEY = "1867722b6af87e1d0388e10c5a94be34";

    public static String getFlagUrl(String countryCode){
        return "http://www.geognos.com/api/en/countries/flag/" + countryCode + ".png";
    }
}
