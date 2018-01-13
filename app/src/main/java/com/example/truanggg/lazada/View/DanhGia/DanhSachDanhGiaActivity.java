package com.example.truanggg.lazada.View.DanhGia;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.example.truanggg.lazada.Adapter.AdapterDanhGia;
import com.example.truanggg.lazada.Model.ObjectClass.DanhGia;
import com.example.truanggg.lazada.Model.ObjectClass.ILoadMore;
import com.example.truanggg.lazada.Model.ObjectClass.LoadMoreSrcroll;
import com.example.truanggg.lazada.Presenter.DanhGia.PresenterLogicDanhGia;
import com.example.truanggg.lazada.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Truang on 12/29/2017.
 */

public class DanhSachDanhGiaActivity extends AppCompatActivity implements ViewDanhGia,ILoadMore{
    RecyclerView recycler_danhsachdanhgia;
    ProgressBar progressBar;
    PresenterLogicDanhGia presenterLogicDanhGia;
    List<DanhGia> AllDanhGia;
    int masp = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhsach_danhgia);
        recycler_danhsachdanhgia = findViewById(R.id.recycler_danhsachDanhGia);
        progressBar = findViewById(R.id.progress_bar);

        masp = getIntent().getIntExtra("masp",0);

        AllDanhGia = new ArrayList<>();
        presenterLogicDanhGia = new PresenterLogicDanhGia(this);
        presenterLogicDanhGia.LayDanhSachDanhGiaCuaSanPham(masp,0,progressBar);


    }

    @Override
    public void LoadMore(int tongitem) {

    }

    @Override
    public void DanhGiaThanhCong() {

    }

    @Override
    public void DanhGiathatBai() {

    }

    @Override
    public void HienThiDanhSachDanhGia(List<DanhGia> danhGias) {
        AllDanhGia.addAll(danhGias);
        AdapterDanhGia adapterDanhGia = new AdapterDanhGia(this,AllDanhGia,0);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_danhsachdanhgia.setLayoutManager(layoutManager);
        recycler_danhsachdanhgia.setAdapter(adapterDanhGia);
        recycler_danhsachdanhgia.addOnScrollListener(new LoadMoreSrcroll(layoutManager,this));
        adapterDanhGia.notifyDataSetChanged();
    }
}
