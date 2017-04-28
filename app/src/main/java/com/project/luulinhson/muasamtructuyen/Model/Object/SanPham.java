package com.project.luulinhson.muasamtructuyen.Model.Object;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Admin on 3/28/2017.
 */

public class SanPham implements Serializable{
    // Các thuộc tính lưu trên server
    int MASP,GIA,SOLUONG,MALOAISP,MATHUONGHIEU,MANV,LUOTMUA;
    String ANHLON;
    String ANHNHO;
    String THONGTIN;
    String TENNV;
    List<ChiTietSanPham> chiTietSanPhamList;

    // 2 thuộc tính này thuộc về cơ sở dữ liệu SQLite local
    byte[] hinhgiohang;
    int SOLUONGTONKHO;




    public int getSOLUONGTONKHO() {
        return SOLUONGTONKHO;
    }

    public void setSOLUONGTONKHO(int SOLUONGTONKHO) {
        this.SOLUONGTONKHO = SOLUONGTONKHO;
    }

    public byte[] getHinhgiohang() {
        return hinhgiohang;
    }

    public void setHinhgiohang(byte[] hinhgiohang) {
        this.hinhgiohang = hinhgiohang;
    }

    public String getTENNV() {
        return TENNV;
    }

    public void setTENNV(String TENNV) {
        this.TENNV = TENNV;
    }


    public List<ChiTietSanPham> getChiTietSanPhamList() {
        return chiTietSanPhamList;
    }

    public void setChiTietSanPhamList(List<ChiTietSanPham> chiTietSanPhamList) {
        this.chiTietSanPhamList = chiTietSanPhamList;
    }

    public String getTENSP() {
        return TENSP;
    }

    public void setTENSP(String TENSP) {
        this.TENSP = TENSP;
    }

    String TENSP;


    public int getMASP() {
        return MASP;
    }

    public void setMASP(int MASP) {
        this.MASP = MASP;
    }

    public int getGIA() {
        return GIA;
    }

    public void setGIA(int GIA) {
        this.GIA = GIA;
    }

    public int getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(int SOLUONG) {
        this.SOLUONG = SOLUONG;
    }

    public int getMALOAISP() {
        return MALOAISP;
    }

    public void setMALOAISP(int MALOAISP) {
        this.MALOAISP = MALOAISP;
    }

    public int getMATHUONGHIEU() {
        return MATHUONGHIEU;
    }

    public void setMATHUONGHIEU(int MATHUONGHIEU) {
        this.MATHUONGHIEU = MATHUONGHIEU;
    }

    public int getMANV() {
        return MANV;
    }

    public void setMANV(int MANV) {
        this.MANV = MANV;
    }

    public int getLUOTMUA() {
        return LUOTMUA;
    }

    public void setLUOTMUA(int LUOTMUA) {
        this.LUOTMUA = LUOTMUA;
    }

    public String getANHLON() {
        return ANHLON;
    }

    public void setANHLON(String ANHLON) {
        this.ANHLON = ANHLON;
    }

    public String getANHNHO() {
        return ANHNHO;
    }

    public void setANHNHO(String ANHNHO) {
        this.ANHNHO = ANHNHO;
    }

    public String getTHONGTIN() {
        return THONGTIN;
    }

    public void setTHONGTIN(String THONGTIN) {
        this.THONGTIN = THONGTIN;
    }


}
