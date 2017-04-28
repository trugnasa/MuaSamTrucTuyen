package com.project.luulinhson.muasamtructuyen.Presenter.MainActivity.FragmentDienTu;

import com.project.luulinhson.muasamtructuyen.Model.MainActivity.FragmentDienTu.ModelDienTu;
import com.project.luulinhson.muasamtructuyen.Model.Object.DienTu;
import com.project.luulinhson.muasamtructuyen.Model.Object.SanPham;
import com.project.luulinhson.muasamtructuyen.Model.Object.ThuongHieu;
import com.project.luulinhson.muasamtructuyen.View.Main.ViewXuLyFragmentDienTu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 3/28/2017.
 */

public class XuLyFragmentDienTu implements iXuLyFragmentDienTu {

    ViewXuLyFragmentDienTu viewXuLyFragmentDienTu;
    ModelDienTu modelDienTu;

    public XuLyFragmentDienTu(ViewXuLyFragmentDienTu viewXuLyFragmentDienTu){
        this.viewXuLyFragmentDienTu = viewXuLyFragmentDienTu;
        modelDienTu = new ModelDienTu();
    }

    @Override
    public void LayDanhSachDienTu() {
        List<DienTu> dienTuList = new ArrayList<>();
        DienTu dienTu = new DienTu();

        List<ThuongHieu> thuongHieuList = modelDienTu.LayDanhSachThuongHieu();
        List<SanPham> sanPhamList = modelDienTu.LayDanhSachSanPham();

        dienTu.setTennoibat("Thương hiệu nổi bật");
        dienTu.setTentopnoibat("Top điện thoại và máy tính bảng");
        dienTu.setThuongHieuList(thuongHieuList);
        dienTu.setSanPhamList(sanPhamList);
        dienTu.setKiemtra(true);

        DienTu dienTu2 = new DienTu();

        List<ThuongHieu> phuKienList = modelDienTu.LayDanhSachPhuKien();
        List<SanPham> topPhuKienList = modelDienTu.LayDanhSachTopPhuKien();

        dienTu2.setTennoibat("Phụ kiện");
        dienTu2.setTentopnoibat("Top phụ kiện");
        dienTu2.setThuongHieuList(phuKienList);
        dienTu2.setSanPhamList(topPhuKienList);
        dienTu2.setKiemtra(false);

        DienTu dienTu3 = new DienTu();

        List<ThuongHieu> tienIchList = modelDienTu.LayDanhSachTienIch();
        List<SanPham> topTienIchList = modelDienTu.LayDanhSachTopTienIch();

        dienTu3.setTennoibat("Tiện ích");
        dienTu3.setTentopnoibat("Tivi & video");
        dienTu3.setThuongHieuList(tienIchList);
        dienTu3.setSanPhamList(topTienIchList);
        dienTu3.setKiemtra(false);

        dienTuList.add(dienTu);
        dienTuList.add(dienTu2);
        dienTuList.add(dienTu3);

        if(dienTuList.size() > 0){
            viewXuLyFragmentDienTu.HienThiDanhSachDienTu(dienTuList);
        }

    }

    @Override
    public void LayDanhSachLogoThuongHieu() {
        List<ThuongHieu> thuongHieuList = modelDienTu.LayDanhSachLogoThuongHieu();
        if(thuongHieuList.size() > 0){
            viewXuLyFragmentDienTu.HienThiDanhSachLogoThuongHieu(thuongHieuList);
        }

    }

    @Override
    public void LayDanhSachSanPhamMoi() {
        List<SanPham> sanPhamList = modelDienTu.LayDanhSachCacSanPhamMoi();
        if(sanPhamList.size() > 0){
            viewXuLyFragmentDienTu.HienThiDanhSachSanPhamMoi(sanPhamList);
        }
    }
}
