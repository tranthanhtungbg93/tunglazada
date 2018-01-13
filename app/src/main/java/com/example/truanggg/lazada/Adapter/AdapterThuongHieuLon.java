package com.example.truanggg.lazada.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.truanggg.lazada.Model.ObjectClass.ThuongHieu;
import com.example.truanggg.lazada.R;
import com.example.truanggg.lazada.View.HienThisanPhamTheoDanhMucActivity.HienThiSanPhamTheoDanhMucActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Truang on 12/21/2017.
 **/

public class AdapterThuongHieuLon
        extends RecyclerView.Adapter<AdapterThuongHieuLon.ViewHolderThuongHieu>
{
    Context context;
    List<ThuongHieu> thuongHieuslist;
    boolean kiemtra;


    public AdapterThuongHieuLon(Context context, List<ThuongHieu> thuongHieus,boolean kiemtra){
        this.context = context;
        this.thuongHieuslist = thuongHieus;
        this.kiemtra = kiemtra;
    }
    @Override
    public ViewHolderThuongHieu onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_recycler_thuonghieulon,parent,false);
        ViewHolderThuongHieu viewHolder = new ViewHolderThuongHieu(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolderThuongHieu holder, int position) {
        final ThuongHieu thuongHieu = thuongHieuslist.get(position);
        holder.tieuDeThuongHieu.setText(thuongHieu.getTENTHUONGHIEU());

        holder.lnThuongHieuLon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent iDanhMucTheoSanPham = new Intent(context, HienThiSanPhamTheoDanhMucActivity.class);
//                iDanhMucTheoSanPham.putExtra("MALOAI",thuongHieu.getMATHUONGHIEU());
//                iDanhMucTheoSanPham.putExtra("TENLOAI",thuongHieu.getTENTHUONGHIEU());
//                iDanhMucTheoSanPham.putExtra("KIEMTRA",kiemtra);
//
//                context.startActivity(iDanhMucTheoSanPham);
                FragmentManager fm = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("MALOAI",thuongHieu.getMATHUONGHIEU());
                bundle.putString("TENLOAI",thuongHieu.getTENTHUONGHIEU());
                bundle.putBoolean("KIEMTRA",kiemtra);

                HienThiSanPhamTheoDanhMucActivity fragment = new HienThiSanPhamTheoDanhMucActivity();
                fragment.setArguments(bundle);
                ft.replace(R.id.themFragment,fragment).addToBackStack("TrangChuActivity").commit();
                Log.d("check",thuongHieu.getMATHUONGHIEU() + " - " + thuongHieu.getTENTHUONGHIEU()+"-"+kiemtra);
            }
        });
        Picasso.with(context).load(thuongHieu.getHINHTHUONGHIEU()).resize(120,120)
                .into(holder.HinhThuongHieu);
    }
    @Override
    public int getItemCount() {
        return thuongHieuslist.size();
    }

    public class ViewHolderThuongHieu extends RecyclerView.ViewHolder {
        TextView tieuDeThuongHieu;
        ImageView HinhThuongHieu;
        LinearLayout lnThuongHieuLon;

        public ViewHolderThuongHieu(View itemView) {
            super(itemView);
            tieuDeThuongHieu = itemView.findViewById(R.id.txtTieuDeThuongHieuDientu);
            HinhThuongHieu = itemView.findViewById(R.id.imgHinhThuongHieuDienTu);
            lnThuongHieuLon = itemView.findViewById(R.id.lnThuongHieuLon);
        }
    }
}
