package com.example.truanggg.lazada.Presenter.ChiTietSanPham;

import android.content.Context;

import com.example.truanggg.lazada.Model.ChiTietSanPham.ModelChiTietSanPham;
import com.example.truanggg.lazada.Model.GioHang.ModelGioHang;
import com.example.truanggg.lazada.Model.ObjectClass.DanhGia;
import com.example.truanggg.lazada.Model.ObjectClass.SanPham;
import com.example.truanggg.lazada.View.ViewChiTietSanPham;

import java.util.List;

/**
 * Created by Truang on 12/27/2017.
 */

public class PresnterLogicChiTietSanPham implements IPresenterChiTietSanPham {

    ViewChiTietSanPham viewChiTietSanPham;
    ModelChiTietSanPham modelChiTietSanPham;
    ModelGioHang modelGioHang;

    public PresnterLogicChiTietSanPham() {
        modelGioHang = new ModelGioHang();
    }

    public PresnterLogicChiTietSanPham (ViewChiTietSanPham viewChiTietSanPham){
        this.viewChiTietSanPham = viewChiTietSanPham;
        modelChiTietSanPham = new ModelChiTietSanPham();
        modelGioHang = new ModelGioHang();
    }
    @Override
    public void LayChiTietSanPham(int masp) {
        SanPham sanPham = modelChiTietSanPham.LayChiTietSanPham("LaySanPhamVsChitietTheoMaSP"
                ,"CHITIETSANPHAM",masp);

        if (sanPham.getMASP() > 0 ){
            String[] linkHinhAnh = sanPham.getAnhNho().split(",");// thủ thuật cắt chuỗi đên dấu "," nó sẽ cắt lưu vào mảng (1,2,3...)
            viewChiTietSanPham.HienthiSlider(linkHinhAnh);
            viewChiTietSanPham.HienThichiTietSanPham(sanPham);
        }
    }

    @Override
    public void LayDanhSachDanhGiaCuaSanPham(int masp, int limit) {
        List<DanhGia> danhGias = modelChiTietSanPham.LayDanhSachdanhGiaCuaSanPham(masp,limit);

        if (danhGias.size() > 0 ){
            viewChiTietSanPham.HienThiDanhGia(danhGias);
        }
    }

    @Override
    public void ThemGioHang(SanPham sanPham, Context context) {
        modelGioHang.MoKetNoiSQL(context);
        boolean kiemtra = modelGioHang.themGioHang(sanPham);
        if (kiemtra){
            viewChiTietSanPham.ThemGioHangThanhCong();
        }else {
            viewChiTietSanPham.ThemGioHangThatBai();
        }
    }
    public int DemSanPhamTrongGioHang(Context context){
        modelGioHang.MoKetNoiSQL(context);
       List<SanPham> sanPhamList = modelGioHang.LaydanhSachSPTrongGioHang();

       int dem = sanPhamList.size();

       return dem;
    }
}
