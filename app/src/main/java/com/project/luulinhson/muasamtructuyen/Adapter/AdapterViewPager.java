package com.project.luulinhson.muasamtructuyen.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.project.luulinhson.muasamtructuyen.View.Main.Fragment.FragmentChuongTrinhKhuyenMai;
import com.project.luulinhson.muasamtructuyen.View.Main.Fragment.FragmentDienTu;
import com.project.luulinhson.muasamtructuyen.View.Main.Fragment.FragmentLamDep;
import com.project.luulinhson.muasamtructuyen.View.Main.Fragment.FragmentMeVaBe;
import com.project.luulinhson.muasamtructuyen.View.Main.Fragment.FragmentNhaCuaVaDoiSong;
import com.project.luulinhson.muasamtructuyen.View.Main.Fragment.FragmentNoiBat;
import com.project.luulinhson.muasamtructuyen.View.Main.Fragment.FragmentTheThaoVaDuLich;
import com.project.luulinhson.muasamtructuyen.View.Main.Fragment.FragmentThoiTrang;
import com.project.luulinhson.muasamtructuyen.View.Main.Fragment.FragmentThuongHieu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2/28/2017.
 */

public class AdapterViewPager extends FragmentPagerAdapter {

    List<Fragment> fragmentList = new ArrayList<Fragment>();
    List<String> tenFragmentList = new ArrayList<String>();

    public AdapterViewPager(FragmentManager fm) {
        super(fm);

        fragmentList.add(new FragmentNoiBat());
        fragmentList.add(new FragmentChuongTrinhKhuyenMai());
        fragmentList.add(new FragmentDienTu());
        fragmentList.add(new FragmentLamDep());
        fragmentList.add(new FragmentMeVaBe());
        fragmentList.add(new FragmentNhaCuaVaDoiSong());
        fragmentList.add(new FragmentTheThaoVaDuLich());
        fragmentList.add(new FragmentThoiTrang());
        fragmentList.add(new FragmentThuongHieu());

        tenFragmentList.add("Nổi bật");
        tenFragmentList.add("Chương trình khuyến mãi");
        tenFragmentList.add("Điện tử");
        tenFragmentList.add("Làm đẹp");
        tenFragmentList.add("Mẹ và bé");
        tenFragmentList.add("Nhà cửa & đời sống");
        tenFragmentList.add("Thể thao & du lịch");
        tenFragmentList.add("Thời trang");
        tenFragmentList.add("Thương hiệu");
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
