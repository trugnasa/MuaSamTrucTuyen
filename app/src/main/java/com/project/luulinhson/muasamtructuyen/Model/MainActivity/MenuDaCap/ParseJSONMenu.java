package com.project.luulinhson.muasamtructuyen.Model.MainActivity.MenuDaCap;

import com.project.luulinhson.muasamtructuyen.Connection.DownLoadJSON;
import com.project.luulinhson.muasamtructuyen.Model.Object.LoaiSanPham;
import com.project.luulinhson.muasamtructuyen.View.Main.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Admin on 3/3/2017.
 */

public class ParseJSONMenu {


    public List<LoaiSanPham> ParseJSONMenu(String datajson){
        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(datajson);
            JSONArray jsonArray = jsonObject.getJSONArray("LOAISANPHAM");
            int count = jsonArray.length();
            for (int i = 0;i < count ;i++){
                JSONObject jsonObjectLoaiSanPham = jsonArray.getJSONObject(i);

                LoaiSanPham loaiSanPham = new LoaiSanPham();
                loaiSanPham.setMALOAI_SANPHAM(jsonObjectLoaiSanPham.getInt("MALOAISP"));
                loaiSanPham.setMALOAI_CHA(jsonObjectLoaiSanPham.getInt("MALOAI_CHA"));
                loaiSanPham.setTEN_SAN_PHAM(jsonObjectLoaiSanPham.getString("TENLOAISP"));

                loaiSanPhamList.add(loaiSanPham);


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return loaiSanPhamList;
    }

    public List<LoaiSanPham> LayDanhSachLoaiSanPham(int maloaicha){
        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String datajson = "";

        // Lấy dữ liệu bằng phương thức POST
//        String duongdan = "http://10.0.3.2/weblazada/dulieu.php"; // dùng cho genymotion
        String duongdan = MainActivity.SERVER_NAME + "/weblazada/dulieu.php"; // dùng cho máy thật

        HashMap<String,String> hmHam = new HashMap<>(); //truyền tên hàm cần dùng để lấy dữ liệu
        hmHam.put("ham","LayDanhSachMenu");

        HashMap<String,String> hmMaLoaiCha = new HashMap<>(); // truyền maloaicha
        hmMaLoaiCha.put("maloaicha",String.valueOf(maloaicha));

        attrs.add(hmMaLoaiCha);
        attrs.add(hmHam);


        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);
        // Kết thúc lấy dữ liệu bằng phương thức POST

        downLoadJSON.execute();

        try {
            datajson = downLoadJSON.get();
            ParseJSONMenu parseJSONMenu = new ParseJSONMenu();
            loaiSanPhamList = parseJSONMenu.ParseJSONMenu(datajson);


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return loaiSanPhamList;
    }

}
