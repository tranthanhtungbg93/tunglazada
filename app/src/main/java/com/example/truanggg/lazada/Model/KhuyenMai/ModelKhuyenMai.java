package com.example.truanggg.lazada.Model.KhuyenMai;

import android.util.Log;

import com.example.truanggg.lazada.ConnectInternet.DownLoadJSON;
import com.example.truanggg.lazada.Model.ObjectClass.ChiTietKhuyenMai;
import com.example.truanggg.lazada.Model.ObjectClass.KhuyenMai;
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
 */

public class ModelKhuyenMai {
    public List<KhuyenMai> LayDanhSachSPKM(String tenham, String tenmang){
        List<KhuyenMai> khuyenMaiList = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham",tenham);


        attrs.add(hsHam);

        DownLoadJSON downloadJSON = new DownLoadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            Log.d("kiemtrads",dataJSON);
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachKM = jsonObject.getJSONArray(tenmang);
            int dem = jsonArrayDanhSachKM.length();

            for (int i = 0; i<dem; i++){
                KhuyenMai khuyenMai = new KhuyenMai();
                JSONObject object = jsonArrayDanhSachKM.getJSONObject(i);

                khuyenMai.setTENLOAISP(object.getString("TENLOAISP"));
                khuyenMai.setMaKM(object.getInt("MAKM"));
                khuyenMai.setTENKM(object.getString("TENKM"));
                khuyenMai.setHINHKHUYENMAI(TrangChuActivity.SERVER + object.getString("HINHKHUYENMAI"));

                List<SanPham> sanPhamList = new ArrayList<>();
                JSONArray arrayDSSP = object.getJSONArray("DANHSACHSANPHAM");
                int demsanpham = arrayDSSP.length();
                for (int j=0 ;j< demsanpham ;j++ ){
                    JSONObject objectSP = arrayDSSP.getJSONObject(j);
                    SanPham sanPham = new SanPham();
                    sanPham.setTENSP(objectSP.getString("TENSP"));
                    sanPham.setMASP(objectSP.getInt("MASP"));
                    sanPham.setGIA(objectSP.getInt("GIA"));
                    sanPham.setAnhLon(TrangChuActivity.SERVER + objectSP.getString("ANHLON"));
                    sanPham.setAnhNho(objectSP.getString("ANHNHO"));

                    ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                    chiTietKhuyenMai.setPHANTRAMKM(objectSP.getInt("PHANTRAMKM"));

                    sanPham.setChiTietKhuyenMai(chiTietKhuyenMai);
                    sanPhamList.add(sanPham);
                }
                khuyenMai.setSanPhamListKM(sanPhamList);
                khuyenMaiList.add(khuyenMai);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return khuyenMaiList;
    }
}
