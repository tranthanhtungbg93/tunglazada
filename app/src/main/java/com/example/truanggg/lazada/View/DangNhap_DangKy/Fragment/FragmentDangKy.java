package com.example.truanggg.lazada.View.DangNhap_DangKy.Fragment;

import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.truanggg.lazada.Model.ObjectClass.NhanVien;
import com.example.truanggg.lazada.Presenter.DangKy.PresenterDangKy;
import com.example.truanggg.lazada.R;
import com.example.truanggg.lazada.View.DangNhap_DangKy.ViewDangKy;

/**
 * Created by Truang on 12/8/2017.
 */

public class FragmentDangKy extends Fragment implements ViewDangKy,View.OnClickListener,View.OnFocusChangeListener{

    PresenterDangKy presenterDangKy;
    EditText edHoTen,edMatKhau,edNhapLaiMatKhau,edDiaChiEmail;
    SwitchCompat scEmailDocQuyen;
    Button btnDangKy;
    String EmaildocQuyen = "";
    TextInputLayout input_edhoten,input_edmatkhau,input_edNhapLaiMatKhau,input_edDiaChiEmail;
    Boolean kiemtrathongtin =false;

    public FragmentDangKy() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_dang_ky, container, false);
        edHoTen = v.findViewById(R.id.edHoTen);
        edMatKhau= v.findViewById(R.id.edMatKhau);
        edNhapLaiMatKhau = v.findViewById(R.id.edNhapLaiMatKhau);
        scEmailDocQuyen = v.findViewById(R.id.scEmailDocQuyen);
        btnDangKy = v.findViewById(R.id.btnDangKyUser);
        edDiaChiEmail =v.findViewById(R.id.edEmail);
        input_edhoten = v.findViewById(R.id.input_HoTen);
        input_edmatkhau = v.findViewById(R.id.input_MatKhau);
        input_edNhapLaiMatKhau = v.findViewById(R.id.input_NhapLaiMatKhau);
        input_edDiaChiEmail = v.findViewById(R.id.input_Email);

        presenterDangKy = new PresenterDangKy(this);

        btnDangKy.setOnClickListener(this);

        edHoTen.setOnFocusChangeListener(this);
        edMatKhau.setOnFocusChangeListener(this);
        edNhapLaiMatKhau.setOnFocusChangeListener(this);
        edDiaChiEmail.setOnFocusChangeListener(this);

        return v;
    }

    @Override
    public void DangKyThanhCong() {
        Toast.makeText(getActivity(), "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DangKyThatBai() {
        Toast.makeText(getActivity(), "Đăng Ký Thất Bại", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnDangKyUser:
                btnDangKy();
                break;
        }
    }
    private void btnDangKy(){
        String hoten = edHoTen.getText().toString();
        String matkhau = edMatKhau.getText().toString();
        String nhaplaiMk = edNhapLaiMatKhau.getText().toString();
        String email = edDiaChiEmail.getText().toString();

        scEmailDocQuyen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                EmaildocQuyen = isChecked +" ";
            }
        });

        if (kiemtrathongtin){
            NhanVien nhanVien = new NhanVien();
            nhanVien.setTenNV(hoten);
            nhanVien.setTenDN(email);
            nhanVien.setMatKhau(matkhau);
            nhanVien.setEmailDocQuyen(EmaildocQuyen);
            nhanVien.setMaLoaiNV(2);

            presenterDangKy.ThucHienDangKy(nhanVien);
        }else {
            Log.d("kiemtradangky","dangkythatbai");
        }

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();
        switch (id){
            case R.id.edHoTen:
                if(!hasFocus){
                    String chuoi = ((EditText)v).getText().toString();
                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        input_edhoten.setErrorEnabled(true);
                        input_edhoten.setError("Cần Nhập Họ Tên");
                        kiemtrathongtin = false;
                    }else {
                        input_edhoten.setErrorEnabled(false);
                        input_edhoten.setError("");
                        kiemtrathongtin = true;
                    }
                }
                break;
            case R.id.edEmail:
                if(!hasFocus){
                    String chuoi = ((EditText)v).getText().toString();

                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        input_edDiaChiEmail.setErrorEnabled(true);
                        input_edDiaChiEmail.setError("Cần Nhập Email");
                        kiemtrathongtin = false;
                    }else {
                        Boolean kiemtraEmail = Patterns.EMAIL_ADDRESS.matcher(chuoi).matches();
                        if (!kiemtraEmail){
                            input_edDiaChiEmail.setErrorEnabled(false);
                            input_edDiaChiEmail.setError("Địa Chỉ Email không hợp lệ");
                            kiemtrathongtin = false;
                        }else {
                            input_edDiaChiEmail.setErrorEnabled(false);
                            input_edDiaChiEmail.setError("");
                            kiemtrathongtin=true;
                        }

                    }
                }
                break;
            case R.id.edMatKhau:
                break;
            case R.id.edNhapLaiMatKhau:
                String chuoi =((EditText)v).getText().toString();
                String matkhau = edMatKhau.getText().toString();
                if (!chuoi.equals(matkhau)){
                    input_edmatkhau.setErrorEnabled(true); //equalsIngnoreCase so sánh k phân biệt chữ hoa ,chữ thường
                    input_edmatkhau.setError("Mật khẩu không trùng khớp");
                    kiemtrathongtin = false;
                }else {
                    input_edmatkhau.setErrorEnabled(false);
                    input_edmatkhau.setError("");
                    kiemtrathongtin = true;
                }
                break;
        }
    }
}
