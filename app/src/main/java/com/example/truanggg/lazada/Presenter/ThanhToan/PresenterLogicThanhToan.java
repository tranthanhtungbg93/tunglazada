package com.example.truanggg.lazada.Presenter.ThanhToan;

import android.content.Context;

import com.example.truanggg.lazada.Model.GioHang.ModelGioHang;
import com.example.truanggg.lazada.Model.ObjectClass.HoaDon;
import com.example.truanggg.lazada.Model.ObjectClass.SanPham;
import com.example.truanggg.lazada.Model.ThanhToan.ModelThanhToan;
import com.example.truanggg.lazada.View.ThanhToan.ViewThanhToan;

import java.util.List;

/**
 * Created by Truang on 12/31/2017.
 **/

public class  PresenterLogicThanhToan implements IPresenterThanhToan {

    ViewThanhToan viewThanhToan;
    ModelThanhToan modelThanhToan;
    ModelGioHang modelGioHang;
    List<SanPham> sanPhamList;
    public PresenterLogicThanhToan(ViewThanhToan viewThanhToan,Context context) {
        this.viewThanhToan = viewThanhToan;
        modelThanhToan = new ModelThanhToan();
        modelGioHang = new ModelGioHang();
        modelGioHang.MoKetNoiSQL(context);
    }

    @Override
    public void ThemThanhToan(HoaDon hoaDon) {
        boolean kiemtra = modelThanhToan.ThemHoaDon(hoaDon);
        if (kiemtra){
            viewThanhToan.DatHangThanhCong();

            int dem = sanPhamList.size();
            for (int i = 0; i < dem; i++){
                modelGioHang.XoaGioHang(sanPhamList.get(i).getMASP());
            }
        }else {
            viewThanhToan.DatHangThatBai();
        }
    }

    @Override
    public void LayDanhSachSPtrongGioHang() {

        sanPhamList = modelGioHang.LaydanhSachSPTrongGioHang();
        viewThanhToan.LayDanhSachSpTrongGioHang(sanPhamList);
    }
}
