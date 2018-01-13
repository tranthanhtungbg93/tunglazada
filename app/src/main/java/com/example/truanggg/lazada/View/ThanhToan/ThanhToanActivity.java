package com.example.truanggg.lazada.View.ThanhToan;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truanggg.lazada.Model.ObjectClass.ChiTietHoaDon;
import com.example.truanggg.lazada.Model.ObjectClass.HoaDon;
import com.example.truanggg.lazada.Model.ObjectClass.SanPham;
import com.example.truanggg.lazada.Presenter.ThanhToan.PresenterLogicThanhToan;
import com.example.truanggg.lazada.R;
import com.example.truanggg.lazada.View.TrangChuActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Truang on 12/31/2017.
 **/

public class ThanhToanActivity extends AppCompatActivity implements View.OnClickListener,ViewThanhToan
{
    TextView txtNhanTienKhiGiaoHang, txtChuyenKhoan;
    Toolbar toolbar;
    EditText edTenNguoiNhan,edDiaChi, edSoDT;
    ImageButton imNhanTienKhiGiaoHang,imChuyenKhoan;
    Button btnThanhToan;
    CheckBox cbThoathuan;
    PresenterLogicThanhToan presenterLogicThanhToan;
    List<ChiTietHoaDon> chiTietHoaDons = new ArrayList<>();
    int chonhinhthuc = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thanhtoan);
        init();
        presenterLogicThanhToan = new PresenterLogicThanhToan(this,this);
        presenterLogicThanhToan.LayDanhSachSPtrongGioHang();

        setSupportActionBar(toolbar);
    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        edTenNguoiNhan = findViewById(R.id.edTenNguoiNhan);
        edDiaChi = findViewById(R.id.edDiaChi);
        edSoDT = findViewById(R.id.edSoDT);
        imNhanTienKhiGiaoHang = findViewById(R.id.imNhanTienKhiGiaoHang);
        imChuyenKhoan = findViewById(R.id.imChuyenKhoan);
        cbThoathuan = findViewById(R.id.cbThoaThuan);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        txtNhanTienKhiGiaoHang = findViewById(R.id.txtNhanTienKhiGiaoHang);
        txtChuyenKhoan = findViewById(R.id.txtChuyenKhoan);
        btnThanhToan.setOnClickListener(this);
        imNhanTienKhiGiaoHang.setOnClickListener(this);
        imChuyenKhoan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnThanhToan:
                String tennguoinhan = edTenNguoiNhan.getText().toString();
                String diachi = edDiaChi.getText().toString();
                String sodt= edSoDT.getText().toString();

                if (tennguoinhan.trim().length()> 0 && diachi.trim().length()>0 && sodt.trim().length() > 0){
                    if (cbThoathuan.isChecked()){
                        HoaDon hoaDon = new HoaDon();
                        hoaDon.setTenNguoiNhan(tennguoinhan);
                        hoaDon.setDiaChi(diachi);
                        hoaDon.setChuyenKhoan(chonhinhthuc);
                        hoaDon.setSoDT(sodt);
                        hoaDon.setChiTietHoaDons(chiTietHoaDons);
                        presenterLogicThanhToan.ThemThanhToan(hoaDon);
                    }else {
                        Toast.makeText(this, "Bạn chưa tích vào ô thỏa thuận", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "Bạn chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.imNhanTienKhiGiaoHang:
                ChonHinhThucGiaoHang(txtNhanTienKhiGiaoHang,txtChuyenKhoan);
                chonhinhthuc = 0;
                break;
            case R.id.imChuyenKhoan:
                ChonHinhThucGiaoHang(txtChuyenKhoan,txtNhanTienKhiGiaoHang);
                chonhinhthuc = 1;
                break;
        }
    }
    private void ChonHinhThucGiaoHang(TextView txtDuocChon, TextView txtHuyChon){
        txtDuocChon.setTextColor(getIdColor(R.color.colorFB));
        txtHuyChon.setTextColor(getIdColor(R.color.tranparentBlack));
    }

    private int getIdColor(int Color){
        int color = 0;
        if (Build.VERSION.SDK_INT < 21 ){
            color = ContextCompat.getColor(this, Color);
        }else {
            color = getResources().getColor(Color);
        }
        return color;
    }

    @Override
    public void DatHangThanhCong() {
        Toast.makeText(this, "Đặt Hàng Thành Công", Toast.LENGTH_SHORT).show();
        Intent iTrangChu = new Intent(ThanhToanActivity.this, TrangChuActivity.class);
        startActivity(iTrangChu);
    }

    @Override
    public void DatHangThatBai() {
        Toast.makeText(this, "Đặt Hàng Thất Bại", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void LayDanhSachSpTrongGioHang(List<SanPham> sanPhamList) {

        for (int i = 0;i< sanPhamList.size();i++){
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
            chiTietHoaDon.setMaSP(sanPhamList.get(i).getMASP());
            chiTietHoaDon.setSoLuong(sanPhamList.get(i).getSoLuong());

            chiTietHoaDons.add(chiTietHoaDon);
        }
    }
}
