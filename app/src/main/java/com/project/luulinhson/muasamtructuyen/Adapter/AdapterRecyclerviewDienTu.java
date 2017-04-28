package com.project.luulinhson.muasamtructuyen.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.luulinhson.muasamtructuyen.Model.Object.DienTu;
import com.project.luulinhson.muasamtructuyen.Model.Object.SanPham;
import com.project.luulinhson.muasamtructuyen.Model.Object.ThuongHieu;
import com.project.luulinhson.muasamtructuyen.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 3/28/2017.
 */

public class AdapterRecyclerviewDienTu extends RecyclerView.Adapter<AdapterRecyclerviewDienTu.ViewHolderDienTu> {

    Context context;
    List<DienTu> dienTuList;

    public AdapterRecyclerviewDienTu(Context context, List<DienTu> dienTuList){
        this.context = context;
        this.dienTuList = dienTuList;

    }

    public class ViewHolderDienTu extends RecyclerView.ViewHolder {
        ImageView imFirstRecyclerView;
        RecyclerView recyclerThuongHieu;
        RecyclerView recyclerSanPham;
        TextView tvThuongHieu,tvSanPham;
        public ViewHolderDienTu(View itemView) {
            super(itemView);

            imFirstRecyclerView = (ImageView) itemView.findViewById(R.id.imFirstRecyclerView);
            recyclerThuongHieu = (RecyclerView) itemView.findViewById(R.id.recyclerThuongHieu);
            recyclerSanPham = (RecyclerView) itemView.findViewById(R.id.recyclerSanPham);
            tvThuongHieu = (TextView) itemView.findViewById(R.id.tvThuongHieu);
            tvSanPham = (TextView) itemView.findViewById(R.id.tvSanPham);
        }
    }
    @Override
    public ViewHolderDienTu onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_recyclerview_dien_tu,parent,false);

        ViewHolderDienTu viewHolderDienTu = new ViewHolderDienTu(view);
        return viewHolderDienTu;
    }

    @Override
    public void onBindViewHolder(ViewHolderDienTu holder, int position) {
        List<ThuongHieu> thuongHieuList;
        List<SanPham> sanPhamList;
        thuongHieuList = dienTuList.get(position).getThuongHieuList();
        sanPhamList = dienTuList.get(position).getSanPhamList();

        holder.tvThuongHieu.setText(dienTuList.get(position).getTennoibat());

        AdapterRecyclerviewThuongHieu adapterRecyclerviewThuongHieu = new AdapterRecyclerviewThuongHieu(context,thuongHieuList,dienTuList.get(position).getKiemtra());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,3,GridLayoutManager.HORIZONTAL,false);

        holder.recyclerThuongHieu.setLayoutManager(layoutManager);
        holder.recyclerThuongHieu.setAdapter(adapterRecyclerviewThuongHieu);
        adapterRecyclerviewThuongHieu.notifyDataSetChanged();

        holder.tvSanPham.setText(dienTuList.get(position).getTentopnoibat());

        AdapterRecyclerviewSanPham adapterRecyclerviewSanPham = new AdapterRecyclerviewSanPham(context,R.layout.custom_recyclerview_san_pham,sanPhamList,dienTuList.get(position).getKiemtra(),false);
        RecyclerView.LayoutManager layoutManagerSanPham = new GridLayoutManager(context,1,LinearLayoutManager.HORIZONTAL,false);

        holder.recyclerSanPham.setLayoutManager(layoutManagerSanPham);
        holder.recyclerSanPham.setAdapter(adapterRecyclerviewSanPham);
        adapterRecyclerviewSanPham.notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return dienTuList.size();
    }


}
