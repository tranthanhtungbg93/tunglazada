package com.example.truanggg.lazada.Presenter.DanhGia;

import android.view.View;
import android.widget.ProgressBar;

import com.example.truanggg.lazada.Model.DanhGia.ModelDanhGia;
import com.example.truanggg.lazada.Model.ModelDangNhap_DangKy.ModelDangNhap;
import com.example.truanggg.lazada.Model.ObjectClass.DanhGia;
import com.example.truanggg.lazada.Presenter.ChiTietSanPham.IPresenterChiTietSanPham;
import com.example.truanggg.lazada.View.DanhGia.ViewDanhGia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Truang on 12/28/2017.
 */

public class PresenterLogicDanhGia implements IPresenterLogicdanhGia {
    ViewDanhGia viewDanhGia;
    ModelDanhGia modelDanhGia;
    public PresenterLogicDanhGia(ViewDanhGia viewDanhGia) {
        this.viewDanhGia = viewDanhGia;
        modelDanhGia= new ModelDanhGia();
    }

    @Override
    public void ThemDanhGia(DanhGia danhGia) {
        boolean kiemtra = modelDanhGia.ThemDanhGia(danhGia);
        if (kiemtra){
            viewDanhGia.DanhGiaThanhCong();
        }else {
            viewDanhGia.DanhGiathatBai();
        }
    }

    @Override
    public void LayDanhSachDanhGiaCuaSanPham(int masp, int limit, ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
        List<DanhGia> danhGias = modelDanhGia.LayDanhSachdanhGiaCuaSanPham(masp,limit);

        if(danhGias.size()>0){
            progressBar.setVisibility(View.GONE);
            viewDanhGia.HienThiDanhSachDanhGia(danhGias);
        }
    }
}
