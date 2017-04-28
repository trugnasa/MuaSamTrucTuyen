package com.project.luulinhson.muasamtructuyen.View.TimKiem;

import com.project.luulinhson.muasamtructuyen.Model.Object.SanPham;

import java.util.List;

/**
 * Created by Admin on 4/21/2017.
 */

public interface ViewTimKiem {
    void TimKiemThanhCong(List<SanPham> sanPhamList);
    void TimKiemThatBai();
}
