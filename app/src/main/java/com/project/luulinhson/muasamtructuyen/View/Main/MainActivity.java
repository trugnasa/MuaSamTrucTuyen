package com.project.luulinhson.muasamtructuyen.View.Main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.project.luulinhson.muasamtructuyen.Adapter.AdapterViewPager;
import com.project.luulinhson.muasamtructuyen.Adapter.ExpandListViewAdapter;
import com.project.luulinhson.muasamtructuyen.Model.LogIn_Register.ModelLogin;
import com.project.luulinhson.muasamtructuyen.Model.Object.LoaiSanPham;
import com.project.luulinhson.muasamtructuyen.Presenter.DetailProduct.XuLyChiTietSanPham;
import com.project.luulinhson.muasamtructuyen.Presenter.MainActivity.MenuDaCap.XuLyMenuDaCap;
import com.project.luulinhson.muasamtructuyen.R;
import com.project.luulinhson.muasamtructuyen.View.GioHang.GioHangActivity;
import com.project.luulinhson.muasamtructuyen.View.LogIn_Register.LogIn_Register_Activity;
import com.project.luulinhson.muasamtructuyen.View.TimKiem.TimKiemActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;
import cn.hugeterry.coordinatortablayout.listener.LoadHeaderImagesListener;
import es.dmoral.toasty.Toasty;

/**
 * Created by Admin on 2/26/2017.
 */

public class MainActivity extends AppCompatActivity implements ViewXuLyMenuDapCap,GoogleApiClient.OnConnectionFailedListener{

    public static final String SERVER_NAME = "http://192.168.1.12"; //192.168.1.7 cho máy thật cắm mạng trực tiếp
                                                                       //10.0.3.2 cho máy ảo Genymotion
//                                                                       public static final String SERVER_NAME = "http://10.0.2.2";
    public static final  String SERVER = "http://192.168.1.12/weblazada";


    ViewPager viewPager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ExpandableListView expandableListView;
    CoordinatorTabLayout mCoordinatorTabLayout;
    int[] mColorArray,mImageArray;

    XuLyMenuDaCap xuLyMenuDaCap;
    String tennguoidung = "";
    AccessToken accessToken;
    Menu menu;
    ModelLogin modelLogin;
    MenuItem menuItemDangNhap;
    MenuItem menuItemDangXuat;
    MenuItem menuItemTimKiem;
    GoogleApiClient mGoogleApiClient;
    GoogleSignInResult googleSignInResult;
    CollapsingToolbarLayout collapsingToolbar;
    String tennv;
    final int ALPHA2 = (int) (255 * .60f);
    TextView tvSoLuongSanPhamTrongGioHang;
    boolean isOnPause = false;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext()); //Nhớ khởi tạo facebook sdk trước khi set giao diện
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // Ánh xạ bên giao diện
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        expandableListView = (ExpandableListView) findViewById(R.id.explistview);
        mCoordinatorTabLayout = (CoordinatorTabLayout) findViewById(R.id.coordinatortablayout);


        //Khởi tạo drawerlayout navigation ,xử lý nút home
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();

        // Xét adater cho tablayout
        AdapterViewPager adapterViewPager = new AdapterViewPager(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);
        adapterViewPager.notifyDataSetChanged();

        //Image array
        mImageArray = new int[]{
                R.drawable.mac,
                R.drawable.phong_canh,
                R.drawable.ios,
                R.drawable.tivi,
                R.drawable.android,
                R.drawable.phong_canh,
                R.drawable.ios,
                R.drawable.tivi,
                R.drawable.android
        };

        mColorArray = new int[]{
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light,
                android.R.color.holo_blue_light
        };


        mCoordinatorTabLayout = (CoordinatorTabLayout) findViewById(R.id.coordinatortablayout);
        mCoordinatorTabLayout.getTabLayout().setTabMode(View.OVER_SCROLL_ALWAYS); // để các tab dàn đều và scoll được
        mCoordinatorTabLayout.getImageView().setImageAlpha(ALPHA2);
        mCoordinatorTabLayout.getActionBar().getThemedContext().setTheme(R.style.toolbar); // set theme cho toolbar

//        mCoordinatorTabLayout.getActionBar().setIcon(R.drawable.logo_lazada);

//        mCoordinatorTabLayout.getTabLayout().setBackgroundResource(R.color.tranDaCam);

        mCoordinatorTabLayout.setTitle("")
//                .setContentScrimColorArray(mColorArray)
                .setImageArray(mImageArray,mColorArray)
                .setupWithViewPager(viewPager);

        mCoordinatorTabLayout.setBackEnable(false); // Có để xuất hiện nút back hay không
        mCoordinatorTabLayout.setLoadHeaderImagesListener(new LoadHeaderImagesListener() {
            @Override
            public void loadHeaderImages(ImageView imageView, TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        loadImages(imageView,SERVER_NAME + "/weblazada/hinhkhuyenmai/noi_bat.jpg");
                        break;
                    case 1:
                        loadImages(imageView,SERVER_NAME + "/weblazada/hinhkhuyenmai/chuongtrinhkhuyenmai.jpg");
                        break;
                    case 2:
                        loadImages(imageView,SERVER_NAME + "/weblazada/hinhkhuyenmai/thuonghieu.jpg");
                        break;
                    case 3:
                        loadImages(imageView,SERVER_NAME + "/weblazada/hinhkhuyenmai/lamdep.jpg");
                        break;
                    case 4:
                        loadImages(imageView,SERVER_NAME + "/weblazada/hinhkhuyenmai/mevabe.jpg");
                        break;
                    case 5:
                        loadImages(imageView,SERVER_NAME + "/weblazada/hinhkhuyenmai/nhacuadoisong.jpg");
                        break;
                    case 6:
                        loadImages(imageView,SERVER_NAME + "/weblazada/hinhkhuyenmai/thethaodulich.jpg");
                        break;
                    case 7:
                        loadImages(imageView,SERVER_NAME + "/weblazada/hinhkhuyenmai/thoitrang.jpg");
                        break;
                    case 8:
                        loadImages(imageView,SERVER_NAME + "/weblazada/hinhkhuyenmai/thuonghieu2.jpg");
                        break;
                    default:
                        break;

                }

            }
        });



        // Load danh sách menu cho menu đa cấp
        xuLyMenuDaCap = new XuLyMenuDaCap(this);
        xuLyMenuDaCap.LayDanhSachMenu();

        // Lấy mGoogleApiClient cho Google +
        modelLogin = new ModelLogin();
        mGoogleApiClient = modelLogin.LayGoogleAPIClient(this,this);

    }

    private void loadImages(ImageView imageView, String url) {
        Picasso.with(MainActivity.this).load(url).into(imageView);
    }

    // Tạo menu bên phải
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app_bar,menu);
        this.menu = menu;

        MenuItem menuItem = menu.findItem(R.id.itgiohang);
        View view = MenuItemCompat.getActionView(menuItem);
        tvSoLuongSanPhamTrongGioHang = (TextView) view.findViewById(R.id.tvSoLuongSanPhamTrongGioHang);
        XuLyChiTietSanPham xuLyChiTietSanPham = new XuLyChiTietSanPham();
        tvSoLuongSanPhamTrongGioHang.setText(String.valueOf(xuLyChiTietSanPham.DemSoLuongSanPhamTrongGioHang(this)));
        ImageButton imshopping = (ImageButton) view.findViewById(R.id.imshopping);
        imshopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioHang = new Intent(MainActivity.this, GioHangActivity.class);
                startActivity(iGioHang);
            }
        });

        tennv = modelLogin.LayDuLieuTrongSharePreference(this);
        Log.d("bua", "onCreateOptionsMenu: " + tennv);
        accessToken = xuLyMenuDaCap.LayThongTinNguoiDungFacebook(); // Lấy accsess token cho Facebook (mã xác nhận)
        googleSignInResult = modelLogin.LayThongTinAccountGoogle(mGoogleApiClient); // Lấy googleSignInResult (mã xác nhận)
        menuItemDangNhap = menu.findItem(R.id.itdangnhap);
        menuItemDangXuat = menu.findItem(R.id.itdangxuat);
        menuItemTimKiem = menu.findItem(R.id.itsearch);

        if(!tennv.equals("")){
            menuItemDangNhap.setTitle(tennv);
        }

        if(accessToken != null){ // Kiểm tra nếu có accsess token thì mới thực hiện lấy dữ liệu người dùng từ facebook
            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {
                        tennguoidung = object.getString("name");
                        menuItemDangNhap.setTitle("Hi!" + tennguoidung); // Thay đổi title của item đăng nhập bằng tên người dùng facebook
                        menuItemDangNhap.setIcon(R.drawable.facebook_circle);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
            Bundle parameter = new Bundle();
            parameter.putString("fields","name");

            graphRequest.setParameters(parameter); // GraphRequest thực chất là một async task(là một tiến trình riêng) nên chú ý không để trong hàm trả về (return)
            graphRequest.executeAsync();
        }

        if(googleSignInResult != null){
            menuItemDangNhap.setTitle("Hi!" + googleSignInResult.getSignInAccount().getDisplayName());
            menuItemDangNhap.setIcon(R.drawable.google_circle);
        }

        if(accessToken != null || googleSignInResult != null || !tennv.equals("")){ // Kiểm tra nếu có accsess token thì có nghĩa là có người đang đăng nhập => hiển thị item đăng xuất ra
            menuItemDangXuat.setVisible(true);
        }
        return true;
    }

    // Xét sự kiện click cho từng item menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.itdangnhap:
                if(accessToken == null && googleSignInResult == null && tennv.equals("")){ // Kiểm tra nếu không có accsess token(tức là chưa có người đăng nhập facebook) thì mới cho chuyển intent qua trang đăng nhập
                    Intent iDangNhap = new Intent(MainActivity.this, LogIn_Register_Activity.class);
                    startActivity(iDangNhap);
                }
                break;

            case R.id.itdangxuat:
                if (accessToken != null){// Kiểm tra nếu có accsess token(tức là có người đăng nhập facebook) thì mới cho đăng xuất
                    LoginManager.getInstance().logOut();
//                    Toast.makeText(MainActivity.this," Đã đăng xuất khỏi Facebook!",Toast.LENGTH_SHORT).show();
                    Toasty.error(MainActivity.this,"Đã đăng xuất khỏi Facebook",Toast.LENGTH_SHORT).show();
                    this.menu.clear(); // Xóa menu cũ đi
                    this.onCreateOptionsMenu(this.menu); // refresh lại menu mới
                }
                if(googleSignInResult != null){
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient);
//                    Toast.makeText(MainActivity.this," Đã đăng xuất khỏi Google+!",Toast.LENGTH_SHORT).show();
                    Toasty.error(MainActivity.this,"Đã đăng xuất khỏi Google+",Toast.LENGTH_SHORT).show();
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }

                if(!tennv.equals("")){
                    modelLogin.CapNhatSharePreference(this,"");
//                    Toast.makeText(this,"Đã đăng xuất khỏi tài khoản Lazada",Toast.LENGTH_SHORT).show();
                    Toasty.error(MainActivity.this,"Đã đăng xuất khỏi tài khoản Lazada",Toast.LENGTH_SHORT).show();
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }
                break;
            case R.id.itsearch:
                Intent iTimKiem = new Intent(MainActivity.this, TimKiemActivity.class);
                startActivity(iTimKiem);
                break;


        }
        if(drawerToggle.onOptionsItemSelected(item)){ // code này để khi ấn vào menu home mới hiện ra menu đa cấp
            return true;
        }
        return true;
    }

    // Xét sự kiện khi click vào nút back
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(event.getAction() == KeyEvent.ACTION_DOWN){
            new AlertDialog.Builder(MainActivity.this)
                    .setMessage("Bạn có muốn thoát khỏi ứng dụng?")
                    .setCancelable(false)
                    .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    })
                    .setNegativeButton("Không,Tôi không muốn", null)
                    .show();
        }
        return false;
    }

    // Override từ sự kiện ViewXuLyMenuDaCap
    @Override
    public void HienThiDanhSachMenu(List<LoaiSanPham> loaiSanPhamList) {
        ExpandListViewAdapter expandListViewAdapter = new ExpandListViewAdapter(this,loaiSanPhamList);
        expandableListView.setAdapter(expandListViewAdapter);
        expandListViewAdapter.notifyDataSetChanged();
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
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
