package com.example.truanggg.lazada.View.ThanhToan;

import com.example.truanggg.lazada.Model.ObjectClass.SanPham;

import java.util.List;

/**
 * Created by Truang on 12/31/2017.
 */

public interface ViewThanhToan {
    void DatHangThanhCong();
    void DatHangThatBai();
    void LayDanhSachSpTrongGioHang(List<SanPham> sanPhamList);
}
