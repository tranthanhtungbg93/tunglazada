package com.example.truanggg.lazada.Presenter.KhuyenMai;

import com.example.truanggg.lazada.Model.KhuyenMai.ModelKhuyenMai;
import com.example.truanggg.lazada.Model.ObjectClass.KhuyenMai;
import com.example.truanggg.lazada.View.ViewKhuyenMai;

import java.util.List;

/**
 * Created by Truang on 1/1/2018.
 */

public class PresenterLogicKM implements IPresenterKm {

    ViewKhuyenMai viewKhuyenMai;
    ModelKhuyenMai modelKhuyenMai;

    public PresenterLogicKM(ViewKhuyenMai viewKhuyenMai) {
        this.viewKhuyenMai = viewKhuyenMai;
        modelKhuyenMai= new ModelKhuyenMai();
    }

    @Override
    public void LayDanhSachSPKM() {
        List<KhuyenMai> khuyenMaiList = modelKhuyenMai.LayDanhSachSPKM("LayDanhSachKhuyenMai","DANHSACHKHUYENMAI");
        if (khuyenMaiList.size() > 0){
            viewKhuyenMai.HienThidanhSachKM(khuyenMaiList);
        }
    }
}
