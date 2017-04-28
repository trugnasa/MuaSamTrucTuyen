package com.project.luulinhson.muasamtructuyen.View.Rating;

import com.project.luulinhson.muasamtructuyen.Model.Object.DanhGia;

import java.util.List;

/**
 * Created by Admin on 4/12/2017.
 */

public interface ViewRating {
    void ThemDanhGiaThanhCong();
    void ThemDanhGiaThatBai();
    void HienThiDanhSachDanhGia(List<DanhGia> danhGiaList);

}
