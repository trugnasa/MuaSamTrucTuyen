package com.project.luulinhson.muasamtructuyen.Model.LogIn_Register;

import android.util.Log;

import com.project.luulinhson.muasamtructuyen.Connection.DownLoadJSON;
import com.project.luulinhson.muasamtructuyen.Model.Object.NhanVien;
import com.project.luulinhson.muasamtructuyen.View.Main.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Admin on 3/24/2017.
 */

public class ModelRegister {

    public boolean DangKyThanhVien(NhanVien nhanVien){

        Boolean kiemtra = false;

//                String duongdan = "http://10.0.3.2/weblazada/dulieu.php"; // dùng cho genymotion
        String duongdan = MainActivity.SERVER_NAME + "/weblazada/dulieu.php"; // dùng cho máy thật

        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String,String> hsTenHam = new HashMap<>();
        hsTenHam.put("ham","DangKyThanhVien");

        HashMap<String,String> hsTenNV = new HashMap<>();
        hsTenNV.put("tennv",nhanVien.getTenNhanVien());

        HashMap<String,String> hsTenDangNhap = new HashMap<>();
        hsTenDangNhap.put("tendangnhap",nhanVien.getTenDangNhap());

        HashMap<String,String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matkhau",nhanVien.getMatKhau());

        HashMap<String,String> hsMaLoaiNhanVien = new HashMap<>();
        hsMaLoaiNhanVien.put("maloainv",String.valueOf(nhanVien.getMaLoaiNV()));

        HashMap<String,String> hsEmailDocQuyen = new HashMap<>();
        hsEmailDocQuyen.put("emaildocquyen",nhanVien.getEmailDocQuyen());

        attrs.add(hsTenHam);
        attrs.add(hsTenNV);
        attrs.add(hsTenDangNhap);
        attrs.add(hsMatKhau);
        attrs.add(hsMaLoaiNhanVien);
        attrs.add(hsEmailDocQuyen);


        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);
        downLoadJSON.execute();

        try {
            String dulieuJson = downLoadJSON.get();
            Log.d("pow", "DangKyThanhVien: " + dulieuJson);
            JSONObject jsonObject = new JSONObject(dulieuJson); // parse json
            String result = jsonObject.getString("ketqua");

            Log.d("ketqua", "DangKyThanhVien: " + result);

            if(result.equals("true")){
                kiemtra = true;
            }else {
                kiemtra = false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("alo", "DangKyThanhVien: " + kiemtra.toString());
        return kiemtra;
    }
}
