package com.example.truanggg.lazada.View.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.truanggg.lazada.Adapter.AdapterDienTu;
import com.example.truanggg.lazada.Adapter.AdapterThuongHieuLonDienTu;
import com.example.truanggg.lazada.Adapter.AdapterTopDienThoaiDientu;
import com.example.truanggg.lazada.Model.ObjectClass.DienTu;
import com.example.truanggg.lazada.Model.ObjectClass.SanPham;
import com.example.truanggg.lazada.Model.ObjectClass.ThuongHieu;
import com.example.truanggg.lazada.Presenter.ChiTietSanPham.PresnterLogicChiTietSanPham;
import com.example.truanggg.lazada.Presenter.TrangChu_DienTu.PresenterLogicDienTu;
import com.example.truanggg.lazada.R;
import com.example.truanggg.lazada.View.ViewDienTu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class DienTuFragment extends Fragment implements ViewDienTu {
    RecyclerView recyclerView,recyclerViewlogo,recyclerViewHangMoiVe;
    List<DienTu> dienTuList;
    PresenterLogicDienTu presenterLogicDienTu;
    ImageView imageView1,imageView2,imageView3;
    TextView textView1,textView2,textView3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_dien_tu, container, false);
        init(view);
        dienTuList = new ArrayList<>();
        presenterLogicDienTu = new PresenterLogicDienTu(this);

        presenterLogicDienTu.LaydanhSachDienTu();
        presenterLogicDienTu.LayDanhSachLogoThuongHieu();
        presenterLogicDienTu.HangMoiveDienTu();

        return view;
    }
    public void init(View view){
        recyclerView = view.findViewById(R.id.recyclerViewDienTu);
        recyclerViewlogo = view.findViewById(R.id.recyclerLogoThuongHieu);
        recyclerViewHangMoiVe = view.findViewById(R.id.recyclerHangMoive);
        imageView1= view.findViewById(R.id.imSanpham1);
        imageView2= view.findViewById(R.id.imSanpham2);
        imageView3= view.findViewById(R.id.imSanpham3);
        textView1= view.findViewById(R.id.txt_san_pham1);
        textView2= view.findViewById(R.id.txt_san_pham2);
        textView3= view.findViewById(R.id.txt_san_pham3);
    }
    @Override
    public void HienThiDanhSach(List<DienTu> dienTus) {

        dienTuList = dienTus;

        AdapterDienTu adapterDienTu = new AdapterDienTu(getContext(),dienTuList);

        RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterDienTu);

        adapterDienTu.notifyDataSetChanged();

    }

    @Override
    public void HienThiLogoThuongHieu(List<ThuongHieu> thuongHieuList) {
        AdapterThuongHieuLonDienTu adapterlogo = new AdapterThuongHieuLonDienTu(getContext(),thuongHieuList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.HORIZONTAL,false);

        recyclerViewlogo.setLayoutManager(layoutManager);
        recyclerViewlogo.setAdapter(adapterlogo);
        adapterlogo.notifyDataSetChanged();

    }

    @Override
    public void LoiLayDuLieu() {

    }

    @Override
    public void HienThiHangMoive(List<SanPham> sanPhamList) {
        // tái sử dụng AdapterTopDienThoaiDientu
        AdapterTopDienThoaiDientu adapterTopDienThoaiDientu = new AdapterTopDienThoaiDientu(getContext(),R.layout.custom_recyclerview_topdienthoaivamaytinhbang ,sanPhamList);

        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);

        recyclerViewHangMoiVe.setLayoutManager(layoutManager1);
        recyclerViewHangMoiVe.setAdapter(adapterTopDienThoaiDientu);
        adapterTopDienThoaiDientu.notifyDataSetChanged();

        Random random = new Random();

        int vitri = random.nextInt(sanPhamList.size());
        Picasso.with(getContext()).load(sanPhamList.get(vitri).getAnhLon()).fit().centerInside().into(imageView1);
        textView1.setText(sanPhamList.get(vitri).getTENSP());

        int vitri1 = random.nextInt(sanPhamList.size());
        Picasso.with(getContext()).load(sanPhamList.get(vitri1).getAnhLon()).fit().centerInside().into(imageView2);
        textView2.setText(sanPhamList.get(vitri1).getTENSP());

        int vitri2 = random.nextInt(sanPhamList.size());
        Picasso.with(getContext()).load(sanPhamList.get(vitri2).getAnhLon()).fit().centerInside().into(imageView3);
        textView3.setText(sanPhamList.get(vitri2).getTENSP());
    }
}
