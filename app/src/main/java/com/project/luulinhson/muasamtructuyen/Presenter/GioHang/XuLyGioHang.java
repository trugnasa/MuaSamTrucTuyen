package com.project.luulinhson.muasamtructuyen.Presenter.GioHang;

import android.content.Context;

import com.project.luulinhson.muasamtructuyen.Model.GioHang.ModelGioHang;
import com.project.luulinhson.muasamtructuyen.Model.Object.SanPham;
import com.project.luulinhson.muasamtructuyen.View.GioHang.ViewGioHang;

import java.util.List;

/**
 * Created by Admin on 4/15/2017.
 */

public class XuLyGioHang implements iXuLyGioHang {

    ModelGioHang modelGioHang;
    ViewGioHang viewGioHang;

    public XuLyGioHang(ViewGioHang viewGioHang) {
        this.viewGioHang = viewGioHang;
        modelGioHang = new ModelGioHang();
    }

    @Override
    public void LayDanhSachGioHang(Context context) {
        modelGioHang.MoKetNoiDatabase(context);
        List<SanPham> sanPhamList = modelGioHang.LayTatCaSanPhamTrongGioHang();
        if(sanPhamList.size() > 0){
            viewGioHang.HienThiDanhSachSanPhamTrongGioHang(sanPhamList);
        }

    }
}
