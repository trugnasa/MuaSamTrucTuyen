package com.project.luulinhson.muasamtructuyen.Model.DetailProduct;

import android.util.Log;

import com.project.luulinhson.muasamtructuyen.Connection.DownLoadJSON;
import com.project.luulinhson.muasamtructuyen.Model.Object.ChiTietSanPham;
import com.project.luulinhson.muasamtructuyen.Model.Object.DanhGia;
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
 * Created by Admin on 4/10/2017.
 */

public class ModelDetailProduct {


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

    public SanPham LayChiTietSanPhamTheoMaSP(int masp){

        SanPham sanPham = new SanPham();
        List<ChiTietSanPham> chiTietSanPhamList = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String datajson = "";

        String duongdan = MainActivity.SERVER_NAME + "/weblazada/dulieu.php"; // dùng cho máy thật

        HashMap<String,String> hmHam = new HashMap<>(); //truyền tên hàm cần dùng để lấy dữ liệu
        hmHam.put("ham","LaySanPhamVaChitietTheoMaSP");

        HashMap<String,String> hmMasp = new HashMap<>(); //truyền tên hàm cần dùng để lấy dữ liệu
        hmMasp.put("masp",String.valueOf(masp));

        attrs.add(hmHam);
        attrs.add(hmMasp);


        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);
        // Kết thúc lấy dữ liệu bằng phương thức POST

        downLoadJSON.execute();

        try {
            datajson = downLoadJSON.get();
            JSONObject jsonObject = new JSONObject(datajson);
            JSONArray jsonArrayThuongHieu = jsonObject.getJSONArray("CHITIETSANPHAM");
            int count = jsonArrayThuongHieu.length();
            for (int i = 0; i < count; i++) {
                JSONObject jObjectThuongHieu = jsonArrayThuongHieu.getJSONObject(i);

                sanPham.setMASP(jObjectThuongHieu.getInt("MASP"));
                sanPham.setTENSP(jObjectThuongHieu.getString("TENSP"));
                sanPham.setGIA(jObjectThuongHieu.getInt("GIATIEN"));
                sanPham.setSOLUONG(jObjectThuongHieu.getInt("SOLUONG"));
                sanPham.setANHNHO(jObjectThuongHieu.getString("ANHNHO"));
                sanPham.setTHONGTIN(jObjectThuongHieu.getString("THONGTIN"));
                sanPham.setMALOAISP(jObjectThuongHieu.getInt("MALOAISP"));
                sanPham.setMATHUONGHIEU(jObjectThuongHieu.getInt("MATHUONGHIEU"));
                sanPham.setMANV(jObjectThuongHieu.getInt("MANV"));
                sanPham.setTENNV(jObjectThuongHieu.getString("TENNHANVIEN"));
                sanPham.setLUOTMUA(jObjectThuongHieu.getInt("LUOTMUA"));

                JSONArray jsonArrayThongSoKyThuat = jObjectThuongHieu.getJSONArray("THONGSOKYTHUAT");
                int dem = jsonArrayThongSoKyThuat.length();

                for (int j = 0; j < dem; j++) {
                    JSONObject jObjectThongSoKyThuat = jsonArrayThongSoKyThuat.getJSONObject(j);
                    int dem2 = jObjectThongSoKyThuat.names().length();
                    for (int k = 0; k < dem2 ; k++) {
                        String tenthongso = jObjectThongSoKyThuat.names().getString(k);

                        ChiTietSanPham chitietsanpham = new ChiTietSanPham();
                        chitietsanpham.setTENCHITIET(tenthongso);
                        chitietsanpham.setGIATRI(jObjectThongSoKyThuat.getString(tenthongso));

                        chiTietSanPhamList.add(chitietsanpham);
                    }
                }
                sanPham.setChiTietSanPhamList(chiTietSanPhamList);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sanPham;
    }

    public ThuongHieu LayThuongHieuTheoMaThuongHieu(int mathuonghieu){
        ThuongHieu thuonghieu = new ThuongHieu();
        List<HashMap<String,String>> attrs = new ArrayList<>();
        String datajson = "";

        String duongdan = MainActivity.SERVER_NAME + "/weblazada/dulieu.php"; // dùng cho máy thật

        HashMap<String,String> hmHam = new HashMap<>(); //truyền tên hàm cần dùng để lấy dữ liệu
        hmHam.put("ham","LayThuongHieuTheoMaThuongHieu");

        HashMap<String,String> hmmathuonghieu = new HashMap<>(); //truyền tên hàm cần dùng để lấy dữ liệu
        hmmathuonghieu.put("mathuonghieu",String.valueOf(mathuonghieu));

        attrs.add(hmHam);
        attrs.add(hmmathuonghieu);


        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);
        // Kết thúc lấy dữ liệu bằng phương thức POST

        downLoadJSON.execute();

        try {
            datajson = downLoadJSON.get();
            JSONObject jsonObject = new JSONObject(datajson);
            JSONArray jsonArrayThuongHieu = jsonObject.getJSONArray("THUONGHIEU");
            int count = jsonArrayThuongHieu.length();
            for (int i = 0; i < count; i++) {
                JSONObject jObjectThuongHieu = jsonArrayThuongHieu.getJSONObject(i);

                thuonghieu.setMATHUONGHIEU(jObjectThuongHieu.getInt("MATHUONGHIEU"));
                thuonghieu.setTENTHUONGHIEU(jObjectThuongHieu.getString("TENTHUONGHIEU"));

                Log.d("thuonghieu", "LayThuongHieuTheoMaThuongHieu: " + thuonghieu.getTENTHUONGHIEU());

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return thuonghieu;
    }
}
