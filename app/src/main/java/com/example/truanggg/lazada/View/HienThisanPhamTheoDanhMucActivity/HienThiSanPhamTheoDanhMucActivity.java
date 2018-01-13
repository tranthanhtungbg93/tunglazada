package com.example.truanggg.lazada.View.HienThisanPhamTheoDanhMucActivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truanggg.lazada.Adapter.AdapterTopDienThoaiDientu;
import com.example.truanggg.lazada.Model.ObjectClass.ILoadMore;
import com.example.truanggg.lazada.Model.ObjectClass.LoadMoreSrcroll;
import com.example.truanggg.lazada.Model.ObjectClass.SanPham;
import com.example.truanggg.lazada.Presenter.ChiTietSanPham.PresnterLogicChiTietSanPham;
import com.example.truanggg.lazada.Presenter.HienThiSanPhamTheoDanhMuc.PresenterHienThiSanPhamTheoDanhMuc;
import com.example.truanggg.lazada.R;
import com.example.truanggg.lazada.View.GioHang.DanhSachSPGioHang;
import com.example.truanggg.lazada.View.TrangChuActivity;
import com.example.truanggg.lazada.View.ViewHienThiSPTheodanhMuc;

import java.util.List;

public class HienThiSanPhamTheoDanhMucActivity extends Fragment implements ViewHienThiSPTheodanhMuc,View.OnClickListener,ILoadMore{
    RecyclerView recyclerView;
    Button btnChangeState;
    boolean dangGrid = true ;
    int masp;
    boolean kiemtra;
    RecyclerView.LayoutManager layoutManager;
    PresenterHienThiSanPhamTheoDanhMuc sanPhamTheoDanhMuc;
    AdapterTopDienThoaiDientu adapterTopDienThoaiDientu;
    Toolbar toolbar;
    List<SanPham> sanPhamList1;
    ProgressBar progressBar;
    boolean onPause;
    TextView txtGioHang;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hien_thi_san_pham_theo_danh_muc_activity,container,false);

        setHasOptionsMenu(false);

        recyclerView = view.findViewById(R.id.recyclerSanPhamTheoDanhMuc);
        btnChangeState = view.findViewById(R.id.btnThayDoiTrangThai);
        toolbar = view.findViewById(R.id.toolbar);
        progressBar = view.findViewById(R.id.progress_bar);

        Bundle bundle = getArguments();
        masp = bundle.getInt("MALOAI",0);
        String tensp = bundle.getString("TENLOAI");
        kiemtra = bundle.getBoolean("KIEMTRA",false);

        sanPhamTheoDanhMuc = new PresenterHienThiSanPhamTheoDanhMuc(this);
        sanPhamTheoDanhMuc.LayDanhSachSP_theoMaLoai(masp,kiemtra);

        btnChangeState.setOnClickListener(this);
        toolbar.setTitle(tensp);

        //ép kiểu về AppCompatActivity
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar); // hàm khởi động toolbar
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true); // hàm khởi động toolbar
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled( true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack("TrangChuActivity", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menutrangchu,menu);
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem menuItem = menu.findItem(R.id.itGioHang);
        View GiaoDienCustomGioHang  = MenuItemCompat.getActionView(menuItem); // Lấy được lauout của custom giỏ hàng
        txtGioHang = GiaoDienCustomGioHang.findViewById(R.id.txtSoLuongSPGioHang);

        GiaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioHang = new Intent(getContext(), DanhSachSPGioHang.class);
                startActivity(iGioHang);
            }
        });

        PresnterLogicChiTietSanPham presnterLogicChiTietSanPham= new PresnterLogicChiTietSanPham();
        txtGioHang.setText(String.valueOf(presnterLogicChiTietSanPham.DemSanPhamTrongGioHang(getContext())));
    }

    @Override
    public void HienThiDanhSachSanPham(List<SanPham> sanPhamList) {
        sanPhamList1= sanPhamList;
        // sử dụng chung adapter
        if (dangGrid){
            layoutManager = new GridLayoutManager(getContext(), 2);
            adapterTopDienThoaiDientu = new AdapterTopDienThoaiDientu(
                    getContext(),R.layout.custom_recyclerview_topdienthoaivamaytinhbang,sanPhamList1);
        }else {
            layoutManager = new LinearLayoutManager(getContext());
            adapterTopDienThoaiDientu = new AdapterTopDienThoaiDientu(
                    getContext(),R.layout.custom_recyclerview_list_topdienthoaivamaytinhbang,sanPhamList1);
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTopDienThoaiDientu);
        recyclerView.addOnScrollListener(new LoadMoreSrcroll(layoutManager,this));// Load More
        adapterTopDienThoaiDientu.notifyDataSetChanged();
    }

    @Override
    public void LoiHienThi() {
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnThayDoiTrangThai:
                dangGrid = !dangGrid;
                sanPhamTheoDanhMuc.LayDanhSachSP_theoMaLoai(masp,kiemtra);
                break;
        }
    }


    @Override
    public void LoadMore(int tongitem) {
        List<SanPham> spLoadMore = sanPhamTheoDanhMuc.LayDanhSachSP_theoMaLoai_LoadMore(masp,kiemtra,tongitem,progressBar);
        sanPhamList1.addAll(spLoadMore);

        adapterTopDienThoaiDientu.notifyDataSetChanged();
    }
    @Override
    public void onResume() {
        super.onResume();
        if (onPause){
            PresnterLogicChiTietSanPham presnterLogicChiTietSanPham= new PresnterLogicChiTietSanPham();
            txtGioHang.setText(String.valueOf(presnterLogicChiTietSanPham.DemSanPhamTrongGioHang(getContext())));
        }else {

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        onPause = true;
    }
}
