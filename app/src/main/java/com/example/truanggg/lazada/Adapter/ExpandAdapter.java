package com.example.truanggg.lazada.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
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
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.truanggg.lazada.Model.ObjectClass.LoaiSanPham;
import com.example.truanggg.lazada.Model.TrangChu.XuLyMenu.XuLiJSONMenu;
import com.example.truanggg.lazada.R;
import com.example.truanggg.lazada.View.HienThisanPhamTheoDanhMucActivity.HienThiSanPhamTheoDanhMucActivity;

import java.util.List;

/**
 * Created by Truang on 12/16/2017.
 */

public class ExpandAdapter extends BaseExpandableListAdapter {
    Context context;
    List<LoaiSanPham> loaiSanPhams;
    ViewHolderMenu viewHolderMenu;
    public ExpandAdapter(Context context, List<LoaiSanPham> loaiSanPhams){
        this.context = context;
        this.loaiSanPhams = loaiSanPhams;

        XuLiJSONMenu xuLiJSONMenu = new XuLiJSONMenu();

        int count =loaiSanPhams.size();
        for (int i = 0; i< count; i++){
            int maloaisp = loaiSanPhams.get(i).getMaloaiSP();
            loaiSanPhams.get(i).setListCon(xuLiJSONMenu.LayLoaiSPTheoMaLoai(maloaisp));
        }
    }

    @Override
    public int getGroupCount() {
        return loaiSanPhams.size();
    }

    @Override
    public int getChildrenCount(int vitriGroupcha) {
        if (loaiSanPhams.get(vitriGroupcha).getListCon().size() !=0){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Object getGroup(int vitriGroupcha) {
        return loaiSanPhams.get(vitriGroupcha);
    }

    @Override
    public Object getChild(int vitriGroupcha, int vitriGroupCon) {
        return loaiSanPhams.get(vitriGroupcha).getListCon().get(vitriGroupCon);
    }

    @Override
    public long getGroupId(int vitriGroupcha) {
        return loaiSanPhams.get(vitriGroupcha).getMaloaiSP();
    }

    @Override
    public long getChildId(int vitriGroupcha, int vitriGroupCon) {
        return loaiSanPhams.get(vitriGroupcha).getListCon().get(vitriGroupCon).getMaloaiSP();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public class ViewHolderMenu {
        TextView txtTenLoaiSP;
        ImageView hinhmenu;

    }

    @Override
    public View getGroupView(final int vitriGroupcha, boolean isExpanded, View convertView, ViewGroup parent) {

        View viewGroupCha = convertView;
        if(viewGroupCha == null) {
             viewHolderMenu = new ViewHolderMenu();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewGroupCha = layoutInflater.inflate(R.layout.custom_layout_groupcha, parent, false);
            viewHolderMenu.txtTenLoaiSP = viewGroupCha.findViewById(R.id.txt_ten_loai_sp);
            viewHolderMenu.hinhmenu = viewGroupCha.findViewById(R.id.imgMenuPlus);

            viewGroupCha.setTag(viewHolderMenu);
        }else {
             viewHolderMenu = (ViewHolderMenu) viewGroupCha.getTag();
        }

        viewHolderMenu.txtTenLoaiSP.setText(loaiSanPhams.get(vitriGroupcha).getTenloaiSP());

        int demSPCon = loaiSanPhams.get(vitriGroupcha).getListCon().size();

        if (demSPCon > 0 ){
            viewHolderMenu.hinhmenu.setVisibility(View.VISIBLE);

        }else {
            viewHolderMenu.hinhmenu.setVisibility(View.INVISIBLE);
        }

        if(isExpanded){
            viewHolderMenu.hinhmenu.setImageResource(R.drawable.ic_remove_black_24dp);
            viewGroupCha.setBackgroundResource(R.color.colorGray);
        }else {
            viewGroupCha.setBackgroundResource(R.color.colorWhite);
            viewHolderMenu.hinhmenu.setImageResource(R.drawable.ic_add_black_24dp);
        }
        // xét click cho cái linear layout group cha
        viewGroupCha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("maloaisp",loaiSanPhams.get(vitriGroupcha).getTenloaiSP()
                        + "-" + loaiSanPhams.get(vitriGroupcha).getMaloaiSP());
                FragmentManager fm = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                HienThiSanPhamTheoDanhMucActivity theoDanhMucActivity =new HienThiSanPhamTheoDanhMucActivity();

                Bundle bundle = new Bundle();
                bundle.putInt("MALOAI",loaiSanPhams.get(vitriGroupcha).getMaloaiSP());
                bundle.putBoolean("KIEMTRA",false);
                bundle.putString("TENLOAI",loaiSanPhams.get(vitriGroupcha).getTenloaiSP());
                theoDanhMucActivity.setArguments(bundle);
                ft.replace(R.id.themFragment,theoDanhMucActivity).addToBackStack("TrangChuActivity").commit();

                return false;
            }
        });
        return viewGroupCha;
    }

    @Override
    public View getChildView(int vitriGroupcha, int vitriGroupcon, boolean isLastChild, View convertView, ViewGroup parent) {
//        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = layoutInflater.inflate(R.layout.custom_layout_group_con,parent,false);
//        ExpandableListView expandableListView = view.findViewById(R.id.epGroupcon);

        SecondExpanable secondExpanable = new SecondExpanable(context);
        ExpandAdapter expandAdapter = new ExpandAdapter(context,loaiSanPhams.get(vitriGroupcha).getListCon());
        secondExpanable.setAdapter(expandAdapter);

        secondExpanable.setGroupIndicator(null);// xóa cái dấu ở danh sách menu
        notifyDataSetChanged();
        return secondExpanable;
    }

    public class SecondExpanable extends ExpandableListView{


        public SecondExpanable(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            int width = size.x;
            int height = size.y;
            Log.d("size", width + "-" + height);
            // khi không dùng thuôc tính width nó sẽ tự hiểu là match parent
            // (icon add sẽ được đẩy sang bên phải màn hình)
//            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width,MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    public class SecondAdapter extends BaseExpandableListAdapter{
        List<LoaiSanPham> listcon;

        public SecondAdapter (List<LoaiSanPham> listcon){
            this.listcon = listcon;
            XuLiJSONMenu xuLiJSONMenu = new XuLiJSONMenu();

            int count =listcon.size();
            for (int i = 0; i< count; i++){
                int maloaisp = listcon.get(i).getMaloaiSP();
                listcon.get(i).setListCon(xuLiJSONMenu.LayLoaiSPTheoMaLoai(maloaisp));
            }
        }

        @Override
        public int getGroupCount() {
            return listcon.size();
        }

        @Override
        public int getChildrenCount(int vitriGroupcha) {
            return listcon.get(vitriGroupcha).getListCon().size();
        }

        @Override
        public Object getGroup(int vitriGroupcha) {
            return listcon.get(vitriGroupcha);
        }

        @Override
        public Object getChild(int vitriGroupcha, int vitriGroupCon) {
            return listcon.get(vitriGroupcha).getListCon().get(vitriGroupCon);
        }

        @Override
        public long getGroupId(int vitriGroupcha) {
            return listcon.get(vitriGroupcha).getMaloaiSP();
        }

        @Override
        public long getChildId(int vitriGroupcha, int vitriGroupCon) {
            return listcon.get(vitriGroupcha).getListCon().get(vitriGroupCon).getMaloaiSP();
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int vitriGroupcha, boolean isExpanded, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.custom_layout_groupcha,parent,false);
            TextView txtTenLoaiSP = view.findViewById(R.id.txt_ten_loai_sp);
            txtTenLoaiSP.setText(listcon.get(vitriGroupcha).getTenloaiSP());
            return view;
        }

        @Override
        public View getChildView(int vitriGroupcha, int vitriGroupcon, boolean isLastChild, View convertView, ViewGroup parent) {
            TextView tv = new TextView(context);
            tv.setText(listcon.get(vitriGroupcha).getListCon().get(vitriGroupcon).getTenloaiSP());
            tv.setPadding(15,5,5,5);
            tv.setBackgroundColor(Color.YELLOW);
            tv.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                        ViewGroup.LayoutParams.MATCH_PARENT));

            return tv;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }
}
