package com.example.truanggg.lazada.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.truanggg.lazada.Model.ObjectClass.ThuongHieu;
import com.example.truanggg.lazada.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Truang on 12/23/2017.
 **/

public class AdapterThuongHieuLonDienTu extends RecyclerView.Adapter<AdapterThuongHieuLonDienTu.ViewHolderThuongHieuLon> {
    Context context;
    List<ThuongHieu> thuongHieuList;

    public AdapterThuongHieuLonDienTu(Context context, List<ThuongHieu> thuongHieuList) {
        this.context = context;
        this.thuongHieuList = thuongHieuList;
    }

    @Override
    public int getItemCount() {
        return thuongHieuList.size();
    }

    public class ViewHolderThuongHieuLon extends RecyclerView.ViewHolder {
        ImageView imThuongHieuLon;
        public ViewHolderThuongHieuLon(View itemView) {
            super(itemView);
            imThuongHieuLon = itemView.findViewById(R.id.imLogoThuongHieuLon);
        }
    }

    @Override
    public ViewHolderThuongHieuLon onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_recycleview_thuonghieulondientu,parent,false);
        ViewHolderThuongHieuLon viewHolderThuongHieuLon =new ViewHolderThuongHieuLon(view);
        return viewHolderThuongHieuLon;
    }

    @Override
    public void onBindViewHolder(ViewHolderThuongHieuLon holder, int position) {
        ThuongHieu thuongHieu = thuongHieuList.get(position);
        Picasso.with(context).load(thuongHieu.getHINHTHUONGHIEU()).resize(180,90).centerInside().into(holder.imThuongHieuLon);

    }
}
