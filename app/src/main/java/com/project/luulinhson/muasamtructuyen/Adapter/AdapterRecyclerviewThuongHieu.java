package com.project.luulinhson.muasamtructuyen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.project.luulinhson.muasamtructuyen.Model.Object.ThuongHieu;
import com.project.luulinhson.muasamtructuyen.R;
import com.project.luulinhson.muasamtructuyen.View.LoadProductPage.ProductActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Admin on 3/28/2017.
 */

public class AdapterRecyclerviewThuongHieu extends RecyclerView.Adapter<AdapterRecyclerviewThuongHieu.ViewHolderThuongHieu> {

    Context context;
    List<ThuongHieu> thuongHieuList;
    Boolean kiemtra;


    public AdapterRecyclerviewThuongHieu(Context context, List<ThuongHieu> thuongHieuList,Boolean kiemtra){
        this.context = context;
        this.thuongHieuList = thuongHieuList;
        this.kiemtra = kiemtra;
    }


    public class ViewHolderThuongHieu extends RecyclerView.ViewHolder {
        TextView tvTenThuongHieu;
        ImageView imThuongHieu;
        ProgressBar progressBar;
        LinearLayout linearLayoutcha;
        public ViewHolderThuongHieu(View itemView) {
            super(itemView);
            tvTenThuongHieu = (TextView) itemView.findViewById(R.id.tvTenThuongHieu);
            imThuongHieu = (ImageView) itemView.findViewById(R.id.imThuongHieu);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar_download);
            linearLayoutcha = (LinearLayout) itemView.findViewById(R.id.lnCha);
        }
    }


    @Override
    public AdapterRecyclerviewThuongHieu.ViewHolderThuongHieu onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_recyclerview_thuong_hieu,parent,false);

        ViewHolderThuongHieu viewHolderThuongHieu = new ViewHolderThuongHieu(view);
        return viewHolderThuongHieu;
    }

    @Override
    public void onBindViewHolder(final AdapterRecyclerviewThuongHieu.ViewHolderThuongHieu holder, final int position) {
        holder.tvTenThuongHieu.setText(thuongHieuList.get(position).getTENTHUONGHIEU());
        Picasso.with(context).load(thuongHieuList.get(position).getHINHTHUONGHIEU()).resize(200,200).into(holder.imThuongHieu, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError() {

            }
        });

        holder.linearLayoutcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent iHienThiSanPhamTheoMaLoai = new Intent(context, ProductActivity.class);
//                iHienThiSanPhamTheoMaLoai.putExtra("TENLOAI",thuongHieuList.get(position).getTENTHUONGHIEU());
//                iHienThiSanPhamTheoMaLoai.putExtra("MALOAI",thuongHieuList.get(position).getMATHUONGHIEU());
//                iHienThiSanPhamTheoMaLoai.putExtra("KIEMTRA",kiemtra);
//                iHienThiSanPhamTheoMaLoai.putExtra("check",false);
//
//                context.startActivity(iHienThiSanPhamTheoMaLoai);

                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                ProductActivity productActivity = new ProductActivity();
                Bundle bundle = new Bundle();
                bundle.putInt("MALOAI",thuongHieuList.get(position).getMATHUONGHIEU());
                bundle.putBoolean("KIEMTRA",kiemtra);
                bundle.putString("TENLOAI",thuongHieuList.get(position).getTENTHUONGHIEU());
                productActivity.setArguments(bundle);
                fragmentTransaction.addToBackStack("TrangChuActivity");
                fragmentTransaction.add(R.id.addFragment,productActivity);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return thuongHieuList.size();
    }


}
