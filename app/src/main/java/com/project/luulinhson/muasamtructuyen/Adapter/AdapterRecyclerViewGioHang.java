package com.project.luulinhson.muasamtructuyen.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.luulinhson.muasamtructuyen.Model.GioHang.ModelGioHang;
import com.project.luulinhson.muasamtructuyen.Model.Object.SanPham;
import com.project.luulinhson.muasamtructuyen.R;
import com.project.luulinhson.muasamtructuyen.View.Main.MainActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Created by Admin on 4/15/2017.
 */

public class AdapterRecyclerViewGioHang extends RecyclerView.Adapter<AdapterRecyclerViewGioHang.ViewHolderGioHang> {

    Context context;
    List<SanPham> sanPhamList;
    ModelGioHang modelGioHang;

    public AdapterRecyclerViewGioHang(Context context, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        modelGioHang = new ModelGioHang();
        modelGioHang.MoKetNoiDatabase(context);
    }

    public class ViewHolderGioHang extends RecyclerView.ViewHolder {
        TextView tvtensanphamgiohang,tvgiasanphamgiohang,tvsoluongsanpham;
        ImageView imhinhsanphamgiohang,imdeletegiohang;
        ImageButton imbgiam,imbtang;

        public ViewHolderGioHang(View itemView) {
            super(itemView);
            tvtensanphamgiohang = (TextView) itemView.findViewById(R.id.tvTenSanPhamGioHang);
            tvgiasanphamgiohang = (TextView) itemView.findViewById(R.id.tvGiaSanPhamGioHang);
            tvsoluongsanpham = (TextView) itemView.findViewById(R.id.tvsoluongsanpham);
            imhinhsanphamgiohang = (ImageView) itemView.findViewById(R.id.imSanPhamGioHang);
            imdeletegiohang = (ImageView) itemView.findViewById(R.id.imDeleteGioHang);
            imbgiam = (ImageButton) itemView.findViewById(R.id.imbgiam);
            imbtang = (ImageButton) itemView.findViewById(R.id.imbtang);

        }
    }


    @Override
    public ViewHolderGioHang onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_recyclerview_gio_hang,parent,false);

        ViewHolderGioHang viewHolderGioHang = new ViewHolderGioHang(view);

        return viewHolderGioHang;
    }

    @Override
    public void onBindViewHolder(final ViewHolderGioHang holder, final int position) {
        final SanPham sanPham = sanPhamList.get(position);

        holder.tvtensanphamgiohang.setText(sanPham.getTENSP());

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGIA());
        holder.tvgiasanphamgiohang.setText(gia + " VND");

        Bitmap bitmap = BitmapFactory.decodeByteArray(sanPham.getHinhgiohang(),0,sanPham.getHinhgiohang().length);
        holder.imhinhsanphamgiohang.setImageBitmap(bitmap);

        holder.imdeletegiohang.setTag(sanPham.getMASP());// lưu lại masp của sản phẩm vào imdeletegiohang để tý nữa khi ấn vào còn get Tag để lấy masp
        holder.imdeletegiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setMessage("Bạn có muốn xóa sản phẩm này?")
                        .setCancelable(false)
                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ModelGioHang modelGioHang = new ModelGioHang();
                                modelGioHang.MoKetNoiDatabase(context);
                                modelGioHang.XoaSanPhamTheoMaSP((int)holder.imdeletegiohang.getTag());
                                sanPhamList.remove(position);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Không,Tôi không muốn", null)
                        .show();


            }
        });

        holder.tvsoluongsanpham.setText(String.valueOf(sanPham.getSOLUONG()));
        Picasso.with(context).load(R.drawable.ic_remove_black_18dp).resize(60,60).into(holder.imbgiam);
        holder.imbgiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(holder.tvsoluongsanpham.getText().toString());
                if(soluong > 1){
                    soluong--;
                }
                modelGioHang.CapNhatSoLuongSanPhamTrongGioHang(sanPham.getMASP(),soluong);
                holder.tvsoluongsanpham.setText(String.valueOf(soluong));
            }
        });

        Picasso.with(context).load(R.drawable.ic_add_black_18dp).resize(60,60).into(holder.imbtang);
        holder.imbtang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(holder.tvsoluongsanpham.getText().toString());
                int soluongtonkho = sanPham.getSOLUONGTONKHO();
                if(soluong < soluongtonkho){
                    soluong++;
                }else {
                    Toasty.error(context,"Số lượng sản phẩm bạn mua vượt quá số lượng cung ứng của cửa hàng", Toast.LENGTH_LONG).show();
                }
                modelGioHang.CapNhatSoLuongSanPhamTrongGioHang(sanPham.getMASP(),soluong);
                holder.tvsoluongsanpham.setText(String.valueOf(soluong));
            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }


}
