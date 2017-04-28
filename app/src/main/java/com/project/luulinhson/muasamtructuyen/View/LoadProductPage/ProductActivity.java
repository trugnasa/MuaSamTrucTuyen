package com.project.luulinhson.muasamtructuyen.View.LoadProductPage;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.project.luulinhson.muasamtructuyen.Adapter.AdapterRecyclerviewSanPham;
import com.project.luulinhson.muasamtructuyen.Model.Object.ILoadMore;
import com.project.luulinhson.muasamtructuyen.Model.Object.SanPham;
import com.project.luulinhson.muasamtructuyen.Model.Object.LoadMoreScroll;
import com.project.luulinhson.muasamtructuyen.Presenter.DetailProduct.XuLyChiTietSanPham;
import com.project.luulinhson.muasamtructuyen.Presenter.LoadProductPage.XuLyLoadProductPage;
import com.project.luulinhson.muasamtructuyen.R;
import com.project.luulinhson.muasamtructuyen.View.GioHang.GioHangActivity;
import com.project.luulinhson.muasamtructuyen.View.Main.MainActivity;

import java.util.List;

/**
 * Created by Admin on 4/4/2017.
 */

public class ProductActivity extends Fragment implements ViewHienThiDanhSachSanPham,View.OnClickListener,ILoadMore{

    RecyclerView recyclerDanhSachSanPhamTheoMa;
    Button btnLoc,btnSapXep,btnList;
    Boolean gridStatus = true;
    RecyclerView.LayoutManager layoutManager;
    int maloai;
    boolean kiemtra;
    XuLyLoadProductPage xuLyLoadProductPage;
    AdapterRecyclerviewSanPham adapterRecyclerviewSanPham;
    Toolbar toolbarProduct;
    List<SanPham> sanPhamListDefaut;
    ProgressBar progressBarRecyclerview;
    TextView tvSoLuongSanPhamTrongGioHang;
    Menu menu;
    Boolean check = false;
    boolean isOnPause = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_product,container,false);

        recyclerDanhSachSanPhamTheoMa = (RecyclerView) view.findViewById(R.id.recyclerDanhSachSanPhamTheoMa);
        toolbarProduct = (Toolbar) view.findViewById(R.id.toolbarProduct);
        progressBarRecyclerview = (ProgressBar) view.findViewById(R.id.progress_bar_recyclerview);

        btnLoc = (Button) view.findViewById(R.id.btnLoc);
        btnSapXep = (Button) view.findViewById(R.id.btnSapXep);
        btnList = (Button) view.findViewById(R.id.btnList);



        Bundle bundle = getArguments();

        maloai = bundle.getInt("MALOAI",0);
        String tenloai = bundle.getString("TENLOAI");
        kiemtra = bundle.getBoolean("KIEMTRA",false);
        check = bundle.getBoolean("check",false);


        toolbarProduct.setTitle(tenloai);
        toolbarProduct.setTitleTextColor(getResources().getColor(R.color.colorTrang));
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarProduct);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_white_18dp);

        toolbarProduct.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack("TrangChuActivity", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        xuLyLoadProductPage = new XuLyLoadProductPage(this);
        xuLyLoadProductPage.LayDanhSachSanPhamTheoMa(maloai,kiemtra);

        btnList.setOnClickListener(this);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        this.menu = menu;
        inflater.inflate(R.menu.menu_app_bar,menu);

//        MenuItem menuItem = this.menu.findItem(R.id.itgiohang);
//        View view = MenuItemCompat.getActionView(menuItem);
//        tvSoLuongSanPhamTrongGioHang = (TextView) view.findViewById(R.id.tvSoLuongSanPhamTrongGioHang);
//        XuLyChiTietSanPham xuLyChiTietSanPham = new XuLyChiTietSanPham();
//        tvSoLuongSanPhamTrongGioHang.setText(String.valueOf(xuLyChiTietSanPham.DemSoLuongSanPhamTrongGioHang(this)));
//        ImageButton imshopping = (ImageButton) view.findViewById(R.id.imshopping);
//        imshopping.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent iGioHang = new Intent(ProductActivity.this, GioHangActivity.class);
//                startActivity(iGioHang);
//            }
//        });
    }

    @Override
    public void HienThiDanhSachSanPham(List<SanPham> sanPhamList) {
        sanPhamListDefaut = sanPhamList;


        if(gridStatus){
            adapterRecyclerviewSanPham = new AdapterRecyclerviewSanPham(getContext(),R.layout.custom_recyclerview_san_pham,sanPhamListDefaut,kiemtra,check);
            layoutManager = new GridLayoutManager(getContext(),2, LinearLayoutManager.VERTICAL,false);
            Drawable drawable = getResources().getDrawable(R.drawable.list);
            btnList.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
        }else {
            adapterRecyclerviewSanPham = new AdapterRecyclerviewSanPham(getContext(),R.layout.custom_listview_san_pham,sanPhamListDefaut,kiemtra,check);
            layoutManager = new GridLayoutManager(getContext(),1,LinearLayoutManager.VERTICAL,false);
            Drawable drawable2 = getResources().getDrawable(R.drawable.grid);
            btnList.setCompoundDrawablesWithIntrinsicBounds(drawable2,null,null,null);
        }

        recyclerDanhSachSanPhamTheoMa.setLayoutManager(layoutManager);
        recyclerDanhSachSanPhamTheoMa.addOnScrollListener(new LoadMoreScroll(layoutManager,this));
        recyclerDanhSachSanPhamTheoMa.setAdapter(adapterRecyclerviewSanPham);
        adapterRecyclerviewSanPham.notifyDataSetChanged();

    }

    @Override
    public void HienThiLoi() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnList:
                gridStatus = !gridStatus;
                xuLyLoadProductPage.LayDanhSachSanPhamTheoMa(maloai,kiemtra);

                break;
        }
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                Intent intent = new Intent(getContext(), MainActivity.class);
//                startActivity(intent);
//                // app icon in action bar clicked; goto parent activity.
//                getActivity().finish();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(event.getAction() == KeyEvent.ACTION_DOWN){
//            Intent intent = new Intent(getContext(), MainActivity.class);
//            startActivity(intent);
//            getActivity().finish();
//        }
//        return false;
//    }

    @Override
    public void LoadMore(int totalItem) {
        List<SanPham> listLoadMore = xuLyLoadProductPage.LayDanhSachSanPhamTheoLoadMore(maloai,kiemtra,totalItem,progressBarRecyclerview);
        sanPhamListDefaut.addAll(listLoadMore);
        adapterRecyclerviewSanPham.notifyDataSetChanged();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(isOnPause){
//            XuLyChiTietSanPham xuLyChiTietSanPham = new XuLyChiTietSanPham();
//            tvSoLuongSanPhamTrongGioHang.setText(String.valueOf(xuLyChiTietSanPham.DemSoLuongSanPhamTrongGioHang(this)));
//        }
//    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        isOnPause = true;
//    }
}
