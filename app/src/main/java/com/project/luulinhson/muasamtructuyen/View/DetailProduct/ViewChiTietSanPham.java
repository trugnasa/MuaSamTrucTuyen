package com.project.luulinhson.muasamtructuyen.View.DetailProduct;

import com.project.luulinhson.muasamtructuyen.Model.Object.DanhGia;
import com.project.luulinhson.muasamtructuyen.Model.Object.SanPham;

import java.util.List;

/**
 * Created by Admin on 4/10/2017.
 */

public interface ViewChiTietSanPham {
    void HienThiChiTietSanPham(SanPham sanPham);
    void HienThiSlideHinhAnh(String[] linkHinhAnh);
    void HienThiDanhSachDanhGia(List<DanhGia> danhGiaList);
    void ThemGioHangThanhCong();
    void ThemGioHangThatBai();
}
