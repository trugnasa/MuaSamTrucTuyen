package com.project.luulinhson.muasamtructuyen.View.GioHang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.project.luulinhson.muasamtructuyen.Adapter.AdapterRecyclerViewGioHang;
import com.project.luulinhson.muasamtructuyen.Model.Object.SanPham;
import com.project.luulinhson.muasamtructuyen.Presenter.GioHang.XuLyGioHang;
import com.project.luulinhson.muasamtructuyen.R;
import com.project.luulinhson.muasamtructuyen.View.ThanhToan.ThanhToanActivity;

import java.util.List;

/**
 * Created by Admin on 4/15/2017.
 */

public class GioHangActivity extends AppCompatActivity implements ViewGioHang,View.OnClickListener{

    RecyclerView recyclerGioHang;
    XuLyGioHang xuLyGioHang;
    Button btnthanhtoan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        recyclerGioHang = (RecyclerView) findViewById(R.id.recyclerGioHang);
        btnthanhtoan = (Button) findViewById(R.id.btnthanhtoan);

        xuLyGioHang = new XuLyGioHang(this);
        xuLyGioHang.LayDanhSachGioHang(this);
        btnthanhtoan.setOnClickListener(this);

    }

    @Override
    public void HienThiDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList) {

        AdapterRecyclerViewGioHang adapterRecyclerViewGioHang = new AdapterRecyclerViewGioHang(this,sanPhamList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerGioHang.setLayoutManager(layoutManager);
        recyclerGioHang.setAdapter(adapterRecyclerViewGioHang);

        adapterRecyclerViewGioHang.notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnthanhtoan:
                Intent intent = new Intent(GioHangActivity.this, ThanhToanActivity.class);
                startActivity(intent);
                break;
        }
    }
}
