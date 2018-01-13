package com.example.truanggg.lazada.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truanggg.lazada.Model.GioHang.ModelGioHang;
import com.example.truanggg.lazada.Model.ObjectClass.SanPham;
import com.example.truanggg.lazada.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Truang on 12/30/2017.
 **/

public class AdapterGioHang extends RecyclerView.Adapter<AdapterGioHang.ViewHolderGioHang> {

    Context context;
    List<SanPham> sanPhamList;
    ModelGioHang modelGioHang;
    public AdapterGioHang(Context context, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        modelGioHang = new ModelGioHang();
        modelGioHang.MoKetNoiSQL(context);
    }

    @Override
    public ViewHolderGioHang onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_giohang,parent,false);
        ViewHolderGioHang viewHolderGioHang = new ViewHolderGioHang(view);
        return viewHolderGioHang;
    }

    @Override
    public void onBindViewHolder(final ViewHolderGioHang holder, final int position) {
        final SanPham sanPham = sanPhamList.get(position);
        holder.txtTenTieuDeGioHang.setText(sanPham.getTENSP());
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGIA()).toString();
        holder.txtGiaGioHang.setText(gia + " VND");
        // gọi hình sản phẩm về kiểu byte (khai báo kiểu byte bằng bitmap)

        byte[] hinhSP = sanPham.getHINHGIOHANG();
        Bitmap bitmapHinhGioHang = BitmapFactory.decodeByteArray(hinhSP,0,hinhSP.length);
        holder.imHinhGioHang.setImageBitmap(bitmapHinhGioHang);

        holder.imXoaGioHang.setTag(sanPham.getMASP());

        holder.imXoaGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelGioHang modelGioHang = new ModelGioHang();
                modelGioHang.MoKetNoiSQL(context);
                modelGioHang.XoaGioHang((int)v.getTag());
                sanPhamList.remove(position);
                notifyDataSetChanged();
                Log.d("kiemtra", (int)v.getTag() +"");
            }
        });
        holder.txtSoLuong.setText(String.valueOf(sanPham.getSoLuong()));

        holder.imAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(holder.txtSoLuong.getText().toString());
                int soluongtonkho = sanPham.getSOLUONGTONKHO();
                if (soluong < soluongtonkho){
                    soluong ++;
                }else {
                    Toast.makeText(context, "Số lượng bạn mua sản phẩm quá số lượng trong kho", Toast.LENGTH_SHORT).show();
                }
                modelGioHang.CapNhatSoLuongTrongGioHang(sanPham.getMASP(),soluong);
                holder.txtSoLuong.setText(String.valueOf(soluong));
            }
        });
        holder.imRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(holder.txtSoLuong.getText().toString());
                if (soluong > 1){
                    soluong --;
                }
                modelGioHang.CapNhatSoLuongTrongGioHang(sanPham.getMASP(),soluong);
                holder.txtSoLuong.setText(String.valueOf(soluong));
            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public class ViewHolderGioHang extends RecyclerView.ViewHolder {
        TextView txtTenTieuDeGioHang,txtGiaGioHang,txtSoLuong;
        ImageView imHinhGioHang, imXoaGioHang;
        ImageButton imRemove,imAdd;
        public ViewHolderGioHang(View itemView) {
            super(itemView);
            txtTenTieuDeGioHang = itemView.findViewById(R.id.txtTenSP);
            txtGiaGioHang = itemView.findViewById(R.id.txtGiaSP);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
            imHinhGioHang = itemView.findViewById(R.id.imHinhGioHang);
            imXoaGioHang = itemView.findViewById(R.id.imXoaSP);
            imRemove = itemView.findViewById(R.id.imRemove);
            imAdd = itemView.findViewById(R.id.imAdd);
        }
    }
}
