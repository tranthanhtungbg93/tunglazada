package com.example.truanggg.lazada.Presenter.TrangChu_DienTu;

import android.util.Log;
import android.view.View;

import com.example.truanggg.lazada.Model.ObjectClass.DienTu;
import com.example.truanggg.lazada.Model.ObjectClass.SanPham;
import com.example.truanggg.lazada.Model.ObjectClass.ThuongHieu;
import com.example.truanggg.lazada.Model.TrangChu_DienTu.ModelDienTu;
import com.example.truanggg.lazada.View.ViewDienTu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Truang on 12/21/2017.
 */

public class PresenterLogicDienTu implements IPresenterDientu{

    ViewDienTu viewDienTu;
    ModelDienTu modelDienTu;
    public PresenterLogicDienTu(ViewDienTu viewDienTu){
        this.viewDienTu = viewDienTu;
        modelDienTu = new ModelDienTu();
    }

    @Override
    public void LaydanhSachDienTu() {
        List<DienTu> dienTuList = new ArrayList<>();
        List<ThuongHieu> thuongHieuList = modelDienTu.LayDanhSachThuongHieu("LayDanhSachCacThuongHieuLon","DANHSACHTHUONGHIEU");
        List<SanPham> sanPhamList = modelDienTu.LayDanhSachPhamTOP("LayDanhSachTopDienThoaiVaMayTinhBang","TOPDIENTHOAI&MAYTINHBANG");

        DienTu dienTu = new DienTu();
        dienTu.setThuongHieus(thuongHieuList);
        dienTu.setSanPhams(sanPhamList);
        dienTu.setTenNoiBat("Danh Sách Thương Hiệu Lớn");
        dienTu.setTenTopNoiBat("Top Điện Thoai và Máy Tính Bảng");
        dienTu.setThuonghieu(true);
        dienTuList.add(dienTu);

        List<SanPham> phukienList = modelDienTu.LayDanhSachPhamTOP("LayDanhSachTopPhuKien","TOPPHUKIEN");
        List<ThuongHieu> TopPhuKienList = modelDienTu.LayDanhSachThuongHieu("LayDanhSachPhuKien","DANHSACHPHUKIEN");

        DienTu dienTu1 = new DienTu();
        dienTu1.setThuongHieus(TopPhuKienList);
        dienTu1.setSanPhams(phukienList);
        dienTu1.setTenNoiBat("Phụ Kiện");
        dienTu1.setTenTopNoiBat("Top Phụ Kiện");
        dienTu1.setThuonghieu(false);
        dienTuList.add(dienTu1);

        List<SanPham> TienIchList = modelDienTu.LayDanhSachPhamTOP("LayTopTienIch","TOPTIENICH");
        List<ThuongHieu> TopTienIchList = modelDienTu.LayDanhSachThuongHieu("LayDanhSachTienIch","DANHSACHTIENICH");

        DienTu dienTu2 = new DienTu();
        dienTu2.setThuongHieus(TopTienIchList);
        dienTu2.setSanPhams(TienIchList);
        dienTu2.setTenNoiBat("Tiện Ích");
        dienTu2.setTenTopNoiBat("Top Video & Tivi");
        dienTu2.setThuonghieu(false);
        dienTuList.add(dienTu2);
        if (thuongHieuList.size() > 0 && sanPhamList.size()>0){
            viewDienTu.HienThiDanhSach(dienTuList);
        }
    }

    @Override
    public void LayDanhSachLogoThuongHieu() {
        List<ThuongHieu> thuongHieuList = modelDienTu.LayDanhSachThuongHieu("LayLogoThuongHieuLon","DANHSACHTHUONGHIEU");
        if (thuongHieuList.size() > 0 ){
            viewDienTu.HienThiLogoThuongHieu(thuongHieuList);
        }else {
            viewDienTu.LoiLayDuLieu();
        }
    }

    @Override
    public void HangMoiveDienTu() {
        List<SanPham> sanPhams = modelDienTu.LayDanhSachPhamTOP("LayDanhSachSanPhamMoi","DANHSACHSANPHAMMOIVE");
        if (sanPhams.size() > 0 ){
            viewDienTu.HienThiHangMoive(sanPhams);
        }else {
            viewDienTu.LoiLayDuLieu();
        }
    }
}
