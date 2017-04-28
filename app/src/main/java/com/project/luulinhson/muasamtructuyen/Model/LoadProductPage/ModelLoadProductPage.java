package com.project.luulinhson.muasamtructuyen.Model.LoadProductPage;

import com.project.luulinhson.muasamtructuyen.Connection.DownLoadJSON;
import com.project.luulinhson.muasamtructuyen.Model.Object.SanPham;
import com.project.luulinhson.muasamtructuyen.View.Main.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Admin on 4/4/2017.
 */

public class ModelLoadProductPage {

    public List<SanPham> LayDanhSachSanPhamTheoMaLoaiDanhMuc(int maloai,int limit){
        List<SanPham> sanPhamList = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String datajson = "";

        String duongdan = MainActivity.SERVER_NAME + "/weblazada/dulieu.php"; // dùng cho máy thật

        HashMap<String,String> hmHam = new HashMap<>(); //truyền tên hàm cần dùng để lấy dữ liệu
        hmHam.put("ham","LaySanPhamTheoMaLoai");

        HashMap<String,String> hmLimit = new HashMap<>(); //truyền tên hàm cần dùng để lấy dữ liệu
        hmLimit.put("limit",String.valueOf(limit));

        HashMap<String,String> hmMaLoai = new HashMap<>(); //truyền tên hàm cần dùng để lấy dữ liệu
        hmMaLoai.put("maloai",String.valueOf(maloai));

        attrs.add(hmHam);
        attrs.add(hmLimit);
        attrs.add(hmMaLoai);


        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);
        // Kết thúc lấy dữ liệu bằng phương thức POST

        downLoadJSON.execute();

        try {
            datajson = downLoadJSON.get();
            JSONObject jsonObject = new JSONObject(datajson);
            JSONArray jsonArrayThuongHieu = jsonObject.getJSONArray("DANHSACHSANPHAM");
            int count = jsonArrayThuongHieu.length();
            for (int i = 0; i < count; i++) {
                JSONObject jObjectThuongHieu = jsonArrayThuongHieu.getJSONObject(i);
                SanPham sanPham = new SanPham();
                sanPham.setMASP(jObjectThuongHieu.getInt("MASP"));
                sanPham.setTENSP(jObjectThuongHieu.getString("TENSP"));
                sanPham.setGIA(jObjectThuongHieu.getInt("GIATIEN"));
                sanPham.setANHLON(jObjectThuongHieu.getString("HINHSANPHAM"));
                sanPham.setANHNHO(jObjectThuongHieu.getString("HINHSANPHAMNHO"));
                sanPham.setMATHUONGHIEU(jObjectThuongHieu.getInt("MATHUONGHIEU"));

                sanPhamList.add(sanPham);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sanPhamList;

    }

    public List<SanPham> LayDanhSachSanPhamTheoMaThuongHieu(int mathuonghieu,int limit){
        List<SanPham> sanPhamList = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String datajson = "";

        String duongdan = MainActivity.SERVER_NAME + "/weblazada/dulieu.php"; // dùng cho máy thật

        HashMap<String,String> hmHam = new HashMap<>(); //truyền tên hàm cần dùng để lấy dữ liệu
        hmHam.put("ham","LaySanPhamTheoMaThuongHieu");

        HashMap<String,String> hmLimit = new HashMap<>(); //truyền tên hàm cần dùng để lấy dữ liệu
        hmLimit.put("limit",String.valueOf(limit));

        HashMap<String,String> hmMaLoai = new HashMap<>(); //truyền tên hàm cần dùng để lấy dữ liệu
        hmMaLoai.put("mathuonghieu",String.valueOf(mathuonghieu));

        attrs.add(hmHam);
        attrs.add(hmMaLoai);
        attrs.add(hmLimit);


        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);
        // Kết thúc lấy dữ liệu bằng phương thức POST

        downLoadJSON.execute();

        try {
            datajson = downLoadJSON.get();
            JSONObject jsonObject = new JSONObject(datajson);
            JSONArray jsonArrayThuongHieu = jsonObject.getJSONArray("DANHSACHSANPHAM");
            int count = jsonArrayThuongHieu.length();
            for (int i = 0; i < count; i++) {
                JSONObject jObjectThuongHieu = jsonArrayThuongHieu.getJSONObject(i);
                SanPham sanPham = new SanPham();
                sanPham.setMASP(jObjectThuongHieu.getInt("MASP"));
                sanPham.setTENSP(jObjectThuongHieu.getString("TENSP"));
                sanPham.setGIA(jObjectThuongHieu.getInt("GIATIEN"));
                sanPham.setANHLON(jObjectThuongHieu.getString("HINHSANPHAM"));
                sanPham.setANHNHO(jObjectThuongHieu.getString("HINHSANPHAMNHO"));
                sanPham.setMATHUONGHIEU(jObjectThuongHieu.getInt("MATHUONGHIEU"));

                sanPhamList.add(sanPham);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sanPhamList;
    }

}
