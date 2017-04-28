package com.project.luulinhson.muasamtructuyen.Presenter.MainActivity.MenuDaCap;

import com.facebook.AccessToken;
import com.project.luulinhson.muasamtructuyen.Connection.DownLoadJSON;
import com.project.luulinhson.muasamtructuyen.Model.LogIn_Register.ModelLogin;
import com.project.luulinhson.muasamtructuyen.Model.MainActivity.MenuDaCap.ParseJSONMenu;
import com.project.luulinhson.muasamtructuyen.Model.Object.LoaiSanPham;
import com.project.luulinhson.muasamtructuyen.View.Main.MainActivity;
import com.project.luulinhson.muasamtructuyen.View.Main.ViewXuLyMenuDapCap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Admin on 3/3/2017.
 */

public class XuLyMenuDaCap implements iXulyMenuDaCap {

    ViewXuLyMenuDapCap viewXuLyMenuDapCap;
    public XuLyMenuDaCap(ViewXuLyMenuDapCap viewXuLyMenuDapCap){
        this.viewXuLyMenuDapCap = viewXuLyMenuDapCap;
    }

    @Override
    public void LayDanhSachMenu() {
        String datajson = "";
        List<LoaiSanPham> loaiSanPhamList;
        List<HashMap<String,String>> attrs = new ArrayList<>();

        // Lấy dữ liệu bằng phương thức GET
//        String duongdan = "http://localhost/weblazada/loaisanpham.php?maloaicha=0";
//          String duongdan = "http://10.0.3.2/weblazada/loaisanpham.php?maloaicha=0";
//        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan);
        //Kết thúc lấy dữ liệu bằng phương thức GET


        // Lấy dữ liệu bằng phương thức POST
//        String duongdan = "http://10.0.3.2/weblazada/dulieu.php"; // dùng cho genymotion
        String duongdan = MainActivity.SERVER_NAME + "/weblazada/dulieu.php"; // dùng cho máy thật

        HashMap<String,String> hmHam = new HashMap<>();
        hmHam.put("ham","LayDanhSachMenu");

        HashMap<String,String> hmMaLoaiCha = new HashMap<>();
        hmMaLoaiCha.put("maloaicha","0");

        attrs.add(hmMaLoaiCha);
        attrs.add(hmHam);

        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);
        // Kết thúc lấy dữ liệu bằng phương thức POST

        downLoadJSON.execute();

        try {
            datajson = downLoadJSON.get();
            ParseJSONMenu parseJSONMenu = new ParseJSONMenu();
            loaiSanPhamList = parseJSONMenu.ParseJSONMenu(datajson);

            viewXuLyMenuDapCap.HienThiDanhSachMenu(loaiSanPhamList);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Override
    public AccessToken LayThongTinNguoiDungFacebook() {
        ModelLogin modelLogin = new ModelLogin();
        AccessToken accsessToken = modelLogin.LayTokenFacebookHienTai();
        return accsessToken;
    }
}
