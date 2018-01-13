package com.example.truanggg.lazada.View;

import android.view.Menu;

import com.example.truanggg.lazada.Model.ObjectClass.DienTu;
import com.example.truanggg.lazada.Model.ObjectClass.SanPham;
import com.example.truanggg.lazada.Model.ObjectClass.ThuongHieu;

import java.util.List;

/**
 * Created by Truang on 12/21/2017.
 */

public interface ViewDienTu {
   void HienThiDanhSach(List<DienTu> dienTuList);
   void HienThiLogoThuongHieu(List<ThuongHieu> thuongHieuList);
   void LoiLayDuLieu();
   void HienThiHangMoive(List<SanPham> sanPhamList);
}
