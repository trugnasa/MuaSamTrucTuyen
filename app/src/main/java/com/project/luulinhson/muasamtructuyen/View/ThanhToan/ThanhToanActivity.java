package com.project.luulinhson.muasamtructuyen.View.ThanhToan;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.luulinhson.muasamtructuyen.Model.Object.ChiTietHoaDon;
import com.project.luulinhson.muasamtructuyen.Model.Object.HoaDon;
import com.project.luulinhson.muasamtructuyen.Model.Object.SanPham;
import com.project.luulinhson.muasamtructuyen.Presenter.ThanhToan.XuLyThanhToan;
import com.project.luulinhson.muasamtructuyen.R;
import com.project.luulinhson.muasamtructuyen.View.LoadProductPage.ProductActivity;
import com.project.luulinhson.muasamtructuyen.View.Main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Created by Admin on 4/18/2017.
 */

public class ThanhToanActivity extends AppCompatActivity implements View.OnClickListener,ViewThanhToan{

    Toolbar toolbarThanhToan;
    EditText edHoTenThanhToan,edDiaChiThanhToan,edSoDienThoaiThanhToan;
    ImageView imCOD,imViSa;
    CheckBox checkBoxThanhToan;
    Button btnThanhToan;
    XuLyThanhToan xuLyThanhToan;
    List<ChiTietHoaDon> chiTietHoaDonList;
    TextView tvCOD,tvViSa;
    int chonHinhThucGiaoHang = 0;
    int ALPHA = (int) (255 * .60f);
    int ALPHA2 = (int) (255 * 100f);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtoan);

        //Ánh xạ giao diện
        edHoTenThanhToan = (EditText) findViewById(R.id.tvHoTenThanhToan);
        edDiaChiThanhToan = (EditText) findViewById(R.id.tvDiaChiThanhToan);
        edSoDienThoaiThanhToan = (EditText) findViewById(R.id.tvSoDienThoaiThanhToan);
        imCOD = (ImageView) findViewById(R.id.imCOD);
        imViSa = (ImageView) findViewById(R.id.imViSa);
        checkBoxThanhToan = (CheckBox) findViewById(R.id.checkBoxThanhToan);
        btnThanhToan = (Button) findViewById(R.id.btnThanhToan);
        tvCOD = (TextView) findViewById(R.id.tvCOD);
        tvViSa = (TextView) findViewById(R.id.tvViSa);

        xuLyThanhToan = new XuLyThanhToan(this,this);
        xuLyThanhToan.LayDanhSachSanPhamTrongGioHang();

        //Khởi tạo toolbar
        toolbarThanhToan = (Toolbar) findViewById(R.id.toolbarThanhToan);
        toolbarThanhToan.setTitle("Bảo mật thanh toán");
        setSupportActionBar(toolbarThanhToan);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_white_18dp);

        //set sự kiện
        btnThanhToan.setOnClickListener(this);
        imCOD.setOnClickListener(this);
        imViSa.setOnClickListener(this);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnThanhToan:
                String hovaten = edHoTenThanhToan.getText().toString();
                String diachi = edDiaChiThanhToan.getText().toString();
                String sodienthoai = edSoDienThoaiThanhToan.getText().toString();

                if(hovaten.trim().length() > 0 && diachi.trim().length() > 0 && sodienthoai.trim().length() > 0){
                    if(checkBoxThanhToan.isChecked()){
                        HoaDon hoaDon = new HoaDon();
                        hoaDon.setTENNGUOINHAN(hovaten);
                        hoaDon.setDIACHI(diachi);
                        hoaDon.setSODT(sodienthoai);
                        hoaDon.setChiTietHoaDonList(chiTietHoaDonList);
                        hoaDon.setCHUYENKHOAN(chonHinhThucGiaoHang);
                        xuLyThanhToan.ThemHoaDon(hoaDon);


                    }else {
                        Toasty.error(ThanhToanActivity.this,"Bạn chưa xác nhận thông tin là chính xác", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toasty.error(ThanhToanActivity.this,"Bạn chưa điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.imCOD:
                LuaChonHinhThucGiaoHang(tvCOD,tvViSa);
                chonHinhThucGiaoHang = 0;
                break;
            case R.id.imViSa:
                LuaChonHinhThucGiaoHang(tvViSa,tvCOD);
                chonHinhThucGiaoHang = 1;
                break;
        }

    }

    private void LuaChonHinhThucGiaoHang(TextView tvduocchon,TextView tvhuybo){
        tvduocchon.setTextColor(getIdColor(R.color.colorXanhLaMaBarclay));
        tvhuybo.setTextColor(getIdColor(R.color.colorXam));
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
    public void ThanhToanThanhCong() {
        Toasty.success(ThanhToanActivity.this,"Đặt hàng thành công", Toast.LENGTH_SHORT).show();

        Intent iTrangChu = new Intent(ThanhToanActivity.this,MainActivity.class);
        startActivity(iTrangChu);
    }

    @Override
    public void ThanhToanThatBai() {
        Toasty.error(ThanhToanActivity.this,"Đã có lỗi xảy ra trong quá trình đặt hàng", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList) {
        chiTietHoaDonList = new ArrayList<>();
        int count = sanPhamList.size();
        for (int i = 0; i < count ; i++) {
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
            chiTietHoaDon.setMASP(sanPhamList.get(i).getMASP());
            chiTietHoaDon.setSOLUONG(sanPhamList.get(i).getSOLUONG());

            chiTietHoaDonList.add(chiTietHoaDon);
        }

    }
}
