package com.project.luulinhson.muasamtructuyen.Presenter.ThanhToan;

import android.content.Context;
import android.util.Log;

import com.project.luulinhson.muasamtructuyen.Model.GioHang.ModelGioHang;
import com.project.luulinhson.muasamtructuyen.Model.Object.HoaDon;
import com.project.luulinhson.muasamtructuyen.Model.Object.SanPham;
import com.project.luulinhson.muasamtructuyen.Model.ThanhToan.ModelThanhToan;
import com.project.luulinhson.muasamtructuyen.View.ThanhToan.ViewThanhToan;

import java.util.List;

/**
 * Created by Admin on 4/19/2017.
 */

public class XuLyThanhToan implements iXuLyThanhToan {


    ViewThanhToan viewThanhToan;
    ModelThanhToan modelThanhToan;
    ModelGioHang modelGioHang;
    List<SanPham> sanPhamList;

    public XuLyThanhToan(ViewThanhToan viewThanhToan,Context context) {
        this.viewThanhToan = viewThanhToan;
        modelThanhToan = new ModelThanhToan();
        modelGioHang = new ModelGioHang();
        modelGioHang.MoKetNoiDatabase(context);
    }


    @Override
    public void ThemHoaDon(HoaDon hoaDon) {
        boolean kiemtra = modelThanhToan.ThemHoaDon(hoaDon);
        if(kiemtra){
            viewThanhToan.ThanhToanThanhCong();
            int count = sanPhamList.size();
            for (int i = 0; i < count; i++) {
                modelGioHang.XoaSanPhamTheoMaSP(sanPhamList.get(i).getMASP());
            }
        }else {
            viewThanhToan.ThanhToanThatBai();
        }

    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang() {
//        modelGioHang.MoKetNoiDatabase(context);
        sanPhamList = modelGioHang.LayTatCaSanPhamTrongGioHang();
        viewThanhToan.LayDanhSachSanPhamTrongGioHang(sanPhamList);
    }
}
