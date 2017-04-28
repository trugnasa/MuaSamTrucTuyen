package com.project.luulinhson.muasamtructuyen.Presenter.DetailProduct;

import android.content.Context;

import com.project.luulinhson.muasamtructuyen.Model.Object.SanPham;

/**
 * Created by Admin on 4/10/2017.
 */

public interface iXuLyChiTietSanPham {
    void LayChiTietSanPham(int masp);
    void LayDanhSachDanhGia(int masp,int limit);
    void ThemGioHang(Context context,SanPham sanPham);
}
