package com.project.luulinhson.muasamtructuyen.Model.TimKiem;

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
 * Created by Admin on 4/21/2017.
 */

public class ModelTimKiem {

    public List<SanPham> LayDanhSachSanPhamTheoTenSanPham(String tensp, int limit){
        List<SanPham> sanPhamList = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String datajson = "";

        String duongdan = MainActivity.SERVER_NAME + "/weblazada/dulieu.php"; // dùng cho máy thật

        HashMap<String,String> hmHam = new HashMap<>(); //truyền tên hàm cần dùng để lấy dữ liệu
        hmHam.put("ham","TimKiemSanPhamTheoTenSanPham");

        HashMap<String,String> hmLimit = new HashMap<>(); //truyền tên hàm cần dùng để lấy dữ liệu
        hmLimit.put("limit",String.valueOf(limit));

        HashMap<String,String> hmTenSP = new HashMap<>(); //truyền tên hàm cần dùng để lấy dữ liệu
        hmTenSP.put("tensp",tensp);

        attrs.add(hmHam);
        attrs.add(hmLimit);
        attrs.add(hmTenSP);


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
                sanPham.setANHLON(MainActivity.SERVER + jObjectThuongHieu.getString("HINHSANPHAM"));
                sanPham.setANHNHO(jObjectThuongHieu.getString("HINHSANPHAMNHO"));
                sanPham.setMANV(jObjectThuongHieu.getInt("MANV"));

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
