package com.project.luulinhson.muasamtructuyen.View.DetailProduct;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.project.luulinhson.muasamtructuyen.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Admin on 4/10/2017.
 */

public class FragmentSlide extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slide_hinh_anh_san_pham,container,false);

        Bundle bundle = getArguments();
        String linkhinh = bundle.getString("linkhinh");

        ImageView imSlide = (ImageView) view.findViewById(R.id.imSlide);

        Picasso.with(getContext()).load(linkhinh).into(imSlide);

        return view;
    }
}
