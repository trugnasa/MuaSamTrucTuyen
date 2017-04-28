package com.project.luulinhson.muasamtructuyen.View.Main.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.luulinhson.muasamtructuyen.Adapter.AdapterRecyclerviewDienTu;
import com.project.luulinhson.muasamtructuyen.Adapter.AdapterRecyclerviewSanPham;
import com.project.luulinhson.muasamtructuyen.Adapter.AdaterRecyclerviewLogoThuongHieu;
import com.project.luulinhson.muasamtructuyen.CustomView.LinearLayoutManagerWithSmoothScroller;
import com.project.luulinhson.muasamtructuyen.Model.Object.DienTu;
import com.project.luulinhson.muasamtructuyen.Model.Object.SanPham;
import com.project.luulinhson.muasamtructuyen.Model.Object.ThuongHieu;
import com.project.luulinhson.muasamtructuyen.Presenter.MainActivity.FragmentDienTu.XuLyFragmentDienTu;
import com.project.luulinhson.muasamtructuyen.R;
import com.project.luulinhson.muasamtructuyen.View.Main.ViewXuLyFragmentDienTu;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Admin on 2/28/2017.
 */

public class FragmentDienTu extends Fragment implements ViewXuLyFragmentDienTu{

    RecyclerView recyclerView,recyclerViewTenThuongHieu,recyclerViewCacSanPhamMoi;
    List<DienTu> dienTuList;
    XuLyFragmentDienTu xuLyFragmentDienTu;
    ImageView imHinhAnhDienTu,imHinhAnhPhu;
    ImageView imSanPham1,imSanPham2,imSanPham3;
    TextView tvTenSanPham1,tvTenSanPham2,tvTenSanPham3,tvGiaSanPham1,tvGiaSanPham2,tvGiaSanPham3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dien_tu,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerDienTu);
        recyclerViewTenThuongHieu = (RecyclerView) view.findViewById(R.id.recyclerTenThuongHieu);
        recyclerViewCacSanPhamMoi = (RecyclerView) view.findViewById(R.id.recyclerCacSanPhamMoi);

//        imHinhAnhDienTu = (ImageView) view.findViewById(R.id.imHinhAnhDienTu);
        imHinhAnhPhu = (ImageView) view.findViewById(R.id.imHinhAnhPhu);

        imSanPham1 = (ImageView) view.findViewById(R.id.imSanPham1);
        imSanPham2 = (ImageView) view.findViewById(R.id.imSanPham2);
        imSanPham3 = (ImageView) view.findViewById(R.id.imSanPham3);

        tvTenSanPham1 = (TextView) view.findViewById(R.id.tvTenSanPham1);
        tvTenSanPham2 = (TextView) view.findViewById(R.id.tvTenSanPham2);
        tvTenSanPham3 = (TextView) view.findViewById(R.id.tvTenSanPham3);

        tvGiaSanPham1 = (TextView) view.findViewById(R.id.tvGiaSanPham1);
        tvGiaSanPham2 = (TextView) view.findViewById(R.id.tvGiaSanPham2);
        tvGiaSanPham3 = (TextView) view.findViewById(R.id.tvGiaSanPham3);


        xuLyFragmentDienTu = new XuLyFragmentDienTu(this);
        dienTuList = new ArrayList<>();

        xuLyFragmentDienTu.LayDanhSachDienTu();
        xuLyFragmentDienTu.LayDanhSachLogoThuongHieu();
        xuLyFragmentDienTu.LayDanhSachSanPhamMoi();

        return view;
    }

    @Override
    public void HienThiDanhSachDienTu(List<DienTu> dienTuList) {

        AdapterRecyclerviewDienTu adapterRecyclerviewDienTu = new AdapterRecyclerviewDienTu(getActivity(),dienTuList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1,LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterRecyclerviewDienTu);
        adapterRecyclerviewDienTu.notifyDataSetChanged();
    }

    @Override
    public void HienThiDanhSachLogoThuongHieu(List<ThuongHieu> thuongHieuList) {
        AdaterRecyclerviewLogoThuongHieu adaterRecyclerviewLogoThuongHieu = new AdaterRecyclerviewLogoThuongHieu(getContext(),thuongHieuList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2,LinearLayoutManager.HORIZONTAL,false);

        recyclerViewTenThuongHieu.setLayoutManager(layoutManager);
        recyclerViewTenThuongHieu.setAdapter(adaterRecyclerviewLogoThuongHieu);

        adaterRecyclerviewLogoThuongHieu.notifyDataSetChanged();

    }

    @Override
    public void HienThiDanhSachSanPhamMoi(List<SanPham> sanPhamList) {
        AdapterRecyclerviewSanPham adapterRecyclerviewSanPham = new AdapterRecyclerviewSanPham(getContext(),R.layout.custom_recyclerview_san_pham,sanPhamList,true,false);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1,LinearLayoutManager.HORIZONTAL,false);

        recyclerViewCacSanPhamMoi.setLayoutManager(layoutManager);
        recyclerViewCacSanPhamMoi.setAdapter(adapterRecyclerviewSanPham);

        adapterRecyclerviewSanPham.notifyDataSetChanged();

        Random random = new Random();
        int vitri = random.nextInt(sanPhamList.size());
        Picasso.with(getContext()).load(sanPhamList.get(vitri).getANHLON()).resize(300,300).into(imSanPham1);
        tvTenSanPham1.setText(sanPhamList.get(vitri).getTENSP());
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPhamList.get(vitri).getGIA());
        tvGiaSanPham1.setText(gia + " VND");

        Random random2 = new Random();
        int vitri2 = random2.nextInt(sanPhamList.size());
        Picasso.with(getContext()).load(sanPhamList.get(vitri2).getANHLON()).resize(300,300).into(imSanPham2);
        tvTenSanPham2.setText(sanPhamList.get(vitri2).getTENSP());
        NumberFormat numberFormat2 = new DecimalFormat("###,###");
        String gia2 = numberFormat2.format(sanPhamList.get(vitri2).getGIA());
        tvGiaSanPham2.setText(gia2 + " VND");

        Random random3 = new Random();
        int vitri3 = random3.nextInt(sanPhamList.size());
        Picasso.with(getContext()).load(sanPhamList.get(vitri3).getANHLON()).resize(300,300).into(imSanPham3);
        tvTenSanPham3.setText(sanPhamList.get(vitri3).getTENSP());
        NumberFormat numberFormat3 = new DecimalFormat("###,###");
        String gia3 = numberFormat3.format(sanPhamList.get(vitri3).getGIA());
        tvGiaSanPham3.setText(gia3 + " VND");

    }


}
