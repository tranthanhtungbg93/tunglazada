package com.example.truanggg.lazada.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.truanggg.lazada.Model.ObjectClass.DanhGia;
import com.example.truanggg.lazada.R;

import java.util.List;

/**
 * Created by Truang on 12/28/2017.
 **/

public class AdapterDanhGia extends RecyclerView.Adapter<AdapterDanhGia.ViewHolderDanhGia> {
    List<DanhGia> danhGias;
    int limit;
    Context context;
    public AdapterDanhGia(Context context, List<DanhGia> danhGias, int limit) {
        this.danhGias = danhGias;
        this.limit = limit;
        this.context = context;
    }

    @Override
    public ViewHolderDanhGia onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_recycler_danhgia_chitiet,parent,false);
        ViewHolderDanhGia viewHolderDanhGia = new ViewHolderDanhGia(view);
        return viewHolderDanhGia;
    }

    @Override
    public void onBindViewHolder(ViewHolderDanhGia holder, int position) {
        DanhGia danhGia = danhGias.get(position);

        holder.txtTieuDe.setText(danhGia.getTIEUDE());
        holder.txtNoiDung.setText(danhGia.getNOIDUNG());
        holder.txtduocDanhgiaBoi.setText("Được Đánh giá bởi " + danhGia.getTENTHIETBI() + " ngày "+ danhGia.getNGAYDANHGIA());
        holder.rbDanhGia.setRating(danhGia.getSOSAO());

        Log.d("tag",danhGia.getNOIDUNG()+ "-"+danhGia.getTIEUDE()+ "-"+danhGia.getTENTHIETBI()+ "-"
        +danhGia.getNGAYDANHGIA()+ "-"+danhGia.getSOSAO());
    }

    @Override
    public int getItemCount() {
        int dong = 0;
        if(danhGias.size() < limit){
            dong = danhGias.size();
        }else {
            if (limit != 0 ){
                dong =limit;
            }else {
                dong = danhGias.size();
            }
        }
        return dong;
    }

    public class ViewHolderDanhGia extends RecyclerView.ViewHolder {
        TextView txtTieuDe,txtNoiDung,txtduocDanhgiaBoi;
        RatingBar rbDanhGia;
        public ViewHolderDanhGia(View itemView) {
            super(itemView);
            txtTieuDe = itemView.findViewById(R.id.txtTieuDeDanhGia);
            txtNoiDung = itemView.findViewById(R.id.txtNoiDungDanhGia);
            txtduocDanhgiaBoi = itemView.findViewById(R.id.txtDuocDangBoi);
            rbDanhGia = itemView.findViewById(R.id.rbDanhGia);
        }
    }
}
