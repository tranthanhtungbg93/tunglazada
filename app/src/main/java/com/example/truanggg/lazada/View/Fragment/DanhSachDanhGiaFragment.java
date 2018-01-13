package com.example.truanggg.lazada.View.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.truanggg.lazada.Adapter.AdapterDanhGia;
import com.example.truanggg.lazada.Model.ObjectClass.DanhGia;
import com.example.truanggg.lazada.Model.ObjectClass.ILoadMore;
import com.example.truanggg.lazada.Model.ObjectClass.LoadMoreSrcroll;
import com.example.truanggg.lazada.Presenter.DanhGia.PresenterLogicDanhGia;
import com.example.truanggg.lazada.R;
import com.example.truanggg.lazada.View.DanhGia.ViewDanhGia;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DanhSachDanhGiaFragment extends Fragment implements ViewDanhGia,ILoadMore{

    RecyclerView recycler_danhsachdanhgia;
    ProgressBar progressBar;
    PresenterLogicDanhGia presenterLogicDanhGia;
    List<DanhGia> AllDanhGia;
    int masp = 0;
    public DanhSachDanhGiaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_danh_sach_danh_gia, container, false);
        recycler_danhsachdanhgia = view.findViewById(R.id.recycler_danhsachDanhGia);
        progressBar = view.findViewById(R.id.progress_bar);

        Bundle bundle = getArguments();
        masp = bundle.getInt("masp",0);

        AllDanhGia = new ArrayList<>();

        presenterLogicDanhGia = new PresenterLogicDanhGia(this);
        presenterLogicDanhGia.LayDanhSachDanhGiaCuaSanPham(masp,0,progressBar);


        return view;
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
        AdapterDanhGia adapterDanhGia = new AdapterDanhGia(getContext(),AllDanhGia,0);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recycler_danhsachdanhgia.setLayoutManager(layoutManager);
        recycler_danhsachdanhgia.setAdapter(adapterDanhGia);
        recycler_danhsachdanhgia.addOnScrollListener(new LoadMoreSrcroll(layoutManager,this));
        adapterDanhGia.notifyDataSetChanged();
    }

    @Override
    public void LoadMore(int tongitem) {
        presenterLogicDanhGia.LayDanhSachDanhGiaCuaSanPham(masp,tongitem,progressBar);
    }
}
