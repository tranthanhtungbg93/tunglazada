package com.example.truanggg.lazada.Presenter.DanhGia;

import android.widget.ProgressBar;

import com.example.truanggg.lazada.Model.ObjectClass.DanhGia;

/**
 * Created by Truang on 12/28/2017.
 */

public interface IPresenterLogicdanhGia {
    void ThemDanhGia(DanhGia danhGia);
    void LayDanhSachDanhGiaCuaSanPham(int masp, int limit, ProgressBar progressBar);
}
