package com.example.truanggg.lazada.Presenter.ChiTietSanPham;

import android.content.Context;

import com.example.truanggg.lazada.Model.ObjectClass.SanPham;

/**
 * Created by Truang on 12/27/2017.
 */

public interface IPresenterChiTietSanPham {
    void LayChiTietSanPham(int masp);
    void LayDanhSachDanhGiaCuaSanPham(int masp, int limit);
    void ThemGioHang(SanPham sanPham, Context context);

}
