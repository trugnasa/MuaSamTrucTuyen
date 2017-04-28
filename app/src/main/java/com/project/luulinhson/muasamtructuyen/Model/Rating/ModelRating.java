package com.project.luulinhson.muasamtructuyen.Model.Rating;

import android.util.Log;

import com.project.luulinhson.muasamtructuyen.Connection.DownLoadJSON;
import com.project.luulinhson.muasamtructuyen.Model.Object.DanhGia;
import com.project.luulinhson.muasamtructuyen.Model.Object.NhanVien;
import com.project.luulinhson.muasamtructuyen.View.Main.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Admin on 4/12/2017.
 */

public class ModelRating {


    public List<DanhGia> LayDanhSachDanhGiaTheoMaSP(int masp, int limit){
        List<DanhGia> danhGiaList = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String datajson = "";

        String duongdan = MainActivity.SERVER_NAME + "/weblazada/dulieu.php"; // dùng cho máy thật

        HashMap<String,String> hmHam = new HashMap<>(); //truyền tên hàm cần dùng để lấy dữ liệu
        hmHam.put("ham","LayDanhSachDanhGiaTheoMaSP");

        HashMap<String,String> hmLimit = new HashMap<>(); //truyền tên hàm cần dùng để lấy dữ liệu
        hmLimit.put("limit",String.valueOf(limit));

        HashMap<String,String> hmMaLoai = new HashMap<>(); //truyền tên hàm cần dùng để lấy dữ liệu
        hmMaLoai.put("masp",String.valueOf(masp));

        attrs.add(hmHam);
        attrs.add(hmMaLoai);
        attrs.add(hmLimit);


        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);
        // Kết thúc lấy dữ liệu bằng phương thức POST

        downLoadJSON.execute();

        try {
            datajson = downLoadJSON.get();
            JSONObject jsonObject = new JSONObject(datajson);
            JSONArray jsonArrayThuongHieu = jsonObject.getJSONArray("DANHSACHDANHGIA");
            int count = jsonArrayThuongHieu.length();
            for (int i = 0; i < count; i++) {
                JSONObject jObjectThuongHieu = jsonArrayThuongHieu.getJSONObject(i);
                DanhGia danhGia = new DanhGia();
                danhGia.setTIEUDE(jObjectThuongHieu.getString("TIEUDE"));
                danhGia.setSOSAO(jObjectThuongHieu.getInt("SOSAO"));
                danhGia.setTENTHIETBI(jObjectThuongHieu.getString("TENTHIETBI"));
                danhGia.setNGAYDANHGIA(jObjectThuongHieu.getString("NGAYDANHGIA"));
                danhGia.setNOIDUNG(jObjectThuongHieu.getString("NOIDUNG"));

                danhGiaList.add(danhGia);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return danhGiaList;
    }

    public boolean ThemDanhGia(DanhGia danhGia){

        Boolean kiemtra = false;

//                String duongdan = "http://10.0.3.2/weblazada/dulieu.php"; // dùng cho genymotion
        String duongdan = MainActivity.SERVER_NAME + "/weblazada/dulieu.php"; // dùng cho máy thật

        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String,String> hsTenHam = new HashMap<>();
        hsTenHam.put("ham","ThemDanhGia");

        HashMap<String,String> hsMadg = new HashMap<>();
        hsMadg.put("madg",danhGia.getMADG());

        HashMap<String,String> hsMasp = new HashMap<>();
        hsMasp.put("masp",String.valueOf(danhGia.getMASP()));

        HashMap<String,String> hsTenThietBi = new HashMap<>();
        hsTenThietBi.put("tenthietbi",danhGia.getTENTHIETBI());

        HashMap<String,String> hsTieuDe = new HashMap<>();
        hsTieuDe.put("tieude",danhGia.getTIEUDE());

        HashMap<String,String> hsNoiDung = new HashMap<>();
        hsNoiDung.put("noidung",danhGia.getNOIDUNG());

        HashMap<String,String> hsSoSao = new HashMap<>();
        hsSoSao.put("sosao",String.valueOf(danhGia.getSOSAO()));

        attrs.add(hsTenHam);
        attrs.add(hsMadg);
        attrs.add(hsMasp);
        attrs.add(hsTenThietBi);
        attrs.add(hsTieuDe);
        attrs.add(hsNoiDung);
        attrs.add(hsSoSao);


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
