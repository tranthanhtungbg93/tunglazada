package com.example.truanggg.lazada.View.ChiTietSanPham;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truanggg.lazada.Adapter.AdapterDanhGia;
import com.example.truanggg.lazada.Adapter.AdapterViewpagerSlider;
import com.example.truanggg.lazada.Model.ObjectClass.ChiTietKhuyenMai;
import com.example.truanggg.lazada.Model.ObjectClass.ChiTietSanPham;
import com.example.truanggg.lazada.Model.ObjectClass.DanhGia;
import com.example.truanggg.lazada.Model.ObjectClass.SanPham;
import com.example.truanggg.lazada.Presenter.ChiTietSanPham.FragmentSliderChiTietSanPham;
import com.example.truanggg.lazada.Presenter.ChiTietSanPham.PresnterLogicChiTietSanPham;
import com.example.truanggg.lazada.R;
import com.example.truanggg.lazada.View.DanhGia.DanhGiaActivity;
import com.example.truanggg.lazada.View.DanhGia.DanhSachDanhGiaActivity;
import com.example.truanggg.lazada.View.Fragment.DanhSachDanhGiaFragment;
import com.example.truanggg.lazada.View.GioHang.DanhSachSPGioHang;
import com.example.truanggg.lazada.View.ThanhToan.ThanhToanActivity;
import com.example.truanggg.lazada.View.TrangChuActivity;
import com.example.truanggg.lazada.View.ViewChiTietSanPham;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamActivity extends AppCompatActivity implements ViewChiTietSanPham
        ,ViewPager.OnPageChangeListener,View.OnClickListener
{
    ViewPager viewPager;
    PresnterLogicChiTietSanPham presnterLogicChiTietSanPham;
    TextView[] txtDots;
    LinearLayout layoutDots;
    List<Fragment> fragmentList;
    TextView txtTenSanPham, txtGiaSanPham,txtTenCuaHangDongGoi,txtThongTinchiTiet,txtGiamGia
            ,txtVietDanhGia,txtXemDanhSachDanhGia,txtGioHang;
    Toolbar toolbar;
    ImageView imXemTinchitiet,imThemGioHang;
    boolean kiemtraXoChiTiet = false;
    LinearLayout lnThongSoKyThuat;
    int masp;
    RecyclerView recyclerViewChiTietDanhgia;
    SanPham sanphamGioHang;
    boolean onPause;
    Button btnMuaNgay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsanpham);
        viewPager = findViewById(R.id.viewpagerSilder);
        layoutDots = findViewById(R.id.layoutDots);

        txtTenSanPham = findViewById(R.id.txt_ten_sanpham);
        txtGiaSanPham = findViewById(R.id.txt_giaSanPham);
        txtTenCuaHangDongGoi = findViewById(R.id.tenCuaHangDongGoi);
        txtThongTinchiTiet = findViewById(R.id.ThongTinChiTiet);
        imXemTinchitiet = findViewById(R.id.imThongTinChiTiet);
        lnThongSoKyThuat= findViewById(R.id.lnThongSoKyThuat);
        txtGiamGia = findViewById(R.id.txt_GiamGiaSP);

        txtVietDanhGia = findViewById(R.id.txtVietDanhGia);
        txtVietDanhGia.setOnClickListener(this);

        txtXemDanhSachDanhGia = findViewById(R.id.txtDanhSachDanhGia);
        txtXemDanhSachDanhGia.setOnClickListener(this);

        imThemGioHang=findViewById(R.id.imThemGioHang);
        imThemGioHang.setOnClickListener(this);

        btnMuaNgay= findViewById(R.id.btnMuaNgay);
        btnMuaNgay.setOnClickListener(this);

        recyclerViewChiTietDanhgia = findViewById(R.id.recycler_NhanXetDanhGia);
        toolbar = findViewById(R.id.toolbarChiTietSP);
        setSupportActionBar(toolbar);

        masp = getIntent().getIntExtra("masp",0);
        presnterLogicChiTietSanPham = new PresnterLogicChiTietSanPham(this);
        presnterLogicChiTietSanPham.LayChiTietSanPham(masp);
        presnterLogicChiTietSanPham.LayDanhSachDanhGiaCuaSanPham(masp,0);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu,menu);
        MenuItem menuItem = menu.findItem(R.id.itGioHang);
        View GiaoDienCustomGioHang  = MenuItemCompat.getActionView(menuItem); // Lấy được lauout của custom giỏ hàng
        txtGioHang = GiaoDienCustomGioHang.findViewById(R.id.txtSoLuongSPGioHang);
        GiaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioHang = new Intent(ChiTietSanPhamActivity.this, DanhSachSPGioHang.class);
                startActivity(iGioHang);
            }
        });
        txtGioHang.setText(String.valueOf(presnterLogicChiTietSanPham.DemSanPhamTrongGioHang(this)));
        return true;
    }
    private Drawable gethinhchitiet(int iddrawable){
        Drawable drawable;
        if (Build.VERSION.SDK_INT < 21 ){
            drawable= ContextCompat.getDrawable(this, iddrawable);
        }else {
            drawable= getResources().getDrawable(iddrawable);
        }
        return drawable;
    }
    @Override
    public void HienThichiTietSanPham(final SanPham sanPham) {
        masp = sanPham.getMASP();
        sanphamGioHang = sanPham;
        sanphamGioHang.setSOLUONGTONKHO(sanPham.getSoLuong());

        txtTenSanPham.setText(sanPham.getTENSP());
        int giatien = sanPham.getGIA();
        ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
        if (chiTietKhuyenMai != null){
            int phantramKm =chiTietKhuyenMai.getPHANTRAMKM();

            if (phantramKm != 0) {
                NumberFormat numberFormat = new DecimalFormat("###,###");
                String gia = numberFormat.format(sanPham.getGIA()).toString();

                txtGiamGia.setVisibility(View.VISIBLE);
                txtGiamGia.setPaintFlags(
                        txtGiamGia.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                txtGiamGia.setText(gia + "VND");

                giatien = giatien * phantramKm / 100;
            }
        }
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(giatien);
        txtGiaSanPham.setText(gia + " VND");

        txtTenCuaHangDongGoi.setText(sanPham.getTENNV());
        txtThongTinchiTiet.setText(sanPham.getThongTin().substring(0,100));

        if (sanPham.getThongTin().length() < 100 ){
            imXemTinchitiet.setVisibility(View.GONE);
        }else {
            imXemTinchitiet.setVisibility(View.VISIBLE);
            imXemTinchitiet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    kiemtraXoChiTiet = !kiemtraXoChiTiet;
                    if(kiemtraXoChiTiet ){
                        // sau khi mở chi tiết
                        txtThongTinchiTiet.setText(sanPham.getThongTin());
                        imXemTinchitiet.setImageDrawable(gethinhchitiet(R.drawable.ic_keyboard_arrow_up_black_24dp));
                        lnThongSoKyThuat.setVisibility(View.VISIBLE);
                        HienThiThongSoKyThuat(sanPham);
                    }else {
                        // đóng chi tiết
                        txtThongTinchiTiet.setText(sanPham.getThongTin().substring(0,100));
                        imXemTinchitiet.setImageDrawable(gethinhchitiet(R.drawable.ic_keyboard_arrow_down_black_24dp));
                        lnThongSoKyThuat.setVisibility(View.GONE);
                    }
                }
            });
        }

    }
    public void HienThiThongSoKyThuat(SanPham sanPham){
        List<ChiTietSanPham> chiTietSanPhamList = sanPham.getChiTietSanPhamList();
        for (int i = 0; i < chiTietSanPhamList.size();i++){
            LinearLayout lnChiTiet = new LinearLayout(this);
            lnChiTiet.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lnChiTiet.setOrientation(LinearLayout.HORIZONTAL);

            TextView txtTenthongso = new TextView(this);
            txtTenthongso.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));
            txtTenthongso.setText(chiTietSanPhamList.get(i).getTENCHITIET());

            TextView giaTriThongSo = new TextView(this);
            giaTriThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));
            giaTriThongSo.setText(chiTietSanPhamList.get(i).getGIATRI());

            lnChiTiet.addView(txtTenthongso);
            lnChiTiet.addView(giaTriThongSo);

            lnThongSoKyThuat.addView(lnChiTiet);
        }
    }
    @Override
    public void HienthiSlider(String[] linkhinhSP) {
         fragmentList = new ArrayList<>();

        for (int i =0; i <linkhinhSP.length ; i++){
            FragmentSliderChiTietSanPham sliderChiTietSanPham = new FragmentSliderChiTietSanPham();
            Bundle bundle = new Bundle();
            bundle.putString("linkHinh",TrangChuActivity.SERVER + linkhinhSP[i]);
            sliderChiTietSanPham.setArguments(bundle);

            fragmentList.add(sliderChiTietSanPham);
        }
        AdapterViewpagerSlider adapterViewpagerSlider = new AdapterViewpagerSlider(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapterViewpagerSlider);
        adapterViewpagerSlider.notifyDataSetChanged();

        themDotsSlider(0);
        viewPager.addOnPageChangeListener(this);
    }


    private void themDotsSlider (int vitrihientai){
        txtDots = new TextView[fragmentList.size()];

        layoutDots.removeAllViews();
        for (int i =0; i< fragmentList.size();i++){
            txtDots[i] = new TextView(this);
            txtDots[i].setText(Html.fromHtml("&#8226;"));//tạo dấu chấm tròn
            txtDots[i].setTextSize(30);
            txtDots[i].setTextColor(getIdColor(R.color.colorSilder));

            layoutDots.addView(txtDots[i]);
        }
        txtDots[vitrihientai].setTextColor(getIdColor((R.color.ColorToolbar)));
    }
    private int getIdColor(int Color){
        int color = 0;
        if (Build.VERSION.SDK_INT < 21 ){
            color = ContextCompat.getColor(this, Color);
        }else {
            color = getResources().getColor(Color);
        }
        return color;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        themDotsSlider(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.txtVietDanhGia:
                Intent iThemDanhGia = new Intent(this, DanhGiaActivity.class);
                iThemDanhGia.putExtra("masp",masp);
                 startActivity(iThemDanhGia);
                break;
            case R.id.txtDanhSachDanhGia:
//                Bundle bundle = new Bundle();
//                bundle.putInt("masp",masp);
//                FragmentManager fm = getSupportFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//                DanhSachDanhGiaFragment danhSachDanhGiaFragment = new DanhSachDanhGiaFragment();
//                danhSachDanhGiaFragment.setArguments(bundle);
//                ft.replace(R.id.myLayout,danhSachDanhGiaFragment);
                    Intent iDSDanhGia = new Intent(ChiTietSanPhamActivity.this,
                                            DanhSachDanhGiaActivity.class);
                    iDSDanhGia.putExtra("masp",masp);
                    startActivity(iDSDanhGia);
                break;
            case  R.id.imThemGioHang:
                Fragment fragment = fragmentList.get(0);
                View view = fragment.getView();
                ImageView imageView = view.findViewById(R.id.imHinhSlider);
                Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();


                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                byte[] hinhSPGioHang = byteArrayOutputStream.toByteArray();

                sanphamGioHang.setHINHGIOHANG(hinhSPGioHang);
                sanphamGioHang.setSoLuong(1);

                presnterLogicChiTietSanPham.ThemGioHang(sanphamGioHang,this);
                break;
            case R.id.btnMuaNgay:
                Fragment fragment1 = fragmentList.get(0);
                View view1 = fragment1.getView();
                ImageView imageView1 = view1.findViewById(R.id.imHinhSlider);
                Bitmap bitmap1 = ((BitmapDrawable)imageView1.getDrawable()).getBitmap();


                ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
                bitmap1.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream1);
                byte[] hinhSPGioHang1 = byteArrayOutputStream1.toByteArray();

                sanphamGioHang.setHINHGIOHANG(hinhSPGioHang1);
                sanphamGioHang.setSoLuong(1);

                presnterLogicChiTietSanPham.ThemGioHang(sanphamGioHang,this);

                Intent iThanhToan = new Intent(ChiTietSanPhamActivity.this, ThanhToanActivity.class);
                startActivity(iThanhToan);

                break;
        }
    }

    @Override
    public void HienThiDanhGia(List<DanhGia> danhGias) {

        AdapterDanhGia adapterDanhGia = new AdapterDanhGia(this,danhGias,3);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewChiTietDanhgia.setLayoutManager(layoutManager);
        recyclerViewChiTietDanhgia.setAdapter(adapterDanhGia);

        adapterDanhGia.notifyDataSetChanged();
    }

    @Override
    public void ThemGioHangThanhCong() {
        Toast.makeText(this, "Add product success", Toast.LENGTH_SHORT).show();
        txtGioHang.setText(String.valueOf(presnterLogicChiTietSanPham.DemSanPhamTrongGioHang(this)));
    }

    @Override
    public void ThemGioHangThatBai() {
        Toast.makeText(this, "Fail add product", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (onPause){
            PresnterLogicChiTietSanPham presnterLogicChiTietSanPham= new PresnterLogicChiTietSanPham();
            txtGioHang.setText(String.valueOf(presnterLogicChiTietSanPham.DemSanPhamTrongGioHang(this)));
        }else {

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPause = true;
    }
}
