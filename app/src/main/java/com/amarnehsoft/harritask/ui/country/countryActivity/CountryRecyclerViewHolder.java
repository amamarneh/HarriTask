package com.amarnehsoft.harritask.ui.country.countryActivity;

import android.support.v7.widget.RecyclerView;

import com.amarnehsoft.harritask.databinding.ItemCountryBinding;

public class CountryRecyclerViewHolder extends RecyclerView.ViewHolder {
    ItemCountryBinding binding;

    CountryRecyclerViewHolder(ItemCountryBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}