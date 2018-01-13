package com.example.truanggg.lazada.Presenter.TrangChu.XuLyMenu;

import com.example.truanggg.lazada.ConnectInternet.DownLoadJSON;
import com.example.truanggg.lazada.Model.ModelDangNhap_DangKy.ModelDangNhap;
import com.example.truanggg.lazada.Model.ObjectClass.LoaiSanPham;
import com.example.truanggg.lazada.Model.TrangChu.XuLyMenu.XuLiJSONMenu;
import com.example.truanggg.lazada.View.TrangChuActivity;
import com.example.truanggg.lazada.View.ViewXuLyMenu;
import com.facebook.AccessToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Truang on 12/1/2017.
 **/

public class PresenterLogicXuLyMenu implements IPresenterXuLyMenu{
    ViewXuLyMenu viewXuLyMenu;
    String tennguoidung = "";

    public PresenterLogicXuLyMenu(ViewXuLyMenu viewXuLyMenu){
            this.viewXuLyMenu =viewXuLyMenu;
    }
    @Override
    public void layDanhSachMenu() {
        List<LoaiSanPham> loaiSanPhamList;
        String dataJSON = "";
        List<HashMap<String,String>> attrs = new ArrayList<>();

        //phương thức GET
//        String duongdan = "http://192.168.50.103:8080/weblazada/loaisanpham%20-%20Copy.php?maloaicha=0";

//        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan);
        // end phương thức GET
//        "http://192.168.50.103:8080/weblazada/loaisanpham.php?ham=LayDanhSachMenu";
//        String duongdan = "http://192.168.1.137:8080/weblazada/loaisanpham.php?ham=LayDanhSachMenu&maloaicha=0";
        String duongdan = TrangChuActivity.SERVER_NAME;
        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","LayDanhSachMenu");

        HashMap<String,String> hsMaLoaiCha = new HashMap<>();
        hsMaLoaiCha.put("maloaicha","0");

        attrs.add(hsMaLoaiCha);
        attrs.add(hsHam);
        DownLoadJSON downLoadJSON = new DownLoadJSON(duongdan,attrs);

        // Làm bằng phương thức POST phải khởi tạo 1 cái hashmap

        // end phương thức POST
        downLoadJSON.execute();

        try {
            dataJSON = downLoadJSON.get();
            XuLiJSONMenu xuLiJSONMenu = new XuLiJSONMenu();
            loaiSanPhamList= xuLiJSONMenu.ParserJSONMenu(dataJSON);
            viewXuLyMenu.HienThiDanhSachMenu(loaiSanPhamList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AccessToken LayTenNguoiDungFb() {
        ModelDangNhap modelDangNhap = new ModelDangNhap();
        AccessToken accessToken = modelDangNhap.LayTokenFBHienTai();
        // khi tách LaytenNguoiDungFB sang package Model sẽ dính vào graphRequest.executeAsync();

        return accessToken;
    }
}
