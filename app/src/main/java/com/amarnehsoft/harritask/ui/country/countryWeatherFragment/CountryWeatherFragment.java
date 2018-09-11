package com.amarnehsoft.harritask.ui.country.countryWeatherFragment;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amarnehsoft.harritask.R;
import com.amarnehsoft.harritask.databinding.FragmentCountryWeatherBinding;
import com.amarnehsoft.harritask.model.Weather;
import com.amarnehsoft.harritask.network.weatherApi.response.Data;
import com.amarnehsoft.harritask.utils.DateUtils;
import com.amarnehsoft.harritask.viewModel.ViewModelFactory;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.AndroidSupportInjectionModule;
import timber.log.Timber;

public class CountryWeatherFragment extends Fragment {

    private CountryWeatherFragmentViewModel viewModel;
    @Inject
    ViewModelFactory factory;

    public CountryWeatherFragment() {
        // Required empty public constructor
    }

    public static CountryWeatherFragment newInstance() {
        CountryWeatherFragment fragment = new CountryWeatherFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        Timber.d("fragment created");

        viewModel = ViewModelProviders.of(this, factory).get(CountryWeatherFragmentViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentCountryWeatherBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_country_weather, container, false);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    public void showWeather(Weather weather){
        viewModel.loadWeather(weather);
    }
}
