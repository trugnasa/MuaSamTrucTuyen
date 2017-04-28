package com.project.luulinhson.muasamtructuyen.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.project.luulinhson.muasamtructuyen.Model.Object.ThuongHieu;
import com.project.luulinhson.muasamtructuyen.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Admin on 3/31/2017.
 */

public class AdaterRecyclerviewLogoThuongHieu extends RecyclerView.Adapter<AdaterRecyclerviewLogoThuongHieu.ViewHolderLogo> {

    Context context;
    List<ThuongHieu> thuongHieuList;

    public AdaterRecyclerviewLogoThuongHieu(Context context, List<ThuongHieu> thuongHieuList){
        this.context = context;
        this.thuongHieuList = thuongHieuList;
    }

    public class ViewHolderLogo extends RecyclerView.ViewHolder {
        ImageView imlogo;
        ProgressBar progressBarLogo;
        public ViewHolderLogo(View itemView) {
            super(itemView);
            imlogo = (ImageView) itemView.findViewById(R.id.imlogo);
            progressBarLogo = (ProgressBar) itemView.findViewById(R.id.progress_bar_logo);
        }
    }

    @Override
    public ViewHolderLogo onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_logo_thuong_hieu,parent,false);

        ViewHolderLogo viewHolderLogo = new ViewHolderLogo(view);
        return viewHolderLogo;
    }

    @Override
    public void onBindViewHolder(final ViewHolderLogo holder, int position) {
        ThuongHieu thuongHieu = thuongHieuList.get(position);

        Picasso.with(context).load(thuongHieu.getHINHTHUONGHIEU()).resize(300,150).into(holder.imlogo, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBarLogo.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError() {

            }
        });

    }

    @Override
    public int getItemCount() {
        return thuongHieuList.size();
    }


}
