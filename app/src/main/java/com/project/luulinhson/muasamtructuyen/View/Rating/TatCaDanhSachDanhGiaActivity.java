package com.project.luulinhson.muasamtructuyen.View.Rating;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.project.luulinhson.muasamtructuyen.Adapter.AdapterRecyclerviewDanhGia;
import com.project.luulinhson.muasamtructuyen.Model.Object.DanhGia;
import com.project.luulinhson.muasamtructuyen.Model.Object.ILoadMore;
import com.project.luulinhson.muasamtructuyen.Model.Object.LoadMoreScroll;
import com.project.luulinhson.muasamtructuyen.Presenter.Rating.XuLyRating;
import com.project.luulinhson.muasamtructuyen.R;
import com.project.luulinhson.muasamtructuyen.View.LoadProductPage.ViewHienThiDanhSachSanPham;
import com.project.luulinhson.muasamtructuyen.View.Rating.ViewRating;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 4/13/2017.
 */

public class TatCaDanhSachDanhGiaActivity extends AppCompatActivity implements ViewRating,ILoadMore{

    Toolbar toolbar;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    List<DanhGia> tatCaDanhGia;
    XuLyRating xuLyRating;
    int masp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_danh_gia);

        toolbar = (Toolbar) findViewById(R.id.toolbardanhsachdanhgia);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerTatCaDanhSachDanhGia);
        progressBar = (ProgressBar) findViewById(R.id.progressbardanhgia);

        tatCaDanhGia = new ArrayList<>();
        masp = getIntent().getIntExtra("masp",0);
        xuLyRating = new XuLyRating(this);
        xuLyRating.LayDanhSachDanhGia(masp,0,progressBar);

        //Khởi tạo toolbar
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_white_18dp);

    }

    @Override
    public void ThemDanhGiaThanhCong() {

    }

    @Override
    public void ThemDanhGiaThatBai() {

    }

    @Override
    public void HienThiDanhSachDanhGia(List<DanhGia> danhGiaList) {

        tatCaDanhGia.addAll(danhGiaList);

        AdapterRecyclerviewDanhGia adapterRecyclerviewDanhGia = new AdapterRecyclerviewDanhGia(this,tatCaDanhGia,0);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterRecyclerviewDanhGia);
        recyclerView.addOnScrollListener(new LoadMoreScroll(layoutManager,this));
        adapterRecyclerviewDanhGia.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void LoadMore(int totalItem) {
        xuLyRating.LayDanhSachDanhGia(masp,totalItem,progressBar);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(event.getAction() == KeyEvent.ACTION_DOWN){
            finish();
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app_bar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                // app icon in action bar clicked; goto parent activity.
//                Intent iProduct = new Intent(DetailProducActivity.this,ProductActivity.class);
//                startActivity(iProduct);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
