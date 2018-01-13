package com.example.truanggg.lazada.View;

import com.example.truanggg.lazada.Model.ObjectClass.DanhGia;
import com.example.truanggg.lazada.Model.ObjectClass.SanPham;

import java.util.List;

/**
 * Created by Truang on 12/27/2017.
 */

public interface ViewChiTietSanPham {
    void HienThichiTietSanPham(SanPham sanPham);
    void HienthiSlider(String[] linkhinhSP);
    void HienThiDanhGia(List<DanhGia> danhGias );
    void ThemGioHangThanhCong();
    void ThemGioHangThatBai();

}
