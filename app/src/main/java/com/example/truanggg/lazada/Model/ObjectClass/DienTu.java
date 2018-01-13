package com.example.truanggg.lazada.Model.ObjectClass;

import android.widget.ImageView;

import java.util.List;

/**
 * Created by Truang on 12/21/2017.
 */

public class DienTu {
    String HinhSanPham;
    List<ThuongHieu> thuongHieus;
    List<SanPham> sanPhams;
    String tenNoiBat,tenTopNoiBat;
    Boolean thuonghieu;

    public Boolean getThuonghieu() {
        return thuonghieu;
    }

    public void setThuonghieu(Boolean thuonghieu) {
        this.thuonghieu = thuonghieu;
    }

    public String getTenNoiBat() {
        return tenNoiBat;
    }

    public void setTenNoiBat(String tenNoiBat) {
        this.tenNoiBat = tenNoiBat;
    }

    public String getTenTopNoiBat() {
        return tenTopNoiBat;
    }

    public void setTenTopNoiBat(String tenTopNoiBat) {
        this.tenTopNoiBat = tenTopNoiBat;
    }

    public String getHinhSanPham() {
        return HinhSanPham;
    }

    public void setHinhSanPham(String hinhSanPham) {
        HinhSanPham = hinhSanPham;
    }

    public List<ThuongHieu> getThuongHieus() {
        return thuongHieus;
    }

    public void setThuongHieus(List<ThuongHieu> thuongHieus) {
        this.thuongHieus = thuongHieus;
    }

    public List<SanPham> getSanPhams() {
        return sanPhams;
    }

    public void setSanPhams(List<SanPham> sanPhams) {
        this.sanPhams = sanPhams;
    }
}
