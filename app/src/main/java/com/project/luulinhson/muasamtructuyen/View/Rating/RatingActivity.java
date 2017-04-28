package com.project.luulinhson.muasamtructuyen.View.Rating;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.project.luulinhson.muasamtructuyen.Model.Object.DanhGia;
import com.project.luulinhson.muasamtructuyen.Presenter.Rating.XuLyRating;
import com.project.luulinhson.muasamtructuyen.R;

import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Created by Admin on 4/12/2017.
 */

public class RatingActivity extends AppCompatActivity implements View.OnClickListener,RatingBar.OnRatingBarChangeListener,ViewRating{

    RatingBar ratingbar;
    TextInputLayout textinputtieude,textinputnoidung;
    EditText edtieude,ednoidung;
    Button btnnhanxet;
    int masp = 0;
    int sosao = 0;
    XuLyRating xuLyRating;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        ratingbar = (RatingBar) findViewById(R.id.rating);
        textinputnoidung = (TextInputLayout) findViewById(R.id.textinputnoidung);
        textinputtieude = (TextInputLayout) findViewById(R.id.textinputtieude);
        edtieude = (EditText) findViewById(R.id.edtieude);
        ednoidung = (EditText) findViewById(R.id.ednoidung);
        btnnhanxet = (Button) findViewById(R.id.btnnhanxet);

        xuLyRating = new XuLyRating(this);

        btnnhanxet.setOnClickListener(this);
        ratingbar.setOnRatingBarChangeListener(this);

        masp = getIntent().getIntExtra("masp",0);

    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        sosao = (int) rating;
    }

    @Override
    public void ThemDanhGiaThanhCong() {
        Toasty.success(RatingActivity.this,"Thêm đánh giá thành công!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ThemDanhGiaThatBai() {
        Toasty.error(RatingActivity.this,"Bạn không thể thêm đánh giá!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void HienThiDanhSachDanhGia(List<DanhGia> danhGiaList) {

    }

    @Override
    public void onClick(View v) {

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        String madg = telephonyManager.getDeviceId();
        String tenthietbi = Build.DEVICE;
        String tieude = edtieude.getText().toString();
        String noidung = ednoidung.getText().toString();

        if(tieude.trim().length() > 0){
            textinputtieude.setErrorEnabled(false);
            textinputtieude.setError("");
        }else {
            textinputtieude.setErrorEnabled(true);
            textinputtieude.setError("Bạn chưa nhập nội dung");
        }

        if(noidung.trim().length() > 0){
            textinputnoidung.setErrorEnabled(false);
            textinputtieude.setError("");
        }else {
            textinputnoidung.setErrorEnabled(true);
            textinputtieude.setError("Bạn chưa nhập nội dung");
        }

        if(!textinputtieude.isErrorEnabled() && !textinputnoidung.isErrorEnabled()){

            DanhGia danhgia = new DanhGia();
            danhgia.setMADG(madg);
            danhgia.setMASP(masp);
            danhgia.setTENTHIETBI(tenthietbi);
            danhgia.setTIEUDE(tieude);
            danhgia.setNOIDUNG(noidung);
            danhgia.setSOSAO(sosao);

            xuLyRating.ThemDanhGia(danhgia);

            finish();
        }

    }
}
