package com.amarnehsoft.harritask.ui.country.countryDetailsFragment;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amarnehsoft.harritask.R;
import com.amarnehsoft.harritask.databinding.FragmentCountryDetailsBinding;
import com.amarnehsoft.harritask.model.Country;
import com.amarnehsoft.harritask.network.NetworkConstants;
import com.amarnehsoft.harritask.viewModel.ViewModelFactory;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;
import timber.log.Timber;


public class CountryDetailsFragment extends Fragment {

    private CountryDetailsFragmentViewModel viewModel;
    @Inject
    ViewModelFactory factory;

    public CountryDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        Timber.d("fragment created");

        viewModel = ViewModelProviders.of(this, factory).get(CountryDetailsFragmentViewModel.class);
    }

    public void showCountry(Country country){
        viewModel.loadCountry(country);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentCountryDetailsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_country_details, container, false);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }
}
