package com.project.luulinhson.muasamtructuyen.View.Main.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.luulinhson.muasamtructuyen.Adapter.AdapterRecyclerviewNoiBat;
import com.project.luulinhson.muasamtructuyen.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2/28/2017.
 */

public class FragmentNoiBat extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noi_bat,container,false);

        return view;
    }
}
