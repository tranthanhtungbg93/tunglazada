package com.example.truanggg.lazada.Model.ChiTietSanPham;

import android.util.Log;

import com.example.truanggg.lazada.ConnectInternet.DownLoadJSON;
import com.example.truanggg.lazada.Model.ObjectClass.ChiTietKhuyenMai;
import com.example.truanggg.lazada.Model.ObjectClass.ChiTietSanPham;
import com.example.truanggg.lazada.Model.ObjectClass.DanhGia;
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
 * Created by Truang on 12/27/2017.
 **/

public class ModelChiTietSanPham {

    public List<DanhGia> LayDanhSachdanhGiaCuaSanPham(int masp, int limit){
        List<DanhGia> danhGias = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","LayDanhSachDanhGiaTheoMaSP");

        HashMap<String,String> hsMaLoai = new HashMap<>();
        hsMaLoai.put("masp",String.valueOf(masp));

        HashMap<String,String> hsLimit = new HashMap<>();
        hsLimit.put("limit",String.valueOf(limit));

        attrs.add(hsHam);
        attrs.add(hsMaLoai);
        attrs.add(hsLimit);

        DownLoadJSON downloadJSON = new DownLoadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            Log.d("kiemtrads",dataJSON);
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachSanPham = jsonObject.getJSONArray("DANHSACHDANHGIA");
            int dem = jsonArrayDanhSachSanPham.length();

            for (int i = 0; i<dem; i++){
                DanhGia danhGia = new DanhGia();
                JSONObject object = jsonArrayDanhSachSanPham.getJSONObject(i);

                danhGia.setNOIDUNG(object.getString("NOIDUNG"));
                danhGia.setNGAYDANHGIA(object.getString("NGAYDANHGIA"));
                danhGia.setTENTHIETBI(object.getString("TENTHIETBI"));
                danhGia.setSOSAO(object.getInt("SOSAO"));
                danhGia.setMADG(object.getString("MADG"));
                danhGia.setMASP(object.getInt("MASP"));
                danhGia.setTIEUDE(object.getString("TIEUDE"));

                danhGias.add(danhGia);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return danhGias;
    }

    public SanPham LayChiTietSanPham(String tenham ,String tenmang,int masp){
        SanPham sanPham =new SanPham();
        List<ChiTietSanPham> chiTietSanPhamList = new ArrayList<>();

        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham",tenham);

        HashMap<String, String> hsMasp = new HashMap<>();
        hsMasp.put("masp", String.valueOf(masp));

        attrs.add(hsHam);
        attrs.add(hsMasp);

        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan, attrs);

        // Làm bằng phương thức POST phải khởi tạo 1 cái hashmap

        // end phương thức POST
        downLoadJSON.execute();
        try {
            dataJSON = downLoadJSON.get();
            Log.d("chi tiet san pham",dataJSON.toString());
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachSanPham =jsonObject.getJSONArray(tenmang);
            int dem = jsonArrayDanhSachSanPham.length();
            for (int i = 0; i < dem ;i++){

                JSONObject object = jsonArrayDanhSachSanPham.getJSONObject(i);
                ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
                chiTietKhuyenMai.setPHANTRAMKM(object.getInt("PHANTRAMKM"));

                sanPham.setChiTietKhuyenMai(chiTietKhuyenMai);

                sanPham.setMASP(object.getInt("MASP"));
                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIATIEN"));
                sanPham.setAnhNho(object.getString("ANHNHO"));
                sanPham.setSoLuong(object.getInt("SOLUONG"));
                sanPham.setThongTin(object.getString("THONGTIN"));
                sanPham.setMALOAISP(object.getInt("MALOAISP"));
                sanPham.setMATHUONGHIEU(object.getInt("MATHUONGHIEU"));
                sanPham.setMANV(object.getInt("MANV"));
                sanPham.setTENNV(object.getString("TENNV"));
                sanPham.setLUOTMUA(object.getInt("LUOTMUA"));

                JSONArray jsonArrayThongSoKyThuat = object.getJSONArray("THONGSOKYTHUAT");
                for (int j = 0; j < jsonArrayThongSoKyThuat.length();j++){
                    JSONObject object1 = jsonArrayThongSoKyThuat.getJSONObject(j);

                    for (int k =0 ; k <object1.names().length();k++ ){
                        String tenchitiet = object1.names().getString(k);

                        ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                        chiTietSanPham.setTENCHITIET(tenchitiet);
                        chiTietSanPham.setGIATRI(object1.getString(tenchitiet));

                        chiTietSanPhamList.add(chiTietSanPham);
                    }
                }
                sanPham.setChiTietSanPhamList(chiTietSanPhamList);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sanPham;
    }
}
