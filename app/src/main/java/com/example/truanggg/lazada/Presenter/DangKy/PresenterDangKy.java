package com.example.truanggg.lazada.Presenter.DangKy;

import com.example.truanggg.lazada.Model.ModelDangNhap_DangKy.ModelDangKy;
import com.example.truanggg.lazada.Model.ObjectClass.NhanVien;
import com.example.truanggg.lazada.View.DangNhap_DangKy.ViewDangKy;

/**
 * Created by Truang on 12/18/2017.
 */

public class PresenterDangKy implements IPresenterDangKy {
    ViewDangKy viewDangKy;
    ModelDangKy modelDangKy;

    public PresenterDangKy(ViewDangKy viewDangKy){
        this.viewDangKy = viewDangKy;
        this.modelDangKy = new ModelDangKy();
    }
    @Override
    public void ThucHienDangKy(NhanVien nhanVien) {
        boolean kiemtra =modelDangKy.DangKyThanhVien(nhanVien);
        if (kiemtra){
            viewDangKy.DangKyThanhCong();
        }else {
            viewDangKy.DangKyThatBai();
        }
    }
}
