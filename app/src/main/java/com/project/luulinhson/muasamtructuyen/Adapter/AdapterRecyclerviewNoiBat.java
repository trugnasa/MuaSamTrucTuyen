package com.project.luulinhson.muasamtructuyen.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.project.luulinhson.muasamtructuyen.R;

import java.util.List;

/**
 * Created by Admin on 3/26/2017.
 */

public class AdapterRecyclerviewNoiBat extends RecyclerView.Adapter<AdapterRecyclerviewNoiBat.ViewHolderNoiBat> {

    Context context;
    List<Integer> drawableList;

    public  AdapterRecyclerviewNoiBat(Context context, List<Integer> drawableList){
        this.context = context;
        this.drawableList = drawableList;
    }

//Bước 2
    public class ViewHolderNoiBat extends RecyclerView.ViewHolder {

        ImageView imgNoiBat;
        public ViewHolderNoiBat(View itemView) {
            super(itemView);
            imgNoiBat = (ImageView) itemView.findViewById(R.id.imgnoibat);
        }
    }

// Bước 1
    @Override
    public ViewHolderNoiBat onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_recyclerview_noi_bat,parent,false);

        ViewHolderNoiBat viewHolderNoiBat = new ViewHolderNoiBat(view);
        return viewHolderNoiBat;
    }
//Bước 3
    @Override
    public void onBindViewHolder(ViewHolderNoiBat holder, int position) {
        holder.imgNoiBat.setImageResource(drawableList.get(position));

    }

    @Override
    public int getItemCount() {
        return drawableList.size();
    }


}
