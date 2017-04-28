package com.project.luulinhson.muasamtructuyen.View.TimKiem;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.project.luulinhson.muasamtructuyen.Adapter.AdapterRecyclerviewSanPham;
import com.project.luulinhson.muasamtructuyen.Model.Object.SanPham;
import com.project.luulinhson.muasamtructuyen.Presenter.TimKiem.XuLyTimKiem;
import com.project.luulinhson.muasamtructuyen.R;
import com.project.luulinhson.muasamtructuyen.View.LoadProductPage.ProductActivity;
import com.project.luulinhson.muasamtructuyen.View.Main.MainActivity;

import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Created by Admin on 4/21/2017.
 */

public class TimKiemActivity extends AppCompatActivity implements ViewTimKiem,SearchView.OnQueryTextListener{

    Toolbar toolbarTimKiem;
    RecyclerView recyclerViewTimKiem;
    XuLyTimKiem xuLyTimKiem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);

        toolbarTimKiem = (Toolbar) findViewById(R.id.toolbarTimKiem);
        recyclerViewTimKiem = (RecyclerView) findViewById(R.id.recyclerTimKiem);

        //Khởi tạo toolbar
        toolbarTimKiem.setTitle("");
        setSupportActionBar(toolbarTimKiem);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_black_18dp);

        xuLyTimKiem = new XuLyTimKiem(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tim_kiem,menu);

        MenuItem itSearch = menu.findItem(R.id.itsearchtimkiem);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(itSearch);
        EditText editText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        editText.setHint("Tìm kiếm");
        editText.setHintTextColor(Color.GRAY);
        editText.setTextColor(Color.BLACK);
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);


        return true;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        xuLyTimKiem.LayDanhSachTimKiemTheoTenSP(query,0);
        Log.d("query", "onQueryTextSubmit: " + query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void TimKiemThanhCong(List<SanPham> sanPhamList) {
        AdapterRecyclerviewSanPham adapterRecyclerviewSanPham = new AdapterRecyclerviewSanPham(this,R.layout.custom_recyclerview_san_pham,sanPhamList,true,false);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,false);

        recyclerViewTimKiem.setLayoutManager(layoutManager);
        recyclerViewTimKiem.setAdapter(adapterRecyclerviewSanPham);

        adapterRecyclerviewSanPham.notifyDataSetChanged();
    }

    @Override
    public void TimKiemThatBai() {
        Toasty.error(this,"Không có sản phẩm nào", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(TimKiemActivity.this,MainActivity.class);
                startActivity(intent);
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            Intent intent = new Intent(TimKiemActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return false;
    }

}
