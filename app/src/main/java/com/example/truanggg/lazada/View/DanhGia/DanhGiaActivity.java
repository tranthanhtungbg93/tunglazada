package com.example.truanggg.lazada.View.DanhGia;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.truanggg.lazada.Model.ObjectClass.DanhGia;
import com.example.truanggg.lazada.Presenter.DanhGia.PresenterLogicDanhGia;
import com.example.truanggg.lazada.R;

import java.util.List;

/**
 * Created by Truang on 12/28/2017.
 */

public class DanhGiaActivity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener, ViewDanhGia, View.OnClickListener {

    TextInputLayout input_edTieuDe, input_edNoidung;
    EditText edTieude, edNoidung;
    RatingBar rbDanhGia;
    Button btnDongYdanhgia;
    int masp = 0;
    int sosao = 0;
    PresenterLogicDanhGia presenterLogicDanhGia;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhgia);
        input_edNoidung = findViewById(R.id.input_edNoiDung);
        input_edTieuDe = findViewById(R.id.input_edTieuDe);
        edTieude = findViewById(R.id.edTieuDe);
        edNoidung = findViewById(R.id.edNoiDung);
        rbDanhGia = findViewById(R.id.rbDanhGia);
        btnDongYdanhgia = findViewById(R.id.btnDongy_danhgia);
        btnDongYdanhgia.setOnClickListener(this);

        masp = getIntent().getIntExtra("masp", 0);

        presenterLogicDanhGia = new PresenterLogicDanhGia(this);

    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        sosao = (int) rating;
    }

    @Override
    public void DanhGiaThanhCong() {
        Toast.makeText(this, "Danh Gia Thanh Cong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DanhGiathatBai() {
        Toast.makeText(this, "Ban Khong The danh Gia San Pham Nay", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void HienThiDanhSachDanhGia(List<DanhGia> danhGias) {

    }

    @Override
    public void onClick(View v) {
        // Lấy được id của thiết bị
        int id = v.getId();
        switch (id){
           case R.id.btnDongy_danhgia:
               TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
               if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                   return;
               }
               String maDg = telephonyManager.getDeviceId();
               String tenThietbi = Build.MODEL; // lấy được tên của thiết bị
               String tieude = edTieude.getText().toString();
               String noidung = edNoidung.getText().toString();

               if(tieude.trim().length() > 0){
                   input_edTieuDe.setErrorEnabled(false);
                   input_edTieuDe.setError("");
               }else {
                   input_edTieuDe.setErrorEnabled(true);
                   input_edTieuDe.setError("Bạn Chưa Nhập Tiêu Đề");
               }

               if (noidung.trim().length()> 0){
                   input_edNoidung.setErrorEnabled(false);
                   input_edNoidung.setError("");
               }else {
                   input_edNoidung.setErrorEnabled(true);
                   input_edNoidung.setError("Bạn Chưa Nhập Nội dung");
               }

               if (!input_edTieuDe.isErrorEnabled() && !input_edNoidung.isErrorEnabled()){
                   DanhGia danhGia = new DanhGia();
                   danhGia.setMADG(maDg);
                   danhGia.setMASP(masp);
                   danhGia.setSOSAO(sosao);
                   danhGia.setTENTHIETBI(tenThietbi);
                   danhGia.setTIEUDE(tieude);
                   danhGia.setNOIDUNG(noidung );
                   presenterLogicDanhGia.ThemDanhGia(danhGia);
                   finish();
               }
               break;
        }
    }
}
