package com.project.luulinhson.muasamtructuyen.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.project.luulinhson.muasamtructuyen.View.LogIn_Register.Fragment.FragmentDangKy;
import com.project.luulinhson.muasamtructuyen.View.LogIn_Register.Fragment.FragmentDangNhap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 3/12/2017.
 */

public class AdapterViewPagerDangNhap extends FragmentPagerAdapter {

    List<Fragment> fragmentList = new ArrayList<>();
    List<String> tenFragmentList = new ArrayList<>();

    public AdapterViewPagerDangNhap(FragmentManager fm) {
        super(fm);
        fragmentList.add(new FragmentDangNhap());
        fragmentList.add(new FragmentDangKy());

        tenFragmentList.add("Đăng nhập");
        tenFragmentList.add("Đăng ký");
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tenFragmentList.get(position);
    }
}
