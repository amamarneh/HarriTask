package com.amarnehsoft.harritask.ui.country.countryActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.amarnehsoft.harritask.model.Weather;
import com.amarnehsoft.harritask.ui.country.countryWeatherFragment.CountryWeatherFragment;

import java.util.List;

public class WeatherPagerAdapter extends FragmentPagerAdapter {

    private CountryWeatherFragment[] weatherFragments;

    public WeatherPagerAdapter(FragmentManager fm) {
        super(fm);
        weatherFragments = new CountryWeatherFragment[getCount()];
    }

    @Override
    public Fragment getItem(int position) {
        if (weatherFragments[position] == null){
            weatherFragments[position] = CountryWeatherFragment.newInstance();
        }
        return weatherFragments[position];
    }

    @Override
    public int getCount() {
        return 2;
    }

    public void updateData(List<Weather> weathers){
        weatherFragments[0].showWeather(weathers.get(0));
        weatherFragments[1].showWeather(weathers.get(1));
    }
}