package com.example.truanggg.lazada.Model.TrangChu.XuLyMenu;

import android.os.Bundle;
import android.util.Log;

import com.example.truanggg.lazada.ConnectInternet.DownLoadJSON;
import com.example.truanggg.lazada.Model.ObjectClass.LoaiSanPham;
import com.example.truanggg.lazada.View.TrangChuActivity;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Truang on 12/1/2017.
 */

public class XuLiJSONMenu {

    public List<LoaiSanPham> ParserJSONMenu(String dulieuJSOn) {
        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();
        try {
            Log.d("kiemtra",dulieuJSOn);
            JSONObject jsonObject = new JSONObject(dulieuJSOn);
            JSONArray loaisanpham = jsonObject.getJSONArray("LOAISANPHAM");
            int count = loaisanpham.length();
            for (int i = 0; i < count; i++) {
                JSONObject value = loaisanpham.getJSONObject(i);

                LoaiSanPham datasanPham = new LoaiSanPham();
                datasanPham.setMaloaiSP(Integer.parseInt(value.getString("MALOAISP")));
                datasanPham.setMaloaiCha(Integer.parseInt(value.getString("MALOAI_CHA")));
                datasanPham.setTenloaiSP(value.getString("TENLOAISP"));

                loaiSanPhamList.add(datasanPham);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return loaiSanPhamList;
    }
    public List<LoaiSanPham> LayLoaiSPTheoMaLoai(int maloaiSP) {
        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham","LayDanhSachMenu");

        HashMap<String, String> hsMaLoaiCha = new HashMap<>();
        hsMaLoaiCha.put("maloaicha", String.valueOf(maloaiSP));


        attrs.add(hsHam);
        attrs.add(hsMaLoaiCha);
        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan, attrs);

        // Làm bằng phương thức POST phải khởi tạo 1 cái hashmap

        // end phương thức POST
        downLoadJSON.execute();
        try {
            dataJSON = downLoadJSON.get();
            XuLiJSONMenu xuLiJSONMenu = new XuLiJSONMenu();
            loaiSanPhamList = xuLiJSONMenu.ParserJSONMenu(dataJSON);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return loaiSanPhamList;
    }
}

