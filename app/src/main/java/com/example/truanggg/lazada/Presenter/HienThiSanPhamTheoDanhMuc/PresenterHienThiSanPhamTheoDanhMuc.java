package com.example.truanggg.lazada.Presenter.HienThiSanPhamTheoDanhMuc;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.truanggg.lazada.Model.HienThiSanPhamTheoDanhMuc.ModelHienThiSanPhamTheoDanhMuc;
import com.example.truanggg.lazada.Model.ObjectClass.LoaiSanPham;
import com.example.truanggg.lazada.Model.ObjectClass.SanPham;
import com.example.truanggg.lazada.View.ViewHienThiSPTheodanhMuc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Truang on 12/24/2017.
 **/

public class PresenterHienThiSanPhamTheoDanhMuc implements IPresenterHienThiSanPhamTheoDanhMuc{

    ViewHienThiSPTheodanhMuc viewHienThiSPTheodanhMuc;
    ModelHienThiSanPhamTheoDanhMuc modelHienThiSanPhamTheoDanhMuc;

    public PresenterHienThiSanPhamTheoDanhMuc(ViewHienThiSPTheodanhMuc viewHienThiSPTheodanhMuc){
            this.viewHienThiSPTheodanhMuc = viewHienThiSPTheodanhMuc;
            modelHienThiSanPhamTheoDanhMuc = new ModelHienThiSanPhamTheoDanhMuc();
    }
    @Override
    public void LayDanhSachSP_theoMaLoai(int masp,boolean kiemtra) {
        List<SanPham> sanPhamList = new ArrayList<>();
        if (kiemtra){
            sanPhamList = modelHienThiSanPhamTheoDanhMuc.LayDanhSachSPTheoMaLoai(masp,
                    "LayDanhSachSanPhamTheoMaThuongHieu","DANHSACHSANPHAM",0);

        }else {
            sanPhamList = modelHienThiSanPhamTheoDanhMuc.LayDanhSachSPTheoMaLoai(masp,
                    "LayDanhSachSanPhamTheoMaLoaiDanhMuc","DANHSACHSANPHAM",0);
        }

        if (sanPhamList.size()>0){
            viewHienThiSPTheodanhMuc.HienThiDanhSachSanPham(sanPhamList);
        }else {
            viewHienThiSPTheodanhMuc.LoiHienThi();
        }
    }
    public List<SanPham> LayDanhSachSP_theoMaLoai_LoadMore(int masp, boolean kiemtra, int limit, ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
        List<SanPham> sanPhamList = new ArrayList<>();
        if (kiemtra) {
            sanPhamList = modelHienThiSanPhamTheoDanhMuc.LayDanhSachSPTheoMaLoai(masp,
                    "LayDanhSachSanPhamTheoMaThuongHieu", "DANHSACHSANPHAM", limit);

        } else {
            sanPhamList = modelHienThiSanPhamTheoDanhMuc.LayDanhSachSPTheoMaLoai(masp,
                    "LayDanhSachSanPhamTheoMaLoaiDanhMuc", "DANHSACHSANPHAM", limit);
        }
        if (sanPhamList.size() != 0 ){
            progressBar.setVisibility(View.GONE);
        }
        return sanPhamList;
    }
}
