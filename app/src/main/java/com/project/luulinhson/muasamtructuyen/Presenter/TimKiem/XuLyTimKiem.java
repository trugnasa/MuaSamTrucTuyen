package com.project.luulinhson.muasamtructuyen.Presenter.TimKiem;

import android.util.Log;

import com.project.luulinhson.muasamtructuyen.Model.Object.SanPham;
import com.project.luulinhson.muasamtructuyen.Model.TimKiem.ModelTimKiem;
import com.project.luulinhson.muasamtructuyen.View.TimKiem.ViewTimKiem;

import java.util.List;

/**
 * Created by Admin on 4/21/2017.
 */

public class XuLyTimKiem implements iXuLyTimKiem{

    ViewTimKiem viewTimKiem;
    ModelTimKiem modelTimKiem;

    public XuLyTimKiem(ViewTimKiem viewTimKiem) {
        this.viewTimKiem = viewTimKiem;
        modelTimKiem = new ModelTimKiem();
    }



    @Override
    public void LayDanhSachTimKiemTheoTenSP(String tensp, int limit) {
        List<SanPham> sanPhamList = modelTimKiem.LayDanhSachSanPhamTheoTenSanPham(tensp,limit);
        Log.d("sanpham", "LayDanhSachTimKiemTheoTenSP: " + sanPhamList.size() + "");
        if(sanPhamList.size() > 0){
            viewTimKiem.TimKiemThanhCong(sanPhamList);
        }else {
            viewTimKiem.TimKiemThatBai();
        }
    }
}
