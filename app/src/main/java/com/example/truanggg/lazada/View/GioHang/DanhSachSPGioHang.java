package com.example.truanggg.lazada.View.GioHang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.truanggg.lazada.Adapter.AdapterGioHang;
import com.example.truanggg.lazada.Model.ObjectClass.SanPham;
import com.example.truanggg.lazada.Presenter.GioHang.PresenterLogicGioHang;
import com.example.truanggg.lazada.R;
import com.example.truanggg.lazada.View.ThanhToan.ThanhToanActivity;

import java.util.List;

/**
 * Created by Truang on 12/30/2017.
 */

public class DanhSachSPGioHang extends AppCompatActivity implements ViewGioHang,View.OnClickListener{

    RecyclerView recyclerViewGioHang;
    PresenterLogicGioHang presenterLogicGioHang ;
    Toolbar toolbar;
    Button btnMuaNgay;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang);
        recyclerViewGioHang = findViewById(R.id.recycler_GioHang);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenterLogicGioHang= new PresenterLogicGioHang(this);
        presenterLogicGioHang.LaydanhSachSPTrongGioHang(this);

        btnMuaNgay = findViewById(R.id.btnMuaNgay);
        btnMuaNgay.setOnClickListener(this);
    }

    @Override
    public void HienThidanhSachTRongGioHang(List<SanPham> sanPhamList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        AdapterGioHang adapterGioHang = new AdapterGioHang(this,sanPhamList);
        recyclerViewGioHang.setLayoutManager(layoutManager);
        recyclerViewGioHang.setAdapter(adapterGioHang);
        adapterGioHang.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnMuaNgay:
                Intent iMuaNgay = new Intent(DanhSachSPGioHang.this, ThanhToanActivity.class);
                startActivity(iMuaNgay);
                break;
        }
    }
}
