package com.example.truanggg.lazada.Model.ObjectClass;

import java.util.List;

/**
 * Created by Truang on 1/1/2018.
 */

public class KhuyenMai {
    int MaKM,MALOAISP;
    String TENKM,NGAYBATDAU,NGAYKETTHUC,HINHKHUYENMAI,TENLOAISP;

    public String getTENLOAISP() {
        return TENLOAISP;
    }

    public void setTENLOAISP(String TENLOAISP) {
        this.TENLOAISP = TENLOAISP;
    }

    List<SanPham> sanPhamListKM;
    public int getMaKM() {
        return MaKM;
    }

    public void setMaKM(int maKM) {
        MaKM = maKM;
    }

    public int getMALOAISP() {
        return MALOAISP;
    }

    public void setMALOAISP(int MALOAISP) {
        this.MALOAISP = MALOAISP;
    }

    public String getTENKM() {
        return TENKM;
    }

    public void setTENKM(String TENKM) {
        this.TENKM = TENKM;
    }

    public String getNGAYBATDAU() {
        return NGAYBATDAU;
    }

    public void setNGAYBATDAU(String NGAYBATDAU) {
        this.NGAYBATDAU = NGAYBATDAU;
    }

    public String getNGAYKETTHUC() {
        return NGAYKETTHUC;
    }

    public void setNGAYKETTHUC(String NGAYKETTHUC) {
        this.NGAYKETTHUC = NGAYKETTHUC;
    }

    public String getHINHKHUYENMAI() {
        return HINHKHUYENMAI;
    }

    public void setHINHKHUYENMAI(String HINHKHUYENMAI) {
        this.HINHKHUYENMAI = HINHKHUYENMAI;
    }

    public List<SanPham> getSanPhamListKM() {
        return sanPhamListKM;
    }

    public void setSanPhamListKM(List<SanPham> sanPhamListKM) {
        this.sanPhamListKM = sanPhamListKM;
    }

}
