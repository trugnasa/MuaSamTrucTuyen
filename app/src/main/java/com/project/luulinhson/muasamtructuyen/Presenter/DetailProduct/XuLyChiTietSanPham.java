package com.project.luulinhson.muasamtructuyen.Presenter.DetailProduct;

import android.content.Context;
import android.util.Log;

import com.project.luulinhson.muasamtructuyen.Model.DetailProduct.ModelDetailProduct;
import com.project.luulinhson.muasamtructuyen.Model.GioHang.ModelGioHang;
import com.project.luulinhson.muasamtructuyen.Model.Object.DanhGia;
import com.project.luulinhson.muasamtructuyen.Model.Object.SanPham;
import com.project.luulinhson.muasamtructuyen.Model.Object.ThuongHieu;
import com.project.luulinhson.muasamtructuyen.View.DetailProduct.ViewChiTietSanPham;

import java.util.List;

/**
 * Created by Admin on 4/10/2017.
 */

public class XuLyChiTietSanPham implements iXuLyChiTietSanPham {

    ViewChiTietSanPham viewChiTietSanPham;
    ModelDetailProduct modelDetailProduct;
    ModelGioHang modelGioHang;

    public XuLyChiTietSanPham(){
        modelGioHang = new ModelGioHang();
    }

    public XuLyChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham) {
        this.viewChiTietSanPham = viewChiTietSanPham;
        modelDetailProduct = new ModelDetailProduct();
        modelGioHang = new ModelGioHang();
    }


    @Override
    public void LayChiTietSanPham(int masp) {
        SanPham sanPham;

        sanPham = modelDetailProduct.LayChiTietSanPhamTheoMaSP(masp);
        if(sanPham.getMASP() > 0){
            String[] linkHinhAnh = sanPham.getANHNHO().split(",");

            viewChiTietSanPham.HienThiSlideHinhAnh(linkHinhAnh);
            viewChiTietSanPham.HienThiChiTietSanPham(sanPham);
        }

    }

    @Override
    public void LayDanhSachDanhGia(int masp, int limit) {
        List<DanhGia> danhGiaList = modelDetailProduct.LayDanhSachDanhGiaTheoMaSP(masp,limit);
        if(danhGiaList.size() > 0){
            viewChiTietSanPham.HienThiDanhSachDanhGia(danhGiaList);
        }
    }

    @Override
    public void ThemGioHang(Context context,SanPham sanPham) {
        modelGioHang.MoKetNoiDatabase(context);
        boolean kiemtra = modelGioHang.ThemGioHang(sanPham);
        if(kiemtra){
            viewChiTietSanPham.ThemGioHangThanhCong();
        }else {
            viewChiTietSanPham.ThemGioHangThatBai();
        }
    }

    public int DemSoLuongSanPhamTrongGioHang(Context context){
        modelGioHang.MoKetNoiDatabase(context);
        List<SanPham> sanPhamList = modelGioHang.LayTatCaSanPhamTrongGioHang();
        int dem = sanPhamList.size();

        return dem;
    }

    public String LayThuongHieu(int mathuonghieu){
        ThuongHieu thuongHieu = modelDetailProduct.LayThuongHieuTheoMaThuongHieu(mathuonghieu);
        String tenthuonghieu = thuongHieu.getTENTHUONGHIEU();

        return tenthuonghieu;
    }
}
