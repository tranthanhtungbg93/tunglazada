package com.example.truanggg.lazada.Presenter.TiemKiem;

import com.example.truanggg.lazada.Model.ObjectClass.SanPham;
import com.example.truanggg.lazada.Model.TimKiem.ModelTimKiem;
import com.example.truanggg.lazada.View.ViewTimKiem;

import java.util.List;

/**
 * Created by Truang on 1/1/2018.
 */

public class PresenterlogicTimKiem implements IPresenterTimKiem{
    ViewTimKiem viewTimKiem;
    ModelTimKiem modelTimKiem;

    public PresenterlogicTimKiem(ViewTimKiem viewTimKiem) {
        this.viewTimKiem = viewTimKiem;
        modelTimKiem = new ModelTimKiem();
    }

    @Override
    public void TimKiemSanPhamTheoTenSp(String tensp, int limit) {
        List<SanPham> sanPhamList = modelTimKiem.TimKiemSanPhamTheoTen
                ("TimKiemSanPhamTheoTenSP","DANHSACHSANPHAM",tensp,limit);
        if (sanPhamList.size()> 0 ){
            viewTimKiem.TimKiemThanhCong(sanPhamList);
        }else {
            viewTimKiem.TimKiemThatBai();
        }
    }
}
