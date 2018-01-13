package com.example.truanggg.lazada.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.truanggg.lazada.Model.ObjectClass.DienTu;
import com.example.truanggg.lazada.Presenter.TrangChu_DienTu.PresenterLogicDienTu;
import com.example.truanggg.lazada.R;
import com.example.truanggg.lazada.View.ViewDienTu;

import java.util.List;

/**
 * Created by Truang on 12/21/2017.
 */

public class AdapterDienTu extends RecyclerView.Adapter<AdapterDienTu.ViewHolderDienTu> {
    Context context;
    List<DienTu> dienTuList;

    public AdapterDienTu(Context context, List<DienTu> dienTuList){
        this.context = context;
        this.dienTuList = dienTuList;
    }
    @Override
    public ViewHolderDienTu onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_recyclerview_dientu,parent,false);
        ViewHolderDienTu viewHolderDienTu = new ViewHolderDienTu(view);
        return viewHolderDienTu;
    }

    @Override
    public void onBindViewHolder(ViewHolderDienTu holder, int position) {
        DienTu dienTu = dienTuList.get(position);

        holder.tenSanPhamNoiBat.setText(dienTu.getTenNoiBat().toString());
        holder.topSPNoiBat.setText(dienTu.getTenTopNoiBat().toString());
        //Adapter Hien Thi Danh Sach Thuong Hiue Lon (Recycleview Thuong hieu lon )
        AdapterThuongHieuLon adapterThuongHieuLon = new AdapterThuongHieuLon(context,dienTu.getThuongHieus(),dienTu.getThuonghieu());

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,3,GridLayoutManager.HORIZONTAL,false);

        holder.recyclerViewThuongHieuLon.setLayoutManager(layoutManager);
        holder.recyclerViewThuongHieuLon.setAdapter(adapterThuongHieuLon);
        adapterThuongHieuLon.notifyDataSetChanged();
        ///Adapter Hien Thi Danh Sach Top San Pham (Recycleview Top San Pham)
        AdapterTopDienThoaiDientu adapterTopDienThoaiDientu = new AdapterTopDienThoaiDientu(context,R.layout.custom_recyclerview_topdienthoaivamaytinhbang ,dienTu.getSanPhams());

        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false);

        holder.recyclerViewTopSanPhamr.setLayoutManager(layoutManager1);
        holder.recyclerViewTopSanPhamr.setAdapter(adapterTopDienThoaiDientu);
        adapterTopDienThoaiDientu.notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return dienTuList.size();
    }

    public class ViewHolderDienTu extends RecyclerView.ViewHolder {
        ImageView imhHinhGiamGia;
        RecyclerView recyclerViewThuongHieuLon,recyclerViewTopSanPhamr;
        TextView tenSanPhamNoiBat, topSPNoiBat;
        public ViewHolderDienTu(View itemView) {
            super(itemView);
            recyclerViewThuongHieuLon = itemView.findViewById(R.id.recyclerThuongHieuLon);
            recyclerViewTopSanPhamr = itemView.findViewById(R.id.recyclerTopDienThoaiMayTinhBang);
            imhHinhGiamGia = itemView.findViewById(R.id.imKhuyenMaiDienTu);
            tenSanPhamNoiBat= itemView.findViewById(R.id.txt_ten_san_pham_noibat);
            topSPNoiBat = itemView.findViewById(R.id.txt_top_san_pham_noibat);
        }
    }
}
