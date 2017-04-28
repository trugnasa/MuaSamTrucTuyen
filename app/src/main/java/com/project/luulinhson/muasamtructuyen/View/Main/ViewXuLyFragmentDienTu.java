package com.project.luulinhson.muasamtructuyen.View.Main;

import com.project.luulinhson.muasamtructuyen.Model.Object.DienTu;
import com.project.luulinhson.muasamtructuyen.Model.Object.SanPham;
import com.project.luulinhson.muasamtructuyen.Model.Object.ThuongHieu;

import java.util.List;

/**
 * Created by Admin on 3/28/2017.
 */

public interface ViewXuLyFragmentDienTu {
    void HienThiDanhSachDienTu(List<DienTu> dienTuList);
    void HienThiDanhSachLogoThuongHieu(List<ThuongHieu> thuongHieuList);
    void HienThiDanhSachSanPhamMoi(List<SanPham> sanPhamList);
}
