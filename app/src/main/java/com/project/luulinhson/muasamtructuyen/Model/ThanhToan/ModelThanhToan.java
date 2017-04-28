package com.project.luulinhson.muasamtructuyen.Model.ThanhToan;

import android.util.Log;

import com.project.luulinhson.muasamtructuyen.Connection.DownLoadJSON;
import com.project.luulinhson.muasamtructuyen.Model.Object.ChiTietHoaDon;
import com.project.luulinhson.muasamtructuyen.Model.Object.DanhGia;
import com.project.luulinhson.muasamtructuyen.Model.Object.HoaDon;
import com.project.luulinhson.muasamtructuyen.View.Main.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Admin on 4/19/2017.
 */

public class ModelThanhToan {

    public boolean ThemHoaDon(HoaDon hoaDon){

        Boolean kiemtra = false;

//                String duongdan = "http://10.0.3.2/weblazada/dulieu.php"; // dùng cho genymotion
        String duongdan = MainActivity.SERVER_NAME + "/weblazada/dulieu.php"; // dùng cho máy thật

        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String,String> hsTenHam = new HashMap<>();
        hsTenHam.put("ham","ThemHoaDon");

        List<ChiTietHoaDon> chiTietHoaDonList = hoaDon.getChiTietHoaDonList();
        String chuoiJson = "{\"DANHSACHSANPHAM\" :[ ";
        int count = chiTietHoaDonList.size();
        for (int i = 0; i < count; i++) {
            chuoiJson += "{";
            chuoiJson += "\"masp\" : " + chiTietHoaDonList.get(i).getMASP() + ",";
            chuoiJson += "\"soluong\" : " + chiTietHoaDonList.get(i).getSOLUONG();

            if(i == (chiTietHoaDonList.size() - 1)){
                chuoiJson += "}";
            }else {
                chuoiJson += "},";
            }

        }
        chuoiJson += "]}";

        Log.d("JSON", "ThemHoaDon: " + chuoiJson);

        HashMap<String,String> hsDanhSachSanPham = new HashMap<>();
        hsDanhSachSanPham.put("danhsachsanpham",chuoiJson);

        HashMap<String,String> hsTenNguoiNhan = new HashMap<>();
        hsTenNguoiNhan.put("tennguoinhan",hoaDon.getTENNGUOINHAN());

        HashMap<String,String> hsSoDienThoai = new HashMap<>();
        hsSoDienThoai.put("sodt",hoaDon.getSODT());

        HashMap<String,String> hsDiaChi = new HashMap<>();
        hsDiaChi.put("diachi",hoaDon.getDIACHI());

        HashMap<String,String> hsChuyenKhoan = new HashMap<>();
        hsChuyenKhoan.put("chuyenkhoan",String.valueOf(hoaDon.getCHUYENKHOAN()));



        attrs.add(hsTenHam);
        attrs.add(hsDanhSachSanPham);
        attrs.add(hsTenNguoiNhan);
        attrs.add(hsSoDienThoai);
        attrs.add(hsDiaChi);
        attrs.add(hsChuyenKhoan);



        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);
        downLoadJSON.execute();

        try {
            String dulieuJson = downLoadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieuJson); // parse json
            String result = jsonObject.getString("ketqua");

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

        return kiemtra;
    }
}
