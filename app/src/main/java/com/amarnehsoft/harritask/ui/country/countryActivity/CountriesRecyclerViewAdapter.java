package com.amarnehsoft.harritask.ui.country.countryActivity;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.amarnehsoft.harritask.R;
import com.amarnehsoft.harritask.databinding.ItemCountryBinding;
import com.amarnehsoft.harritask.model.Country;

import java.util.List;


public class CountriesRecyclerViewAdapter extends RecyclerView.Adapter<CountryRecyclerViewHolder> {
    private List<Country> list;
    private LayoutInflater layoutInflater;
    private Listener listener;

    CountriesRecyclerViewAdapter(List<Country> list, LayoutInflater inflater, Listener listener) {
        this.list = list;
        this.layoutInflater = inflater;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CountryRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCountryBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_country, parent, false);
        return new CountryRecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryRecyclerViewHolder holder, int position) {
        holder.binding.getRoot().setOnClickListener(view -> {
            listener.onCountryClicked(list.get(position));
        });
        holder.binding.setBean(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    void updatelist(List<Country> countries) {
        this.list = countries;
        notifyDataSetChanged();
    }

    interface Listener{
        void onCountryClicked(Country country);
    }
}
