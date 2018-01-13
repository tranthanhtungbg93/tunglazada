package com.example.truanggg.lazada.Presenter.ChiTietSanPham;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.truanggg.lazada.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Truang on 12/27/2017.
 */

public class FragmentSliderChiTietSanPham extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_slider_chitietsanpham,container,false);
         Bundle bundle = getArguments();
         String linkHinh = bundle.getString("linkHinh");

        ImageView imageView = view.findViewById(R.id.imHinhSlider);

        Picasso.with(getContext()).load(linkHinh).into(imageView);

        return view;
    }
}
