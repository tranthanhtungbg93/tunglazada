package com.example.truanggg.lazada.Model.DanhGia;

import android.util.Log;

import com.example.truanggg.lazada.ConnectInternet.DownLoadJSON;
import com.example.truanggg.lazada.Model.ObjectClass.DanhGia;
import com.example.truanggg.lazada.View.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Truang on 12/28/2017.
 */

public class ModelDanhGia {
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

    public boolean ThemDanhGia(DanhGia danhGia){
        String duongdan = TrangChuActivity.SERVER_NAME;
        List<HashMap<String,String>> attrs = new ArrayList<>();
        Boolean kiemtra = false;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","ThemDanhGia");

        HashMap<String,String> hsMaDG = new HashMap<>();
        hsMaDG.put("madg",danhGia.getMADG());

        HashMap<String,String> hsMaSP = new HashMap<>();
        hsMaSP.put("masp", String.valueOf(danhGia.getMASP()));

        HashMap<String,String> hsSoSao = new HashMap<>();
        hsSoSao.put("sosao", String.valueOf(danhGia.getSOSAO()));

        HashMap<String,String> hsTieuDe = new HashMap<>();
        hsTieuDe.put("tieude", danhGia.getTIEUDE());

        HashMap<String,String> hsNoidung = new HashMap<>();
        hsNoidung.put("noidung",danhGia.getNOIDUNG());

        HashMap<String,String> hsTenThietBi = new HashMap<>();
        hsTenThietBi.put("tenthietbi",danhGia.getTENTHIETBI());

        attrs.add(hsHam);
        attrs.add(hsMaDG);
        attrs.add(hsMaSP);
        attrs.add(hsSoSao);
        attrs.add(hsTieuDe);
        attrs.add(hsNoidung);
        attrs.add(hsTenThietBi);

        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);
        downLoadJSON.execute();

        try {
            String dulieuJson = downLoadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieuJson);
            String ketqua = jsonObject.getString("ketqua");
            Log.d("kiem tra danhgia ",dulieuJson);
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
