package com.project.luulinhson.muasamtructuyen.View.ThanhToan;

import com.project.luulinhson.muasamtructuyen.Model.Object.SanPham;

import java.util.List;

/**
 * Created by Admin on 4/19/2017.
 */

public interface ViewThanhToan {
    void ThanhToanThanhCong();
    void ThanhToanThatBai();
    void LayDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList);
}
