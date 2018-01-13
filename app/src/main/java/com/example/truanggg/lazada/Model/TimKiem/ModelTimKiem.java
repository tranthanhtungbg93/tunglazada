package com.example.truanggg.lazada.Model.TimKiem;

import android.util.Log;

import com.example.truanggg.lazada.ConnectInternet.DownLoadJSON;
import com.example.truanggg.lazada.Model.ObjectClass.ChiTietKhuyenMai;
import com.example.truanggg.lazada.Model.ObjectClass.SanPham;
import com.example.truanggg.lazada.View.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Truang on 1/1/2018.
 **/

public class ModelTimKiem {
    public List<SanPham> TimKiemSanPhamTheoTen(String tenham,String tenmang,String tensp,int limit){
        List<SanPham> sanPhams = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham",tenham);

        HashMap<String,String> hsTenSP  = new HashMap<>();
        hsTenSP.put("tensp",tensp);

        HashMap<String,String> hsLimit = new HashMap<>();
        hsLimit.put("limit", String.valueOf(limit));


        attrs.add(hsHam);
        attrs.add(hsTenSP);
        attrs.add(hsLimit);

        DownLoadJSON downloadJSON = new DownLoadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachSanPham = jsonObject.getJSONArray(tenmang);
            int dem = jsonArrayDanhSachSanPham.length();

            for (int i = 0; i<dem; i++){
                SanPham sanPham = new SanPham();
                JSONObject object = jsonArrayDanhSachSanPham.getJSONObject(i);

                sanPham.setMASP(object.getInt("MASP"));
                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIATIEN"));
                sanPham.setAnhLon(TrangChuActivity.SERVER + object.getString("HINHSANPHAM"));
                sanPham.setAnhNho(object.getString("HINHSANPHAMNHO"));

                ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                chiTietKhuyenMai.setPHANTRAMKM(object.getInt("PHANTRAMKM"));

                sanPham.setChiTietKhuyenMai(chiTietKhuyenMai);
                sanPhams.add(sanPham);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sanPhams;
    }
}
