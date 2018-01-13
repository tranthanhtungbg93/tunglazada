package com.example.truanggg.lazada.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.truanggg.lazada.Model.ObjectClass.ChiTietKhuyenMai;
import com.example.truanggg.lazada.Model.ObjectClass.SanPham;
import com.example.truanggg.lazada.R;
import com.example.truanggg.lazada.View.ChiTietSanPham.ChiTietSanPhamActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Truang on 12/22/2017.
 **/

public class AdapterTopDienThoaiDientu extends RecyclerView.Adapter<AdapterTopDienThoaiDientu.ViewHolderTopDienThoai> {

    Context context;
    List<SanPham> sanPhamList;
    int layout;

    public AdapterTopDienThoaiDientu (Context context,int layout,List<SanPham> sanPhamList){
        this.context=context;
        this.sanPhamList= sanPhamList;
        this.layout = layout;
    }

    public class ViewHolderTopDienThoai extends RecyclerView.ViewHolder {
        ImageView imTopDienThoaiDienTu;
        TextView txtTieuDeTopDienThoaiDienTu,txtGiaTopDienThoaiDienTu,txtGiamGiaTopDienThoaiDienTu;
        CardView cardView1;
        public ViewHolderTopDienThoai(View itemView) {
            super(itemView);
            imTopDienThoaiDienTu = itemView.findViewById(R.id.imTopDienThoaiDienTu);
            txtTieuDeTopDienThoaiDienTu = itemView.findViewById(R.id.txtTieuDeTopDienThoaiDienTu);
            txtGiaTopDienThoaiDienTu=itemView.findViewById(R.id.txtGiaTopDienThoaiDienTu);
            txtGiamGiaTopDienThoaiDienTu= itemView.findViewById(R.id.txtGiamGiaTopDienThoaiDienTu);
            cardView1 = itemView.findViewById(R.id.cardView_detailProduct);

        }
    }
    @Override
    public ViewHolderTopDienThoai onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layout,parent,false);
        ViewHolderTopDienThoai viewHolderTopDienThoai = new ViewHolderTopDienThoai(view);
        return viewHolderTopDienThoai;
    }

    @Override
    public void onBindViewHolder(ViewHolderTopDienThoai holder, int position) {
        SanPham sanPham = sanPhamList.get(position);
        Picasso.with(context).load(sanPham.getAnhLon()).resize(140,140).centerInside().into(holder.imTopDienThoaiDienTu);
        holder.txtTieuDeTopDienThoaiDienTu.setText(sanPham.getTENSP());

        ChiTietKhuyenMai chiTietKhuyenMai = sanPham.getChiTietKhuyenMai();
        int giatien = sanPham.getGIA();
        if (chiTietKhuyenMai != null){
            int phantramKm =chiTietKhuyenMai.getPHANTRAMKM();

            NumberFormat numberFormat = new DecimalFormat("###,###");
            String gia = numberFormat.format(sanPham.getGIA()).toString();

            holder.txtGiamGiaTopDienThoaiDienTu.setVisibility(View.VISIBLE);
            holder.txtGiamGiaTopDienThoaiDienTu.setPaintFlags(
                    holder.txtGiamGiaTopDienThoaiDienTu.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.txtGiamGiaTopDienThoaiDienTu.setText(gia + "VND");

            giatien = giatien*phantramKm/100;

        }

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(giatien);
        holder.txtGiaTopDienThoaiDienTu.setText(gia + "VND");
        holder.cardView1.setTag(sanPham.getMASP());

        holder.cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ichitietsanpham = new Intent(context, ChiTietSanPhamActivity.class);
                ichitietsanpham.putExtra("masp",(int) v.getTag());
                context.startActivity(ichitietsanpham);
            }
        });


    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

}
