package com.example.truanggg.lazada.Presenter.GioHang;

import android.content.Context;

import com.example.truanggg.lazada.Model.GioHang.ModelGioHang;
import com.example.truanggg.lazada.Model.ObjectClass.SanPham;
import com.example.truanggg.lazada.Presenter.DanhGia.IPresenterLogicdanhGia;
import com.example.truanggg.lazada.View.GioHang.ViewGioHang;

import java.util.List;

/**
 * Created by Truang on 12/30/2017.
 */

public class PresenterLogicGioHang implements IPresenterGioHang{

    ModelGioHang modelGioHang;
    ViewGioHang viewGioHang;
    public PresenterLogicGioHang(ViewGioHang viewGioHang) {
        modelGioHang = new ModelGioHang();
        this.viewGioHang = viewGioHang;
    }

    @Override
    public void LaydanhSachSPTrongGioHang(Context context) {
        modelGioHang.MoKetNoiSQL(context);
        List<SanPham> sanPhamList = modelGioHang.LaydanhSachSPTrongGioHang();
        if (sanPhamList.size()>0){
            viewGioHang.HienThidanhSachTRongGioHang(sanPhamList);
        }

    }
}
