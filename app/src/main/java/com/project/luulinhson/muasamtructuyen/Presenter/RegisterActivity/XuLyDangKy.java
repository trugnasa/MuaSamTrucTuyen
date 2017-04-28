package com.project.luulinhson.muasamtructuyen.Presenter.RegisterActivity;

import com.project.luulinhson.muasamtructuyen.Model.LogIn_Register.ModelRegister;
import com.project.luulinhson.muasamtructuyen.Model.Object.NhanVien;
import com.project.luulinhson.muasamtructuyen.View.LogIn_Register.ViewXuLyDangKy;

/**
 * Created by Admin on 3/24/2017.
 */

public class XuLyDangKy implements iXuLyDangKy {

    ViewXuLyDangKy viewXuLyDangKy;
    ModelRegister modelRegister;

    public XuLyDangKy(ViewXuLyDangKy viewXuLyDangKy){
        this.viewXuLyDangKy = viewXuLyDangKy;
        modelRegister = new ModelRegister();
    }

    @Override
    public void ThucHienDangKy(NhanVien nhanVien) {
        Boolean kiemtra = modelRegister.DangKyThanhVien(nhanVien);
        if(kiemtra){
            viewXuLyDangKy.DangKyThanhCong();
        }else {
            viewXuLyDangKy.DangKyThatBai();
        }
    }
}
