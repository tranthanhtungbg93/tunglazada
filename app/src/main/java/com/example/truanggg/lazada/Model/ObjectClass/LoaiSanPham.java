package com.example.truanggg.lazada.Model.ObjectClass;

import java.util.List;

/**
 * Created by Truang on 12/1/2017.
 */

public class LoaiSanPham {
    int maloaiSP,maloaiCha;
    String tenloaiSP;
    List<LoaiSanPham> listCon;

    public int getMaloaiSP() {
        return maloaiSP;
    }

    public void setMaloaiSP(int maloaiSP) {
        this.maloaiSP = maloaiSP;
    }

    public int getMaloaiCha() {
        return maloaiCha;
    }

    public void setMaloaiCha(int maloaiCha) {
        this.maloaiCha = maloaiCha;
    }

    public String getTenloaiSP() {
        return tenloaiSP;
    }

    public void setTenloaiSP(String tenloaiSP) {
        this.tenloaiSP = tenloaiSP;
    }

    public List<LoaiSanPham> getListCon() {
        return listCon;
    }

    public void setListCon(List<LoaiSanPham> listCon) {
        this.listCon = listCon;
    }
}
