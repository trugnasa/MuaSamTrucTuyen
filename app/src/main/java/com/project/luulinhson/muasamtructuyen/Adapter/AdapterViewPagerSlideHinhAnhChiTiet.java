package com.project.luulinhson.muasamtructuyen.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Admin on 4/10/2017.
 */

public class AdapterViewPagerSlideHinhAnhChiTiet extends FragmentStatePagerAdapter {

    List<Fragment> fragmentList;
    public AdapterViewPagerSlideHinhAnhChiTiet(FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
