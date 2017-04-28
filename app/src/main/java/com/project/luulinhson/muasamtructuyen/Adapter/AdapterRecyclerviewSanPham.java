package com.project.luulinhson.muasamtructuyen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.luulinhson.muasamtructuyen.Model.Object.SanPham;
import com.project.luulinhson.muasamtructuyen.R;
import com.project.luulinhson.muasamtructuyen.View.DetailProduct.DetailProducActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Admin on 3/29/2017.
 */

public class AdapterRecyclerviewSanPham extends RecyclerView.Adapter<AdapterRecyclerviewSanPham.ViewHolderSanPham> {

    Context context;
    List<SanPham> sanPhamList;
    int layout;
    boolean kiemtra;
    boolean check;
    public AdapterRecyclerviewSanPham(Context context,int layout ,List<SanPham> sanPhamList,boolean kiemtra,boolean check){
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.layout = layout;
        this.kiemtra = kiemtra;
        this.check = check;
    }


    public class ViewHolderSanPham extends RecyclerView.ViewHolder {
        ImageView imSanPham;
        TextView tvTenSanPham;
        TextView tvGiaSanPham;
        CardView cardView;
        public ViewHolderSanPham(View itemView) {
            super(itemView);
            imSanPham = (ImageView) itemView.findViewById(R.id.imSanPham);
            tvTenSanPham = (TextView) itemView.findViewById(R.id.tvTenSanPham);
            tvGiaSanPham = (TextView) itemView.findViewById(R.id.tvGiaSanPham);
            cardView = (CardView) itemView.findViewById(R.id.cardview);
        }
    }


    @Override
    public ViewHolderSanPham onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout,parent,false);

        ViewHolderSanPham viewHolderSanPham = new ViewHolderSanPham(view);

        return viewHolderSanPham;
    }

    @Override
    public void onBindViewHolder(final ViewHolderSanPham holder, int position) {
        Picasso.with(context).load(sanPhamList.get(position).getANHLON()).resize(350,350).into(holder.imSanPham);
        holder.tvTenSanPham.setText(sanPhamList.get(position).getTENSP());

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPhamList.get(position).getGIA());

        holder.tvGiaSanPham.setText(gia + " VND");
        holder.cardView.setTag(sanPhamList.get(position));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iDetailProduct = new Intent(context, DetailProducActivity.class);
                iDetailProduct.putExtra("sanpham",(SanPham)v.getTag());
                iDetailProduct.putExtra("kiemtra",kiemtra);
                iDetailProduct.putExtra("check",check);
                context.startActivity(iDetailProduct);
            }
        });

    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }


}
