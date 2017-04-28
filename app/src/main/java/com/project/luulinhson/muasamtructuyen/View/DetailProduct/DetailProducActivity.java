package com.project.luulinhson.muasamtructuyen.View.DetailProduct;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.luulinhson.muasamtructuyen.Adapter.AdapterRecyclerviewDanhGia;
import com.project.luulinhson.muasamtructuyen.Adapter.AdapterViewPagerSlideHinhAnhChiTiet;
import com.project.luulinhson.muasamtructuyen.Model.Object.ChiTietSanPham;
import com.project.luulinhson.muasamtructuyen.Model.Object.DanhGia;
import com.project.luulinhson.muasamtructuyen.Model.Object.SanPham;
import com.project.luulinhson.muasamtructuyen.Presenter.DetailProduct.XuLyChiTietSanPham;
import com.project.luulinhson.muasamtructuyen.R;
import com.project.luulinhson.muasamtructuyen.View.GioHang.GioHangActivity;
import com.project.luulinhson.muasamtructuyen.View.LoadProductPage.ProductActivity;
import com.project.luulinhson.muasamtructuyen.View.Main.MainActivity;
import com.project.luulinhson.muasamtructuyen.View.Rating.RatingActivity;
import com.project.luulinhson.muasamtructuyen.View.Rating.TatCaDanhSachDanhGiaActivity;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Created by Admin on 4/8/2017.
 */

public class DetailProducActivity extends AppCompatActivity implements ViewChiTietSanPham,ViewPager.OnPageChangeListener,View.OnClickListener{

    AdapterViewPagerSlideHinhAnhChiTiet adapterViewPagerSlideHinhAnhChiTiet;
    ViewPager viewPager;
    XuLyChiTietSanPham xuLyChiTietSanPham;
    TextView[] textViews;
    LinearLayout lnDots;
    List<Fragment> fragmentList;
    TextView tvTenSanPhamDetail,tvGiaSanPhamDetail;
    Toolbar toolbarDetail;
    TextView tvchitietsanpham,tvtennhanvien;
    ImageView imkeyboard;
    boolean checkImkeyboard = false;
    LinearLayout lnthongsokythuat;
    Button btnvietdanhgia,btnxemtatcanhanxet,btngiohang;
    int masp;
    RecyclerView recyclerdanhgia;
    SanPham sanphamgiohang;
    TextView tvSoLuongSanPhamTrongGioHang;
    int mathuonghieu;
    String tenthuonghieu;
    boolean kiemtra;
    boolean check = false;
    boolean isOnPause = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        viewPager = (ViewPager) findViewById(R.id.viewpagerdetail);
        lnDots = (LinearLayout) findViewById(R.id.lnDots);
        tvTenSanPhamDetail = (TextView) findViewById(R.id.tvTenSanPhamDetail);
        tvGiaSanPhamDetail = (TextView) findViewById(R.id.tvGiaSanPhamDetail);
        toolbarDetail = (Toolbar) findViewById(R.id.toolbar_detail);
        tvchitietsanpham = (TextView) findViewById(R.id.tvchitietsanpham);
        tvtennhanvien = (TextView) findViewById(R.id.tvtennhanvien);
        imkeyboard = (ImageView) findViewById(R.id.imkeyboard);
        lnthongsokythuat = (LinearLayout) findViewById(R.id.lnthongsokythuat);
        btnvietdanhgia = (Button) findViewById(R.id.btnvietdanhgia);
        recyclerdanhgia = (RecyclerView) findViewById(R.id.rycyclerdanhgia);
        btnxemtatcanhanxet = (Button) findViewById(R.id.btnxemtatcanhanxet);
        btngiohang = (Button) findViewById(R.id.btngiohang);


        btnvietdanhgia.setOnClickListener(this);
        btnxemtatcanhanxet.setOnClickListener(this);
        btngiohang.setOnClickListener(this);

        //Khởi tạo toolbar
        toolbarDetail.setTitle("");
        setSupportActionBar(toolbarDetail);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_white_18dp);

        // Lấy masp truyền qua từ adapter sản phẩm
        Intent intent = getIntent();
        SanPham sanpham = (SanPham) intent.getSerializableExtra("sanpham");
        int masp = sanpham.getMASP();
        mathuonghieu = sanpham.getMATHUONGHIEU();
        Log.d("mathuonghieu", "onCreate: " + mathuonghieu + "");
        check = intent.getBooleanExtra("check",false);

        kiemtra = intent.getBooleanExtra("kiemtra",false);


        // khởi tạo và gọi hàm lấy chi tiết sản phẩm
        xuLyChiTietSanPham = new XuLyChiTietSanPham(this);
        xuLyChiTietSanPham.LayChiTietSanPham(masp);
        xuLyChiTietSanPham.LayDanhSachDanhGia(masp,0);
        tenthuonghieu = xuLyChiTietSanPham.LayThuongHieu(mathuonghieu);

    }

    @Override
    public void HienThiChiTietSanPham(final SanPham sanPham) {
        masp = sanPham.getMASP();

        sanphamgiohang = sanPham;
        sanphamgiohang.setSOLUONGTONKHO(sanPham.getSOLUONG());

        tvTenSanPhamDetail.setText(sanPham.getTENSP());
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGIA());
        tvGiaSanPhamDetail.setText(String.valueOf(gia) + " VND");
        tvchitietsanpham.setText(sanPham.getTHONGTIN().substring(0,100));
        tvtennhanvien.setText(sanPham.getTENNV());

        int count = sanPham.getTHONGTIN().length();

        if(count < 100){
           imkeyboard.setVisibility(View.GONE);
        }else {
            imkeyboard.setVisibility(View.VISIBLE);
            imkeyboard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkImkeyboard = !checkImkeyboard;
                    if(checkImkeyboard){
                        tvchitietsanpham.setText(sanPham.getTHONGTIN());
                        imkeyboard.setImageDrawable(getIdDrawable(R.drawable.ic_keyboard_arrow_up_black_18dp));
                        lnthongsokythuat.setVisibility(View.VISIBLE);
                        HienThiThongSoKyThuat(sanPham);
                    }else {
                        tvchitietsanpham.setText(sanPham.getTHONGTIN().substring(0,100));
                        imkeyboard.setImageDrawable(getIdDrawable(R.drawable.ic_keyboard_arrow_down_black_18dp));
                        lnthongsokythuat.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    private void HienThiThongSoKyThuat(SanPham sanPham){
        List<ChiTietSanPham> chiTietSanPhamList = sanPham.getChiTietSanPhamList();
        lnthongsokythuat.removeAllViews();
        int count = chiTietSanPhamList.size();
        for (int i = 0; i < count; i++) {
            LinearLayout lnChiTiet = new LinearLayout(this);
            lnChiTiet.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lnChiTiet.setOrientation(LinearLayout.HORIZONTAL);

            TextView tvtenthongso = new TextView(this);
            tvtenthongso.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));
            tvtenthongso.setText(chiTietSanPhamList.get(i).getTENCHITIET());
            tvtenthongso.setTextColor(getIdColor(R.color.colorDen));

            TextView tvgiatrithongso = new TextView(this);
            tvgiatrithongso.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));
            tvgiatrithongso.setText(chiTietSanPhamList.get(i).getGIATRI());

            lnChiTiet.addView(tvtenthongso);
            lnChiTiet.addView(tvgiatrithongso);

            lnthongsokythuat.addView(lnChiTiet);
        }
    }

    @Override
    public void HienThiSlideHinhAnh(String[] linkHinhAnh) {

        fragmentList = new ArrayList<>();
        int count = linkHinhAnh.length;
        for (int i = 0; i < count; i++) {
            FragmentSlide fragmentSilde = new FragmentSlide();
            Bundle bundle = new Bundle();
            bundle.putString("linkhinh", MainActivity.SERVER_NAME + "/weblazada/" + linkHinhAnh[i]);
            fragmentSilde.setArguments(bundle);

            fragmentList.add(fragmentSilde);
        }

        adapterViewPagerSlideHinhAnhChiTiet = new AdapterViewPagerSlideHinhAnhChiTiet(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapterViewPagerSlideHinhAnhChiTiet);
        adapterViewPagerSlideHinhAnhChiTiet.notifyDataSetChanged();

        DotsSlide(0);
        viewPager.addOnPageChangeListener(this);

    }

    @Override
    public void HienThiDanhSachDanhGia(List<DanhGia> danhGiaList) {
        AdapterRecyclerviewDanhGia adapterRecyclerviewDanhGia = new AdapterRecyclerviewDanhGia(this,danhGiaList,3);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recyclerdanhgia.setLayoutManager(layoutManager);
        recyclerdanhgia.setAdapter(adapterRecyclerviewDanhGia);

        adapterRecyclerviewDanhGia.notifyDataSetChanged();
    }

    @Override
    public void ThemGioHangThanhCong() {
        Toasty.success(this,"Thêm thành công", Toast.LENGTH_SHORT).show();
        tvSoLuongSanPhamTrongGioHang.setText(String.valueOf(xuLyChiTietSanPham.DemSoLuongSanPhamTrongGioHang(this)));
    }

    @Override
    public void ThemGioHangThatBai() {
        Toasty.error(this,"Sản phẩm đã được thêm", Toast.LENGTH_SHORT).show();
    }

    private void DotsSlide(int vitrihientai){
        int dodailist = fragmentList.size();
        textViews = new TextView[dodailist];

        lnDots.removeAllViews();

        for (int i = 0; i < dodailist; i++) {
            textViews[i] = new TextView(this);
            textViews[i].setText(Html.fromHtml("&#8226;"));
            textViews[i].setTextSize(40);
            textViews[i].setTextColor(getIdColor(R.color.colorXam));

            lnDots.addView(textViews[i]);

        }

        textViews[vitrihientai].setTextColor(getIdColor(R.color.colorDaCam));

    }

    private Drawable getIdDrawable(int idDrawable){

        Drawable drawable;
        if(Build.VERSION.SDK_INT > 21){
            drawable = ContextCompat.getDrawable(this,idDrawable);
        }else {
            drawable = getResources().getDrawable(idDrawable);
        }

        return drawable;

    }

    private int getIdColor(int idcolor){

        int color = 0;
        if(Build.VERSION.SDK_INT > 21){
            color = ContextCompat.getColor(this,idcolor);
        }else {
            color = getResources().getColor(idcolor);
        }

        return color;

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        DotsSlide(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app_bar,menu);

        MenuItem menuItem = menu.findItem(R.id.itgiohang);
        View view = MenuItemCompat.getActionView(menuItem);
        tvSoLuongSanPhamTrongGioHang = (TextView) view.findViewById(R.id.tvSoLuongSanPhamTrongGioHang);

        tvSoLuongSanPhamTrongGioHang.setText(String.valueOf(xuLyChiTietSanPham.DemSoLuongSanPhamTrongGioHang(this)));

        ImageButton imshopping = (ImageButton) view.findViewById(R.id.imshopping);
        imshopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioHang = new Intent(DetailProducActivity.this, GioHangActivity.class);
                startActivity(iGioHang);
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                if(check){
                    Intent iProduct = new Intent(DetailProducActivity.this,ProductActivity.class);
                    iProduct.putExtra("MALOAI",mathuonghieu);
                    iProduct.putExtra("TENLOAI",tenthuonghieu);
                    iProduct.putExtra("KIEMTRA",kiemtra);
                    startActivity(iProduct);
                    this.finish();
                }else {
                    Intent iProduct = new Intent(DetailProducActivity.this,MainActivity.class);
                    this.finish();
                }

                // app icon in action bar clicked; goto parent activity.

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(event.getAction() == KeyEvent.ACTION_DOWN){
            if(check){
                Intent iProduct = new Intent(DetailProducActivity.this,ProductActivity.class);
                iProduct.putExtra("MALOAI",mathuonghieu);
                iProduct.putExtra("TENLOAI",tenthuonghieu);
                iProduct.putExtra("KIEMTRA",kiemtra);
                startActivity(iProduct);
                this.finish();
            }else {
                Intent iProduct = new Intent(DetailProducActivity.this,MainActivity.class);
                this.finish();
            }

        }
        return false;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnvietdanhgia:
                Intent iDanhGia = new Intent(this, RatingActivity.class);
                iDanhGia.putExtra("masp",masp);
                startActivity(iDanhGia);
                break;
            case R.id.btnxemtatcanhanxet:
                Intent intent = new Intent(this,TatCaDanhSachDanhGiaActivity.class);
                intent.putExtra("masp",masp);
                startActivity(intent);
                break;
            case R.id.btngiohang:
                Fragment fragment = fragmentList.get(0);
                View view = fragment.getView();
                ImageView imageView = (ImageView) view.findViewById(R.id.imSlide);
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                byte[] hinhsanpham = byteArrayOutputStream.toByteArray();

                sanphamgiohang.setSOLUONG(1);
                sanphamgiohang.setHinhgiohang(hinhsanpham);
                xuLyChiTietSanPham.ThemGioHang(this,sanphamgiohang);

                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isOnPause){
            XuLyChiTietSanPham xuLyChiTietSanPham = new XuLyChiTietSanPham();
            tvSoLuongSanPhamTrongGioHang.setText(String.valueOf(xuLyChiTietSanPham.DemSoLuongSanPhamTrongGioHang(this)));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        isOnPause = true;
    }
}
