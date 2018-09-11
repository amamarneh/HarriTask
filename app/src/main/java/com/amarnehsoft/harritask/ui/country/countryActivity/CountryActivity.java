package com.amarnehsoft.harritask.ui.country.countryActivity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.amarnehsoft.harritask.R;
import com.amarnehsoft.harritask.base.BaseActivity;
import com.amarnehsoft.harritask.model.Country;
import com.amarnehsoft.harritask.ui.country.countryDetailsFragment.CountryDetailsFragment;
import com.amarnehsoft.harritask.viewModel.ViewModelFactory;

import javax.inject.Inject;

public class CountryActivity extends BaseActivity implements CountriesRecyclerViewAdapter.Listener{

    private RecyclerView recyclerView;
    private CountriesRecyclerViewAdapter countriesRecyclerViewAdapter;
    private ViewPager viewPager;
    private WeatherPagerAdapter weatherPagerAdapter;

    private CountryDetailsFragment countryDetailsFragment;

    @Inject
    ViewModelFactory factory;
    private CountryActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_country);
        viewPager = findViewById(R.id.container);
        recyclerView = findViewById(R.id.nav_view).findViewById(R.id.recyclerView);

        countryDetailsFragment = (CountryDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.countryDetailFragment);

        setupActionBar();

        setupTabsAndViewPager();

        initViewModel();
    }

    private void setupActionBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setupTabsAndViewPager(){
        weatherPagerAdapter = new WeatherPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(weatherPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        tabLayout.getTabAt(0).setText(getString(R.string.today));
        tabLayout.getTabAt(1).setText(getString(R.string.tomorrow));
    }

    private void initViewModel(){
        viewModel = ViewModelProviders.of(this, factory).get(CountryActivityViewModel.class);
        viewModel.init();

        viewModel.getResultLive().observe(this, countries -> {
            if (countries != null) {
                if (countriesRecyclerViewAdapter == null) {
                    countriesRecyclerViewAdapter = new CountriesRecyclerViewAdapter(countries, getLayoutInflater(), this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(CountryActivity.this));
                    recyclerView.setAdapter(countriesRecyclerViewAdapter);
                } else {
                    countriesRecyclerViewAdapter.updatelist(countries);
                    countriesRecyclerViewAdapter.notifyDataSetChanged();
                }

                //show details of first country
                if (countries.size() > viewModel.getSelectedPosition()){
                    onCountryClicked(countries.get(viewModel.getSelectedPosition()), viewModel.getSelectedPosition());
                }
            }
        });

        viewModel.getErrorLive().observe(this, s -> {
            Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void onBackPressed() {
        if (!hideDrawer())
            super.onBackPressed();
    }

    private boolean hideDrawer() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void onCountryClicked(Country country, int position) {
        countryDetailsFragment.showCountry(country);
        viewModel.getWeatherForTodayAndTomorrow(country.getLatlng().get(0), country.getLatlng().get(1)).observe(CountryActivity.this, weathers -> {
            if (weathers != null && weathers.size() > 0){
                weatherPagerAdapter.updateData(weathers);
            }
        });
        hideDrawer();

        viewModel.setSelectedPosition(position);
    }
}
