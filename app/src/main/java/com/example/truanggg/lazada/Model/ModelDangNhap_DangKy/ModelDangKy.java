package com.example.truanggg.lazada.Model.ModelDangNhap_DangKy;

import android.util.Log;

import com.example.truanggg.lazada.ConnectInternet.DownLoadJSON;
import com.example.truanggg.lazada.Model.ObjectClass.NhanVien;
import com.example.truanggg.lazada.View.TrangChuActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Truang on 12/18/2017.
 **/

public class ModelDangKy {
    public Boolean DangKyThanhVien(NhanVien nhanvien){
        String duongdan = TrangChuActivity.SERVER_NAME;
        List<HashMap<String,String>> attrs = new ArrayList<>();
        Boolean kiemtra = false;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","DangKyThanhVien");

        HashMap<String,String> hsTenNv = new HashMap<>();
        hsTenNv.put("tennv",nhanvien.getTenNV());

        HashMap<String,String> hsTenDN = new HashMap<>();
        hsTenDN.put("tendangnhap",nhanvien.getTenDN());

        HashMap<String,String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matkhau",nhanvien.getMatKhau());

        HashMap<String,String> hsMaloaiNV = new HashMap<>();
        hsMaloaiNV.put("maloainv", String.valueOf(nhanvien.getMaLoaiNV()));

        HashMap<String,String> hsEmailDocQuyen = new HashMap<>();
        hsEmailDocQuyen.put("emaildocquyen",nhanvien.getEmailDocQuyen());

        attrs.add(hsHam);
        attrs.add(hsTenNv);
        attrs.add(hsTenDN);
        attrs.add(hsMatKhau);
        attrs.add(hsMaloaiNV);
        attrs.add(hsEmailDocQuyen);

        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);
        downLoadJSON.execute();

        try {
            String dulieuJson = downLoadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieuJson);
            String ketqua = jsonObject.getString("ketqua");
            Log.d("kiem tra user ",dulieuJson);
            if(ketqua.equals("true")){
                kiemtra = true;
            }else {
                kiemtra = false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return kiemtra;
    }
}
