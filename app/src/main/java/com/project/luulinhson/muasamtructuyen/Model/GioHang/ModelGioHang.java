package com.project.luulinhson.muasamtructuyen.Model.GioHang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.project.luulinhson.muasamtructuyen.Model.Object.SanPham;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 4/14/2017.
 */

public class ModelGioHang {

    SQLiteDatabase database;

    public void MoKetNoiDatabase(Context context){
        DatabaseSQLiteGioHang databaseSQLiteGioHang = new DatabaseSQLiteGioHang(context);
        database = databaseSQLiteGioHang.getWritableDatabase();
    }

    public boolean ThemGioHang(SanPham sanPham){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseSQLiteGioHang.TB_GIOHANG_MASP,sanPham.getMASP());
        contentValues.put(DatabaseSQLiteGioHang.TB_GIOHANG_TENSP,sanPham.getTENSP());
        contentValues.put(DatabaseSQLiteGioHang.TB_GIOHANG_GIASP,sanPham.getGIA());
        contentValues.put(DatabaseSQLiteGioHang.TB_GIOHANG_HINHANH,sanPham.getHinhgiohang());
        contentValues.put(DatabaseSQLiteGioHang.TB_GIOHANG_SOLUONG,sanPham.getSOLUONG());
        contentValues.put(DatabaseSQLiteGioHang.TB_GIOHANG_SOLUONGTONKHO,sanPham.getSOLUONGTONKHO());

        long trave = database.insert(DatabaseSQLiteGioHang.TB_GIOHANG,null,contentValues);

        if(trave > 0){
            return true;
        }else {
            return false;
        }
    }

    public List<SanPham> LayTatCaSanPhamTrongGioHang(){
        List<SanPham> sanPhamList = new ArrayList<>();

        String truyvan = "SELECT * FROM " + DatabaseSQLiteGioHang.TB_GIOHANG;
        Cursor cursor = database.rawQuery(truyvan,null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int masp = cursor.getInt(cursor.getColumnIndex(DatabaseSQLiteGioHang.TB_GIOHANG_MASP));
            String tensp = cursor.getString(cursor.getColumnIndex(DatabaseSQLiteGioHang.TB_GIOHANG_TENSP));
            int giasp = cursor.getInt(cursor.getColumnIndex(DatabaseSQLiteGioHang.TB_GIOHANG_GIASP));
            byte[] hinhanh = cursor.getBlob(cursor.getColumnIndex(DatabaseSQLiteGioHang.TB_GIOHANG_HINHANH));
            int soluong = cursor.getInt(cursor.getColumnIndex(DatabaseSQLiteGioHang.TB_GIOHANG_SOLUONG));
            int soluongtonkho = cursor.getInt(cursor.getColumnIndex(DatabaseSQLiteGioHang.TB_GIOHANG_SOLUONGTONKHO));

            SanPham sanPham = new SanPham();
            sanPham.setMASP(masp);
            sanPham.setTENSP(tensp);
            sanPham.setGIA(giasp);
            sanPham.setHinhgiohang(hinhanh);
            sanPham.setSOLUONG(soluong);
            sanPham.setSOLUONGTONKHO(soluongtonkho);

            sanPhamList.add(sanPham);

            cursor.moveToNext();
        }

        return sanPhamList;
    }

    public boolean XoaSanPhamTheoMaSP(int masp){

        long kiemtra = database.delete(DatabaseSQLiteGioHang.TB_GIOHANG,DatabaseSQLiteGioHang.TB_GIOHANG_MASP + "=" + masp,null);
        if(kiemtra > 0){
            return true;
        }else {
            return false;
        }
    }

    public boolean CapNhatSoLuongSanPhamTrongGioHang(int masp,int soluong){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseSQLiteGioHang.TB_GIOHANG_SOLUONG,soluong);

        int id = database.update(DatabaseSQLiteGioHang.TB_GIOHANG,contentValues,DatabaseSQLiteGioHang.TB_GIOHANG_MASP + " = " + masp,null);
        Log.d("id", "CapNhatSoLuongSanPhamTrongGioHang: " + id + "");
        if(id > 0){
            return true;
        }else {
            return false;
        }
    }
}
