package com.project.luulinhson.muasamtructuyen.Model.Object;

import java.util.List;

/**
 * Created by Admin on 3/28/2017.
 */

public class DienTu {

    List<ThuongHieu> thuongHieuList;
    List<SanPham> sanPhamList;
    String tennoibat;
    String tentopnoibat;
    Boolean kiemtra;

    public Boolean getKiemtra() {
        return kiemtra;
    }

    public void setKiemtra(Boolean kiemtra) {
        this.kiemtra = kiemtra;
    }



    public String getTennoibat() {
        return tennoibat;
    }

    public void setTennoibat(String tennoibat) {
        this.tennoibat = tennoibat;
    }

    public String getTentopnoibat() {
        return tentopnoibat;
    }

    public void setTentopnoibat(String tentopnoibat) {
        this.tentopnoibat = tentopnoibat;
    }






    public List<ThuongHieu> getThuongHieuList() {
        return thuongHieuList;
    }

    public void setThuongHieuList(List<ThuongHieu> thuongHieuList) {
        this.thuongHieuList = thuongHieuList;
    }

    public List<SanPham> getSanPhamList() {
        return sanPhamList;
    }

    public void setSanPhamList(List<SanPham> sanPhamList) {
        this.sanPhamList = sanPhamList;
    }


}
