package com.example.truanggg.lazada.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.truanggg.lazada.Model.ObjectClass.KhuyenMai;
import com.example.truanggg.lazada.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Truang on 1/1/2018.
 */

public class AdapterDSKM extends RecyclerView.Adapter<AdapterDSKM.ViewHolderKM> {

    Context context;
    List<KhuyenMai> khuyenMais;

    public AdapterDSKM(Context context, List<KhuyenMai> khuyenMais) {
        this.context = context;
        this.khuyenMais = khuyenMais;
    }

    @Override
    public ViewHolderKM onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_itemkhuyenmai,parent,false);
        ViewHolderKM viewHolderKM = new ViewHolderKM(view);
        return viewHolderKM;
    }

    @Override
    public void onBindViewHolder(ViewHolderKM holder, int position) {
        KhuyenMai khuyenMai = khuyenMais.get(position);
        holder.txtTieuDeKM.setText(khuyenMai.getTENLOAISP());
        AdapterTopDienThoaiDientu adapterTopDienThoaiDientu = new AdapterTopDienThoaiDientu(context,
                R.layout.custom_recyclerview_topdienthoaivamaytinhbang,khuyenMai.getSanPhamListKM());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL,false);
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setAdapter(adapterTopDienThoaiDientu);

    }

    @Override
    public int getItemCount() {
        return khuyenMais.size();
    }

    public class ViewHolderKM extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        TextView txtTieuDeKM;
        public ViewHolderKM(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recycler_itemKM);
            txtTieuDeKM = itemView.findViewById(R.id.txtTieuDeKM);
        }
    }
}
