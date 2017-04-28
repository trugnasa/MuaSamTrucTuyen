package com.project.luulinhson.muasamtructuyen.View.LogIn_Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.project.luulinhson.muasamtructuyen.Adapter.AdapterViewPagerDangNhap;
import com.project.luulinhson.muasamtructuyen.R;
import com.project.luulinhson.muasamtructuyen.View.Main.MainActivity;

/**
 * Created by Admin on 3/12/2017.
 */

public class LogIn_Register_Activity extends AppCompatActivity {

    TabLayout tabLayoutDangNhap;
    ViewPager viewPagerDangNhap;
    Toolbar toolbarDangNhap;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        tabLayoutDangNhap = (TabLayout) findViewById(R.id.tablayoutdagnhap);
        viewPagerDangNhap = (ViewPager) findViewById(R.id.viewpagerdangnhap);
        toolbarDangNhap = (Toolbar) findViewById(R.id.toolbardangnhap);

        //Khởi tạo toolbar
        toolbarDangNhap.setTitle("Đăng nhập/Đăng ký");
        setSupportActionBar(toolbarDangNhap);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Xét màu cho nút back
//        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_close_white_18dp);
//        //ic_menu_close_clear_cancel
//        //abc_ic_ab_back_material
//        upArrow.setColorFilter(getResources().getColor(R.color.colorTrang), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_18dp);



        // Xét adater cho tablayout
        AdapterViewPagerDangNhap adapterViewPagerDangNhap = new AdapterViewPagerDangNhap(getSupportFragmentManager());
        viewPagerDangNhap.setAdapter(adapterViewPagerDangNhap);
        adapterViewPagerDangNhap.notifyDataSetChanged();

        tabLayoutDangNhap.setupWithViewPager(viewPagerDangNhap);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                // app icon in action bar clicked; goto parent activity.
                Intent iTrangChu = new Intent(LogIn_Register_Activity.this, MainActivity.class);
                startActivity(iTrangChu);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Xét sự kiện khi click vào nút back
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(event.getAction() == KeyEvent.ACTION_DOWN){
            Intent iTrangChu = new Intent(LogIn_Register_Activity.this, MainActivity.class);
            startActivity(iTrangChu);
        }
        return false;
    }
}
