package com.project.luulinhson.muasamtructuyen.Presenter.Rating;

import android.widget.ProgressBar;

import com.project.luulinhson.muasamtructuyen.Model.Object.DanhGia;

/**
 * Created by Admin on 4/12/2017.
 */

public interface iXuLyRating {
    void ThemDanhGia(DanhGia danhGia);
    void LayDanhSachDanhGia(int masp, int limit, ProgressBar progressBar);


}
