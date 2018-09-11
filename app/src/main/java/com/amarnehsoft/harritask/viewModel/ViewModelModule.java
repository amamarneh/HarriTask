package com.amarnehsoft.harritask.viewModel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.amarnehsoft.harritask.ui.country.countryActivity.CountryActivityViewModel;
import com.amarnehsoft.harritask.ui.country.countryDetailsFragment.CountryDetailsFragmentViewModel;
import com.amarnehsoft.harritask.ui.country.countryWeatherFragment.CountryWeatherFragmentViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory provideViewModelFactory(ViewModelFactory factory);


    @Binds
    @IntoMap
    @ViewModelKey(CountryActivityViewModel.class)
    abstract ViewModel bindCountryActivityViewModel(CountryActivityViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CountryDetailsFragmentViewModel.class)
    abstract ViewModel bindCountryDetailsFragmentViewModel(CountryDetailsFragmentViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CountryWeatherFragmentViewModel.class)
    abstract ViewModel bindCountryWeatherFragmentViewModel(CountryWeatherFragmentViewModel viewModel);
}
