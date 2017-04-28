package com.project.luulinhson.muasamtructuyen.View.Main.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.luulinhson.muasamtructuyen.R;

/**
 * Created by Admin on 2/28/2017.
 */

public class FragmentThuongHieu extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thuong_hieu,container,false);

        return view;
    }
}
