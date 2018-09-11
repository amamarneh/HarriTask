package com.amarnehsoft.harritask.di;

import com.amarnehsoft.harritask.ui.country.countryActivity.CountryActivity;
import com.amarnehsoft.harritask.ui.country.countryDetailsFragment.CountryDetailsFragment;
import com.amarnehsoft.harritask.ui.country.countryWeatherFragment.CountryWeatherFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivitiesBuilderModule {

    @ContributesAndroidInjector
    public abstract CountryActivity contributeCountryActivity();

    @ContributesAndroidInjector
    public abstract CountryDetailsFragment contributeCountryDetailsFragment();

    @ContributesAndroidInjector
    public abstract CountryWeatherFragment contributeCountryWeatherFragment();
}
