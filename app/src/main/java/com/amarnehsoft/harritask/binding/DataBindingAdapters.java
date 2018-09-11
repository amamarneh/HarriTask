package com.amarnehsoft.harritask.binding;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.amarnehsoft.harritask.network.NetworkConstants;
import com.squareup.picasso.Picasso;

public class DataBindingAdapters {

    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, String imageUrl) {
        if (TextUtils.isEmpty(imageUrl)) {
            view.setImageURI(null);
        } else {
            Picasso.get().load(imageUrl).into(view);
        }
    }

    @BindingAdapter("android:flag")
    public static void setFlag(ImageView view, String countryCode) {
        if (TextUtils.isEmpty(countryCode)) {
            view.setImageURI(null);
        } else {
            Picasso.get().load(NetworkConstants.getFlagUrl(countryCode)).into(view);
        }
    }

    @BindingAdapter("android:visible")
    public static void setVisible(View view, boolean visible){
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}