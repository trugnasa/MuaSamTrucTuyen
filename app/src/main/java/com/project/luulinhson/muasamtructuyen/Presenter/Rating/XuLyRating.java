package com.project.luulinhson.muasamtructuyen.Presenter.Rating;

import android.view.View;
import android.widget.ProgressBar;

import com.project.luulinhson.muasamtructuyen.Model.Object.DanhGia;
import com.project.luulinhson.muasamtructuyen.Model.Rating.ModelRating;
import com.project.luulinhson.muasamtructuyen.View.Rating.ViewRating;

import java.util.List;

/**
 * Created by Admin on 4/12/2017.
 */

public class XuLyRating implements iXuLyRating{

    ViewRating viewRating;
    ModelRating modelRating;

    public XuLyRating(ViewRating viewRating) {
        this.viewRating = viewRating;
        modelRating = new ModelRating();
    }

    @Override
    public void ThemDanhGia(DanhGia danhGia) {
        boolean kiemtra = modelRating.ThemDanhGia(danhGia);
        if (kiemtra){
            viewRating.ThemDanhGiaThanhCong();
        }else {
            viewRating.ThemDanhGiaThatBai();
        }

    }

    @Override
    public void LayDanhSachDanhGia(int masp, int limit, ProgressBar progressBar) {

//        progressBar.setVisibility(View.VISIBLE);

        List<DanhGia> danhGiaList = modelRating.LayDanhSachDanhGiaTheoMaSP(masp,limit);

        if(danhGiaList.size() != 0){
            viewRating.HienThiDanhSachDanhGia(danhGiaList);
//            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
