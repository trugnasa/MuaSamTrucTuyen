package com.project.luulinhson.muasamtructuyen.Model.GioHang;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin on 4/14/2017.
 */

public class DatabaseSQLiteGioHang extends SQLiteOpenHelper {

    public static final String TB_GIOHANG = "GIOHANG";
    public static final String TB_GIOHANG_MASP = "MASP";
    public static final String TB_GIOHANG_TENSP = "TENSP";
    public static final String TB_GIOHANG_GIASP = "GIASP";
    public static final String TB_GIOHANG_HINHANH = "HINHANH";
    public static final String TB_GIOHANG_SOLUONG = "SOLUONG";
    public static final String TB_GIOHANG_SOLUONGTONKHO = "SOLUONGTONKHO";

    public static final String TB_YEUTHICH = "YEUTHICH";
    public static final String TB_YEUTHICH_MASP = "MASP";
    public static final String TB_YEUTHICH_TENSP = "TENSP";
    public static final String TB_YEUTHICH_GIASP = "GIASP";
    public static final String TB_YEUTHICH_HINHANH = "HINHANH";



    public DatabaseSQLiteGioHang(Context context) {
        super(context, "DuLieuGioHang", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String truyvangiohang = "CREATE TABLE " + TB_GIOHANG + " ("
                 + TB_GIOHANG_MASP + " INTERGER PRIMARY KEY,"
                 + TB_GIOHANG_TENSP + " TEXT,"
                 + TB_GIOHANG_GIASP + " REAL,"
                 + TB_GIOHANG_SOLUONGTONKHO + " INTEGER,"
                 + TB_GIOHANG_SOLUONG + " INTEGER,"
                 + TB_GIOHANG_HINHANH + " BLOD);";

        String truyvanyeuthich = "CREATE TABLE " + TB_YEUTHICH + " ("
                + TB_YEUTHICH_MASP + " INTERGER PRIMARY KEY,"
                + TB_YEUTHICH_TENSP + " TEXT,"
                + TB_YEUTHICH_GIASP + " REAL,"
                + TB_YEUTHICH_HINHANH + " BLOD);";

        db.execSQL(truyvangiohang);
        db.execSQL(truyvanyeuthich);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < newVersion){
            db.execSQL("drop table if exists " + TB_GIOHANG);
            db.execSQL("drop table if exists " + TB_YEUTHICH);
            onCreate(db);
        }

    }
}
