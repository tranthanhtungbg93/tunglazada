package com.example.truanggg.lazada.Model.TrangChu_DienTu;

import android.util.Log;

import com.example.truanggg.lazada.ConnectInternet.DownLoadJSON;
import com.example.truanggg.lazada.Model.ObjectClass.SanPham;
import com.example.truanggg.lazada.Model.ObjectClass.ThuongHieu;
import com.example.truanggg.lazada.View.TrangChuActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Truang on 12/21/2017.
 **/

public class ModelDienTu {

//////
    public List<SanPham>  LayDanhSachPhamTOP(String tenham,String tenmang){
        List<SanPham> sanPhamList = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham",tenham);

        attrs.add(hsHam);

        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan, attrs);

        // Làm bằng phương thức POST phải khởi tạo 1 cái hashmap

        // end phương thức POST
        downLoadJSON.execute();
        try {
            dataJSON = downLoadJSON.get();
            Log.d("kiemtratop",dataJSON.toString());
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachSanPham =jsonObject.getJSONArray(tenmang);
            int dem = jsonArrayDanhSachSanPham.length();
            for (int i = 0; i < dem ;i++){
                SanPham sanPham = new SanPham();
                JSONObject object = jsonArrayDanhSachSanPham.getJSONObject(i);

                sanPham.setMASP(object.getInt("MASP"));
                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIATIEN"));
                sanPham.setAnhLon(object.getString("HINHSANPHAM"));

                sanPhamList.add(sanPham);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sanPhamList;
    }

    public List<ThuongHieu> LayDanhSachThuongHieu(String tenham,String tenmang){
        List<ThuongHieu> thuongHieuList = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham",tenham);


        attrs.add(hsHam);

        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan, attrs);

        // Làm bằng phương thức POST phải khởi tạo 1 cái hashmap

        // end phương thức POST
        downLoadJSON.execute();
        try {
            dataJSON = downLoadJSON.get();
            Log.d("kiemtrads",dataJSON.toString());
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachThuongHieu =jsonObject.getJSONArray(tenmang);
            int dem = jsonArrayDanhSachThuongHieu.length();
            for (int i = 0; i < dem ;i++){
                ThuongHieu thuongHieu = new ThuongHieu();
                JSONObject object = jsonArrayDanhSachThuongHieu.getJSONObject(i);

                thuongHieu.setMATHUONGHIEU(object.getInt("MASP"));
                thuongHieu.setTENTHUONGHIEU(object.getString("TENSP"));
                thuongHieu.setHINHTHUONGHIEU(object.getString("HINHSANPHAM"));

                thuongHieuList.add(thuongHieu);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return thuongHieuList;
    }
}
