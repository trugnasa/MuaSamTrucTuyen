package com.project.luulinhson.muasamtructuyen.Model.MainActivity.FragmentDienTu;

import android.util.Log;

import com.project.luulinhson.muasamtructuyen.Connection.DownLoadJSON;
import com.project.luulinhson.muasamtructuyen.Model.MainActivity.MenuDaCap.ParseJSONMenu;
import com.project.luulinhson.muasamtructuyen.Model.Object.SanPham;
import com.project.luulinhson.muasamtructuyen.Model.Object.ThuongHieu;
import com.project.luulinhson.muasamtructuyen.View.Main.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Admin on 3/28/2017.
 */

public class ModelDienTu {

    public List<ThuongHieu> LayDanhSachThuongHieu(){
        List<ThuongHieu> thuongHieuList = new ArrayList<>();
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String datajson = "";

        String duongdan = MainActivity.SERVER_NAME + "/weblazada/dulieu.php"; // dùng cho máy thật

        HashMap<String,String> hmHam = new HashMap<>(); //truyền tên hàm cần dùng để lấy dữ liệu
        hmHam.put("ham","LayDanhSachCacThuongHieuLon");

        attrs.add(hmHam);


        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);
        // Kết thúc lấy dữ liệu bằng phương thức POST

        downLoadJSON.execute();

        try {
            datajson = downLoadJSON.get();
            JSONObject jsonObject = new JSONObject(datajson);
            JSONArray jsonArrayThuongHieu = jsonObject.getJSONArray("DANHSACHTHUONGHIEU");
            int count = jsonArrayThuongHieu.length();
            for (int i = 0; i < count; i++) {
                JSONObject jObjectThuongHieu = jsonArrayThuongHieu.getJSONObject(i);
                ThuongHieu thuonghieu = new ThuongHieu();
                thuonghieu.setMATHUONGHIEU(jObjectThuongHieu.getInt("MATHUONGHIEU"));
                thuonghieu.setTENTHUONGHIEU(jObjectThuongHieu.getString("TENTHUONGHIEU"));
                thuonghieu.setHINHTHUONGHIEU(jObjectThuongHieu.getString("HINHTHUONGHIEU"));

                thuongHieuList.add(thuonghieu);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return thuongHieuList;
    }

    public List<SanPham> LayDanhSachSanPham(){
        List<SanPham> sanPhamList = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String datajson = "";

        String duongdan = MainActivity.SERVER_NAME + "/weblazada/dulieu.php"; // dùng cho máy thật

        HashMap<String,String> hmHam = new HashMap<>(); //truyền tên hàm cần dùng để lấy dữ liệu
        hmHam.put("ham","LayDanhSachTopDienThoaiVaMayTinhBang");

        attrs.add(hmHam);


        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);
        // Kết thúc lấy dữ liệu bằng phương thức POST

        downLoadJSON.execute();

        try {
            datajson = downLoadJSON.get();
            JSONObject jsonObject = new JSONObject(datajson);
            JSONArray jsonArrayThuongHieu = jsonObject.getJSONArray("TOPDIENTHOAI&MAYTINHBANG");
            int count = jsonArrayThuongHieu.length();
            for (int i = 0; i < count; i++) {
                JSONObject jObjectThuongHieu = jsonArrayThuongHieu.getJSONObject(i);
                SanPham sanPham = new SanPham();
                sanPham.setMASP(jObjectThuongHieu.getInt("MASP"));
                sanPham.setTENSP(jObjectThuongHieu.getString("TENSP"));
                sanPham.setGIA(jObjectThuongHieu.getInt("GIATIEN"));
                sanPham.setANHLON(jObjectThuongHieu.getString("HINHSANPHAM"));

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

    public List<ThuongHieu> LayDanhSachPhuKien(){
        List<ThuongHieu> phuKienList = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String datajson = "";

        String duongdan = MainActivity.SERVER_NAME + "/weblazada/dulieu.php"; // dùng cho máy thật

        HashMap<String,String> hmHam = new HashMap<>(); //truyền tên hàm cần dùng để lấy dữ liệu
        hmHam.put("ham","LayDanhSachPhuKien");

        attrs.add(hmHam);


        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);
        // Kết thúc lấy dữ liệu bằng phương thức POST

        downLoadJSON.execute();

        try {
            datajson = downLoadJSON.get();
            Log.d("phukien", "LayDanhSachPhuKien: " + datajson.toString());
            JSONObject jsonObject = new JSONObject(datajson);
            JSONArray jsonArrayThuongHieu = jsonObject.getJSONArray("DANHSACHPHUKIEN");
            int count = jsonArrayThuongHieu.length();
            for (int i = 0; i < count; i++) {
                JSONObject jObjectThuongHieu = jsonArrayThuongHieu.getJSONObject(i);
                ThuongHieu thuongHieu = new ThuongHieu();

                thuongHieu.setMATHUONGHIEU(jObjectThuongHieu.getInt("MASP"));
                thuongHieu.setTENTHUONGHIEU(jObjectThuongHieu.getString("TENSP"));
                thuongHieu.setHINHTHUONGHIEU(jObjectThuongHieu.getString("HINHSANPHAM"));

                phuKienList.add(thuongHieu);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return phuKienList;
    }

    public List<SanPham> LayDanhSachTopPhuKien(){
        List<SanPham> topPhuKienList = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String datajson = "";

        String duongdan = MainActivity.SERVER_NAME + "/weblazada/dulieu.php"; // dùng cho máy thật

        HashMap<String,String> hmHam = new HashMap<>(); //truyền tên hàm cần dùng để lấy dữ liệu
        hmHam.put("ham","LayDanhSachTopPhuKien");

        attrs.add(hmHam);


        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);
        // Kết thúc lấy dữ liệu bằng phương thức POST

        downLoadJSON.execute();

        try {
            datajson = downLoadJSON.get();
            JSONObject jsonObject = new JSONObject(datajson);
            JSONArray jsonArrayThuongHieu = jsonObject.getJSONArray("TOPPHUKIEN");
            int count = jsonArrayThuongHieu.length();
            for (int i = 0; i < count; i++) {
                JSONObject jObjectThuongHieu = jsonArrayThuongHieu.getJSONObject(i);
                SanPham sanPham = new SanPham();
                sanPham.setMASP(jObjectThuongHieu.getInt("MASP"));
                sanPham.setTENSP(jObjectThuongHieu.getString("TENSP"));
                sanPham.setGIA(jObjectThuongHieu.getInt("GIATIEN"));
                sanPham.setANHLON(jObjectThuongHieu.getString("HINHSANPHAM"));

                topPhuKienList.add(sanPham);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return topPhuKienList;
    }

    public List<ThuongHieu> LayDanhSachTienIch(){
        List<ThuongHieu> tienIchList = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String datajson = "";

        String duongdan = MainActivity.SERVER_NAME + "/weblazada/dulieu.php"; // dùng cho máy thật

        HashMap<String,String> hmHam = new HashMap<>(); //truyền tên hàm cần dùng để lấy dữ liệu
        hmHam.put("ham","LayDanhSachTienIch");

        attrs.add(hmHam);


        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);
        // Kết thúc lấy dữ liệu bằng phương thức POST

        downLoadJSON.execute();

        try {
            datajson = downLoadJSON.get();
            JSONObject jsonObject = new JSONObject(datajson);
            JSONArray jsonArrayThuongHieu = jsonObject.getJSONArray("DANHSACHTIENICH");
            int count = jsonArrayThuongHieu.length();

            for (int i = 0; i < count; i++) {
                JSONObject jObjectThuongHieu = jsonArrayThuongHieu.getJSONObject(i);
                ThuongHieu thuongHieu = new ThuongHieu();

                thuongHieu.setMATHUONGHIEU(jObjectThuongHieu.getInt("MASP"));
                thuongHieu.setTENTHUONGHIEU(jObjectThuongHieu.getString("TENSP"));
                thuongHieu.setHINHTHUONGHIEU(jObjectThuongHieu.getString("HINHSANPHAM"));

                tienIchList.add(thuongHieu);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tienIchList;
    }

    public List<SanPham> LayDanhSachTopTienIch(){
        List<SanPham> topTienIchList = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String datajson = "";

        String duongdan = MainActivity.SERVER_NAME + "/weblazada/dulieu.php"; // dùng cho máy thật

        HashMap<String,String> hmHam = new HashMap<>(); //truyền tên hàm cần dùng để lấy dữ liệu
        hmHam.put("ham","LayTopTienIch");

        attrs.add(hmHam);


        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);
        // Kết thúc lấy dữ liệu bằng phương thức POST

        downLoadJSON.execute();

        try {
            datajson = downLoadJSON.get();
            JSONObject jsonObject = new JSONObject(datajson);
            JSONArray jsonArrayThuongHieu = jsonObject.getJSONArray("TOPTIENICH");
            int count = jsonArrayThuongHieu.length();
            for (int i = 0; i < count; i++) {
                JSONObject jObjectThuongHieu = jsonArrayThuongHieu.getJSONObject(i);
                SanPham sanPham = new SanPham();
                sanPham.setMASP(jObjectThuongHieu.getInt("MASP"));
                sanPham.setTENSP(jObjectThuongHieu.getString("TENSP"));
                sanPham.setGIA(jObjectThuongHieu.getInt("GIATIEN"));
                sanPham.setANHLON(jObjectThuongHieu.getString("HINHSANPHAM"));

                topTienIchList.add(sanPham);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return topTienIchList;
    }

    public List<ThuongHieu> LayDanhSachLogoThuongHieu(){
        List<ThuongHieu> logoList = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String datajson = "";

        String duongdan = MainActivity.SERVER_NAME + "/weblazada/dulieu.php"; // dùng cho máy thật

        HashMap<String,String> hmHam = new HashMap<>(); //truyền tên hàm cần dùng để lấy dữ liệu
        hmHam.put("ham","LayLogoThuongHieuLon");

        attrs.add(hmHam);


        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);
        // Kết thúc lấy dữ liệu bằng phương thức POST

        downLoadJSON.execute();

        try {
            datajson = downLoadJSON.get();
            JSONObject jsonObject = new JSONObject(datajson);
            JSONArray jsonArrayThuongHieu = jsonObject.getJSONArray("DANHSACHTHUONGHIEU");
            int count = jsonArrayThuongHieu.length();

            for (int i = 0; i < count; i++) {
                JSONObject jObjectThuongHieu = jsonArrayThuongHieu.getJSONObject(i);
                ThuongHieu thuongHieu = new ThuongHieu();

                thuongHieu.setMATHUONGHIEU(jObjectThuongHieu.getInt("MASP"));
                thuongHieu.setTENTHUONGHIEU(jObjectThuongHieu.getString("TENSP"));
                thuongHieu.setHINHTHUONGHIEU(jObjectThuongHieu.getString("HINHSANPHAM"));

                logoList.add(thuongHieu);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return logoList;
    }

    public List<SanPham> LayDanhSachCacSanPhamMoi(){
        List<SanPham> sanPhamMoiList = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String datajson = "";

        String duongdan = MainActivity.SERVER_NAME + "/weblazada/dulieu.php"; // dùng cho máy thật

        HashMap<String,String> hmHam = new HashMap<>(); //truyền tên hàm cần dùng để lấy dữ liệu
        hmHam.put("ham","LayDanhSachSanPhamMoi");

        attrs.add(hmHam);


        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);
        // Kết thúc lấy dữ liệu bằng phương thức POST

        downLoadJSON.execute();

        try {
            datajson = downLoadJSON.get();
            JSONObject jsonObject = new JSONObject(datajson);
            JSONArray jsonArrayThuongHieu = jsonObject.getJSONArray("DANHSACHSANPHAMMOIVE");
            int count = jsonArrayThuongHieu.length();
            for (int i = 0; i < count; i++) {
                JSONObject jObjectThuongHieu = jsonArrayThuongHieu.getJSONObject(i);
                SanPham sanPham = new SanPham();
                sanPham.setMASP(jObjectThuongHieu.getInt("MASP"));
                sanPham.setTENSP(jObjectThuongHieu.getString("TENSP"));
                sanPham.setGIA(jObjectThuongHieu.getInt("GIATIEN"));
                sanPham.setANHLON(jObjectThuongHieu.getString("HINHSANPHAM"));

                sanPhamMoiList.add(sanPham);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sanPhamMoiList;
    }

}
