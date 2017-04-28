package com.project.luulinhson.muasamtructuyen.Presenter.LoadProductPage;

import android.view.View;
import android.widget.ProgressBar;

import com.project.luulinhson.muasamtructuyen.Model.LoadProductPage.ModelLoadProductPage;
import com.project.luulinhson.muasamtructuyen.Model.Object.SanPham;
import com.project.luulinhson.muasamtructuyen.View.LoadProductPage.ViewHienThiDanhSachSanPham;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 4/4/2017.
 */

public class XuLyLoadProductPage implements iXuLyLoadProductPage {

    ViewHienThiDanhSachSanPham viewHienThiDanhSachSanPham;
    ModelLoadProductPage modelLoadProductPage;

    public  XuLyLoadProductPage(ViewHienThiDanhSachSanPham viewHienThiDanhSachSanPham){
        this.viewHienThiDanhSachSanPham = viewHienThiDanhSachSanPham;
        modelLoadProductPage = new ModelLoadProductPage();
    }

    @Override
    public void LayDanhSachSanPhamTheoMa(int mathuonghieu,boolean kiemtra) {

        List<SanPham> sanPhamList = new ArrayList<>();
        if(kiemtra){
            sanPhamList = modelLoadProductPage.LayDanhSachSanPhamTheoMaThuongHieu(mathuonghieu,0);
        }else {
            sanPhamList =  modelLoadProductPage.LayDanhSachSanPhamTheoMaLoaiDanhMuc(mathuonghieu,0);
        }

        if(sanPhamList.size() > 0){
            viewHienThiDanhSachSanPham.HienThiDanhSachSanPham(sanPhamList);
        }else {
            viewHienThiDanhSachSanPham.HienThiLoi();
        }

    }

    public List<SanPham> LayDanhSachSanPhamTheoLoadMore(int mathuonghieu, boolean kiemtra, int limit, ProgressBar progressBar) {

        progressBar.setVisibility(View.VISIBLE);

        List<SanPham> sanPhamList = new ArrayList<>();
        if(kiemtra){
            sanPhamList = modelLoadProductPage.LayDanhSachSanPhamTheoMaThuongHieu(mathuonghieu,limit);
        }else {
            sanPhamList =  modelLoadProductPage.LayDanhSachSanPhamTheoMaLoaiDanhMuc(mathuonghieu,limit);
        }

        if(sanPhamList.size() != 0){
            progressBar.setVisibility(View.GONE);
        }

        return sanPhamList;
    }
}
