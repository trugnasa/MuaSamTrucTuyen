package com.project.luulinhson.muasamtructuyen.Adapter;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.luulinhson.muasamtructuyen.Model.MainActivity.MenuDaCap.ParseJSONMenu;
import com.project.luulinhson.muasamtructuyen.Model.Object.LoaiSanPham;
import com.project.luulinhson.muasamtructuyen.R;
import com.project.luulinhson.muasamtructuyen.View.LoadProductPage.ProductActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Admin on 3/5/2017.
 */

public class ExpandListViewAdapter extends BaseExpandableListAdapter {

    Context context;
    List<LoaiSanPham> loaiSanPhamList;


    public ExpandListViewAdapter(Context context, List<LoaiSanPham> loaiSanPhamList){
        this.context = context;
        this.loaiSanPhamList = loaiSanPhamList;

        ParseJSONMenu parseJSONMenu = new ParseJSONMenu();
        int count = loaiSanPhamList.size();
        for (int i = 0; i < count; i++) {
            int maloaisp = loaiSanPhamList.get(i).getMALOAI_SANPHAM();
            loaiSanPhamList.get(i).setLoaiSanPhamChildren(parseJSONMenu.LayDanhSachLoaiSanPham(maloaisp));
        }

    }

    @Override
    public int getGroupCount() {
        return loaiSanPhamList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int count = loaiSanPhamList.get(groupPosition).getLoaiSanPhamChildren().size();
        if(count != 0){
            return 1;
        }else {
            return 0;
        }

    }

    @Override
    public Object getGroup(int groupPosition) {
        return loaiSanPhamList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return loaiSanPhamList.get(groupPosition).getLoaiSanPhamChildren().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return loaiSanPhamList.get(groupPosition).getMALOAI_SANPHAM();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return loaiSanPhamList.get(groupPosition).getLoaiSanPhamChildren().get(childPosition).getMALOAI_SANPHAM();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_menu_da_cap_group_cha,parent,false);

        TextView tvmenugroupcha = (TextView) view.findViewById(R.id.tvmenugroupcha);
        ImageView imageView = (ImageView) view.findViewById(R.id.imadd);
        tvmenugroupcha.setText(loaiSanPhamList.get(groupPosition).getTEN_SAN_PHAM());

        int count = loaiSanPhamList.get(groupPosition).getLoaiSanPhamChildren().size();
        if(count > 0){
            imageView.setVisibility(View.VISIBLE); //Nếu có dữ liệu thì hiển thị dấu + lên
        }else {
            imageView.setVisibility(View.INVISIBLE); // Nếu không có dữ liệu thì ẩn dấu đi
        }

        if(isExpanded){
//            imageView.setImageResource(R.drawable.ic_remove_black_18dp); //Nếu item được chọn thì chuyển thành dấu -
            Picasso.with(context).load(R.drawable.ic_remove_black_18dp).centerInside().resize(50,50).into(imageView);
            view.setBackgroundResource(R.color.colorXanhLaMaBarclay);
        }else {
//            imageView.setImageResource(R.drawable.ic_add_black_18dp); // Nếu không thì chuyển thành dấu +
            Picasso.with(context).load(R.drawable.ic_add_black_18dp).centerInside().resize(50,50).into(imageView);
        }

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("MaLoaiSanPham", "onTouch: " + loaiSanPhamList.get(groupPosition).getTEN_SAN_PHAM() + "," + loaiSanPhamList.get(groupPosition).getMALOAI_SANPHAM());

                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                ProductActivity productActivity = new ProductActivity();
                Bundle bundle = new Bundle();
                bundle.putInt("MALOAI",loaiSanPhamList.get(groupPosition).getMALOAI_SANPHAM());
                bundle.putBoolean("KIEMTRA",false);
                bundle.putString("TENLOAI",loaiSanPhamList.get(groupPosition).getTEN_SAN_PHAM());
                productActivity.setArguments(bundle);
                fragmentTransaction.addToBackStack("TrangChuActivity");
                fragmentTransaction.add(R.id.addFragment,productActivity);
                fragmentTransaction.commit();
                return false;
            }
        });

        return view;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.custom_menu_da_cap_group_con,parent,false);
//        ExpandableListView expandableListView = (ExpandableListView) view.findViewById(R.id.expandlistviewcon);

        /* child view thực chất là load lại một ExpandListView mới nhưng vẫn sử dụng chính ExpandListViewAdapter phía trên,
        như vậy child view lại biến thành group view */

        ExpandListViewSecond expandListViewSecond = new ExpandListViewSecond(context);
        ExpandListViewAdapter secondAdapter = new ExpandListViewAdapter(context,loaiSanPhamList.get(groupPosition).getLoaiSanPhamChildren());

        expandListViewSecond.setGroupIndicator(null);
        expandListViewSecond.setAdapter(secondAdapter);
        secondAdapter.notifyDataSetChanged();

        return expandListViewSecond;
    }

    public class ExpandListViewSecond extends ExpandableListView{

        public ExpandListViewSecond(Context context) {
            super(context);
        }


        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            int wight = size.x;
            int height = size.y;

//            widthMeasureSpec = MeasureSpec.makeMeasureSpec(wight,MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,MeasureSpec.AT_MOST); // Xét chiều cao động tối đa cho ExpListView theo chiều rộng dài của màn hình bất kỳ
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}

//      class SecondAdapter extends BaseExpandableListAdapter {
//
//         List<LoaiSanPham> listcons;
//         Context context;
//
//         public SecondAdapter(Context context,List<LoaiSanPham> listcons) {
//             this.listcons = listcons;
//             this.context = context;
//
//             ParseJSONMenu parseJSONMenu = new ParseJSONMenu();
//             int count = listcons.size();
//             for (int i = 0; i < count; i++) {
//                 int maloaisp = listcons.get(i).getMALOAI_SANPHAM();
//                 listcons.get(i).setLoaiSanPhamChildren(parseJSONMenu.LayDanhSachLoaiSanPham(maloaisp));
//             }
//         }
//
//         @Override
//         public int getGroupCount() {
//             return listcons.size();
//         }
//
//         @Override
//         public int getChildrenCount(int groupPosition) {
//             return listcons.get(groupPosition).getLoaiSanPhamChildren().size();
//         }
//
//         @Override
//         public Object getGroup(int groupPosition) {
//             return listcons.get(groupPosition);
//         }
//
//         @Override
//         public Object getChild(int groupPosition, int childPosition) {
//             return listcons.get(groupPosition).getLoaiSanPhamChildren().get(childPosition);
//         }
//
//         @Override
//         public long getGroupId(int groupPosition) {
//             return listcons.get(groupPosition).getMALOAI_SANPHAM();
//         }
//
//         @Override
//         public long getChildId(int groupPosition, int childPosition) {
//             return listcons.get(groupPosition).getLoaiSanPhamChildren().get(childPosition).getMALOAI_SANPHAM();
//         }
//
//         @Override
//         public boolean hasStableIds() {
//             return false;
//         }
//
//         @Override
//         public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//             LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//             View view = inflater.inflate(R.layout.custom_menu_da_cap_group_cha,parent,false);
//
//             TextView tvmenugroupcha = (TextView) view.findViewById(R.id.tvmenugroupcha);
//             tvmenugroupcha.setText(listcons.get(groupPosition).getTEN_SAN_PHAM());
//
//             return view;
//         }
//
//         @Override
//         public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//             TextView tv = new TextView(context);
//             tv.setText(listcons.get(groupPosition).getLoaiSanPhamChildren().get(childPosition).getTEN_SAN_PHAM());
//             tv.setPadding(15,5,5,5);
//             tv.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
//
//             return tv;
//         }
//
//         @Override
//         public boolean isChildSelectable(int groupPosition, int childPosition) {
//             return false;
//         }
//    }
