package com.project.luulinhson.muasamtructuyen.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.project.luulinhson.muasamtructuyen.Model.Object.DanhGia;
import com.project.luulinhson.muasamtructuyen.R;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Admin on 4/13/2017.
 */

public class AdapterRecyclerviewDanhGia extends RecyclerView.Adapter<AdapterRecyclerviewDanhGia.ViewHolderDanhGia> {


    Context context;
    List<DanhGia> danhGiaList;
    int limit;

    public AdapterRecyclerviewDanhGia(Context context, List<DanhGia> danhGiaList, int limit) {
        this.context = context;
        this.danhGiaList = danhGiaList;
        this.limit = limit;
    }

    public class ViewHolderDanhGia extends RecyclerView.ViewHolder {

        TextView tvtieudedanhgia,tvduocdangboi,tvnoidungdanhgia;
        RatingBar ratingbardanhgia;

        public ViewHolderDanhGia(View itemView) {
            super(itemView);
            tvtieudedanhgia = (TextView) itemView.findViewById(R.id.tvtieudedanhgia);
            tvduocdangboi = (TextView) itemView.findViewById(R.id.tvduocdangboi);
            tvnoidungdanhgia = (TextView) itemView.findViewById(R.id.tvnoidungdanhgia);
            ratingbardanhgia = (RatingBar) itemView.findViewById(R.id.ratingbardanhgia);
        }
    }

    @Override
    public ViewHolderDanhGia onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_recyclerview_danh_gia,parent,false);

        ViewHolderDanhGia viewHolderDanhGia = new ViewHolderDanhGia(view);
        return viewHolderDanhGia;
    }

    @Override
    public void onBindViewHolder(ViewHolderDanhGia holder, int position) {

        DanhGia danhGia = danhGiaList.get(position);

        holder.tvtieudedanhgia.setText(danhGia.getTIEUDE());
        holder.tvduocdangboi.setText("Được đăng bởi " + danhGia.getTENTHIETBI() + " vào " + danhGia.getNGAYDANHGIA());
        holder.tvnoidungdanhgia.setText(danhGia.getNOIDUNG());
        holder.ratingbardanhgia.setRating(danhGia.getSOSAO());

    }

    @Override
    public int getItemCount() {
        int dong = 0;
        if(danhGiaList.size() < limit){
            dong = danhGiaList.size();
        }else {
            if(limit != 0){
                dong = limit;
            }else {
                dong = danhGiaList.size();
            }
        }
        return dong;
    }


}
