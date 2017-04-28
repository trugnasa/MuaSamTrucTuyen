package com.project.luulinhson.muasamtructuyen.Model.Object;

import java.util.List;

/**
 * Created by Admin on 3/3/2017.
 */

public class LoaiSanPham {
    int MALOAI_SANPHAM;
    int MALOAI_CHA;
    String TEN_SAN_PHAM;
    List<LoaiSanPham> loaiSanPhamChildren;


    public int getMALOAI_SANPHAM() {
        return MALOAI_SANPHAM;
    }

    public void setMALOAI_SANPHAM(int MALOAI_SANPHAM) {
        this.MALOAI_SANPHAM = MALOAI_SANPHAM;
    }

    public int getMALOAI_CHA() {
        return MALOAI_CHA;
    }

    public void setMALOAI_CHA(int MALOAI_CHA) {
        this.MALOAI_CHA = MALOAI_CHA;
    }

    public String getTEN_SAN_PHAM() {
        return TEN_SAN_PHAM;
    }

    public void setTEN_SAN_PHAM(String TEN_SAN_PHAM) {
        this.TEN_SAN_PHAM = TEN_SAN_PHAM;
    }

    public List<LoaiSanPham> getLoaiSanPhamChildren() {
        return loaiSanPhamChildren;
    }

    public void setLoaiSanPhamChildren(List<LoaiSanPham> loaiSanPhamChildren) {
        this.loaiSanPhamChildren = loaiSanPhamChildren;
    }
}
