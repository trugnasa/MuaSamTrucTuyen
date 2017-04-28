package com.project.luulinhson.muasamtructuyen.View.LogIn_Register.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.project.luulinhson.muasamtructuyen.Model.Object.NhanVien;
import com.project.luulinhson.muasamtructuyen.Presenter.RegisterActivity.XuLyDangKy;
import com.project.luulinhson.muasamtructuyen.R;
import com.project.luulinhson.muasamtructuyen.View.LogIn_Register.ViewXuLyDangKy;

import es.dmoral.toasty.Toasty;

/**
 * Created by Admin on 3/12/2017.
 */

public class FragmentDangKy extends Fragment implements ViewXuLyDangKy,View.OnClickListener,View.OnFocusChangeListener{

    EditText edHoTenDK,edEmailDK,edMatKhauDK,edNhapLaiMatKhauDK;
    SwitchCompat switchEmailDocQuyen;
    Button btnDangKy;
    TextInputLayout inputEdHoTenDk,inputEdEmailDK,inputEdNhapLaiMatKhauDK,inputEdMatKhauDK;
    XuLyDangKy xuLyDangKy;
    Boolean kiemtra = false ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dang_ky,container,false);

        edHoTenDK = (EditText) view.findViewById(R.id.edHoTenDK);
        edEmailDK = (EditText) view.findViewById(R.id.edEmailDK);
        edMatKhauDK = (EditText) view.findViewById(R.id.edMatKhauDK);
        edNhapLaiMatKhauDK = (EditText) view.findViewById(R.id.edNhapLaiMatKhauDK);
        switchEmailDocQuyen = (SwitchCompat) view.findViewById(R.id.switchEmailDocQuyen);
        btnDangKy = (Button) view.findViewById(R.id.btnDangKy);

        inputEdHoTenDk = (TextInputLayout) view.findViewById(R.id.input_edHoTenDK);
        inputEdEmailDK = (TextInputLayout) view.findViewById(R.id.input_edEmailDK);
        inputEdMatKhauDK = (TextInputLayout) view.findViewById(R.id.input_edPassDK);
        inputEdNhapLaiMatKhauDK = (TextInputLayout) view.findViewById(R.id.input_edPassAgainDK);

        xuLyDangKy = new XuLyDangKy(this);


        btnDangKy.setOnClickListener(this);
//        edHoTenDK.setOnFocusChangeListener(this);
        edEmailDK.setOnFocusChangeListener(this);
//        edMatKhauDK.setOnFocusChangeListener(this);
//        edNhapLaiMatKhauDK.setOnFocusChangeListener(this);
//        edNhapLaiMatKhauDK.addTextChangedListener(new ObjectInputValidation());
        edNhapLaiMatKhauDK.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                    String chuoi = edNhapLaiMatKhauDK.getText().toString();
                    String matkhau = edMatKhauDK.getText().toString();
                    if(!chuoi.equals(matkhau)){
                        inputEdNhapLaiMatKhauDK.setErrorEnabled(true);
                        inputEdNhapLaiMatKhauDK.setError("Mật khẩu không trùng khớp");
                        kiemtra = false;
                    }else {
                        inputEdNhapLaiMatKhauDK.setErrorEnabled(false);
                        inputEdNhapLaiMatKhauDK.setError("");
                        kiemtra = true;
                    }
            }
        });
//



        return view;
    }

    @Override
    public void DangKyThanhCong() {
//        Toast.makeText(getActivity(),"Đăng ký thành công",Toast.LENGTH_SHORT).show();
        Toasty.success(getActivity(),"Đăng ký thành công",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DangKyThatBai() {
//        Toast.makeText(getActivity(),"Đăng ký thất bại",Toast.LENGTH_SHORT).show();
        Toasty.error(getActivity(),"Đăng ký thất bại",Toast.LENGTH_SHORT).show();
    }

    String emaildocquyen = "";

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnDangKy:
                String tennv = edHoTenDK.getText().toString();
                String tendangnhap = edEmailDK.getText().toString();
                String matkhau = edMatKhauDK.getText().toString();


                switchEmailDocQuyen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            emaildocquyen = "true";
                        }else {
                            emaildocquyen = "false";
                        }
                        Log.d("true", "onCheckedChanged: " + emaildocquyen);
                    }
                });

                Log.d("check", "onClick: " + kiemtra.toString());

                if(kiemtra)
                {
                    NhanVien nhanVien = new NhanVien();
                    nhanVien.setTenNhanVien(tennv);
                    nhanVien.setTenDangNhap(tendangnhap);
                    nhanVien.setMatKhau(matkhau);
                    nhanVien.setEmailDocQuyen(emaildocquyen);
                    nhanVien.setMaLoaiNV(2);

                    xuLyDangKy.ThucHienDangKy(nhanVien);
                }
                break;
        }


    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();
        switch (id){
            case R.id.edHoTenDK:
                break;
            case R.id.edEmailDK:
                if(!hasFocus){
                    String chuoi = ((EditText)v).getText().toString();
                    Boolean kiemtraemail = Patterns.EMAIL_ADDRESS.matcher(chuoi).matches();
                    if(!kiemtraemail){
                        inputEdEmailDK.setErrorEnabled(true);
                        inputEdEmailDK.setError("Đây không phải là địa chỉ Email!");
                        kiemtra = false;
                    }else {
                        inputEdEmailDK.setErrorEnabled(false);
                        inputEdEmailDK.setError("");
                        kiemtra = true;
                    }
                }
                break;
            case R.id.edMatKhauDK:

                break;
            case R.id.edNhapLaiMatKhauDK:
                if(!hasFocus){
                    String chuoi = ((EditText)v).getText().toString();
                    String matkhau = edMatKhauDK.getText().toString();
                    if(!chuoi.equals(matkhau)){
                        inputEdNhapLaiMatKhauDK.setErrorEnabled(true);
                        inputEdNhapLaiMatKhauDK.setError("Mật khẩu không trùng khớp");
                        kiemtra = false;
                    }else {
                        inputEdNhapLaiMatKhauDK.setErrorEnabled(false);
                        inputEdNhapLaiMatKhauDK.setError("");
                        kiemtra = true;
                    }
                }
                break;
        }
    }

}
