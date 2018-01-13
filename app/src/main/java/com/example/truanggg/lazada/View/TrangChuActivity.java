package com.example.truanggg.lazada.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.truanggg.lazada.Adapter.ExpandAdapter;
import com.example.truanggg.lazada.Adapter.ViewPagerAdapter;
import com.example.truanggg.lazada.Model.ModelDangNhap_DangKy.ModelDangNhap;
import com.example.truanggg.lazada.Model.ObjectClass.LoaiSanPham;
import com.example.truanggg.lazada.Presenter.ChiTietSanPham.PresnterLogicChiTietSanPham;
import com.example.truanggg.lazada.Presenter.TrangChu.XuLyMenu.PresenterLogicXuLyMenu;
import com.example.truanggg.lazada.R;
import com.example.truanggg.lazada.View.DangNhap_DangKy.LoginActivity;
import com.example.truanggg.lazada.View.GioHang.DanhSachSPGioHang;
import com.example.truanggg.lazada.View.TimKiem.TimKiemActivity;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

///**
// * Created by Truang on 11/30/2017.
// */

public class TrangChuActivity extends AppCompatActivity implements ViewXuLyMenu,
                                            GoogleApiClient.OnConnectionFailedListener,
                                            AppBarLayout.OnOffsetChangedListener{
    public static final String SERVER_NAME = "http://192.168.1.103/weblazada/loaisanpham.php";
    public static final String SERVER = "http://192.168.1.103/weblazada";
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private DrawerLayout drawerLayout;
    TextView txtGioHang;
    ActionBarDrawerToggle mToggle;
    ExpandableListView expandableListView;
    PresenterLogicXuLyMenu logicXuLyMenu;
    String tennguoidung = "";
    AccessToken accessToken;
    Menu menu;
    ModelDangNhap modelDangNhap;
    MenuItem menuItDangXuat,menuItDangNhap;
    GoogleApiClient mGoogleAPIClient;
    GoogleSignInResult mGoogleSignInResult;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    boolean onPause;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.trang_chu_activity);

        toolbar = findViewById(R.id.toolbar);
        viewPager =findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabLayout);
        drawerLayout = findViewById(R.id.drawerLayout);
        expandableListView = findViewById(R.id.epMenu);

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        appBarLayout = findViewById(R.id.appbar);

        // xóa title của thanh toolbar
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        // gán mtoggle vào, DrawerToggle phải khởi tạo dưới toolbar
        mToggle = new ActionBarDrawerToggle(this,drawerLayout,null,R.string.close,R.string.open);
        drawerLayout.addDrawerListener(mToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToggle.syncState();

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        //tabLayout sẽ nhận vào viewPager thông qua ViewPagerAdapter
        tabLayout.setupWithViewPager(viewPager);

        logicXuLyMenu = new PresenterLogicXuLyMenu(this);
        modelDangNhap = new ModelDangNhap();

        logicXuLyMenu.layDanhSachMenu();

        mGoogleAPIClient = modelDangNhap.LayGoogleCLient(this,this);

        //appbar
        appBarLayout.addOnOffsetChangedListener(this);

    }
    // kích hoạt cái menu


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu,menu);
        this.menu = menu;
        MenuItem menuItem = menu.findItem(R.id.itGioHang);
        View GiaoDienCustomGioHang  = MenuItemCompat.getActionView(menuItem); // Lấy được lauout của custom giỏ hàng
        txtGioHang = GiaoDienCustomGioHang.findViewById(R.id.txtSoLuongSPGioHang);

        GiaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioHang = new Intent(TrangChuActivity.this, DanhSachSPGioHang.class);
                startActivity(iGioHang);
            }
        });

        PresnterLogicChiTietSanPham presnterLogicChiTietSanPham= new PresnterLogicChiTietSanPham();
        txtGioHang.setText(String.valueOf(presnterLogicChiTietSanPham.DemSanPhamTrongGioHang(this)));
        menuItDangXuat = menu.findItem(R.id.dangxuat);
        menuItDangNhap = menu.findItem(R.id.dangnhap);

        accessToken = logicXuLyMenu.LayTenNguoiDungFb();
        mGoogleSignInResult = modelDangNhap.LayThongTinDangNhapGG(mGoogleAPIClient);

        if (accessToken != null ){
            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken,
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
                            try {
                                tennguoidung = object.getString("name");

                                menuItDangNhap.setTitle(tennguoidung);

                                Log.d("token",tennguoidung);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            Bundle param =new Bundle();
            param.putString("fields","name");

            graphRequest.setParameters(param);
            graphRequest.executeAsync(); // khi nó chạy executeAsync thì nó mới chạy vào hàm onCompleted
        }

        if(mGoogleSignInResult !=null ){
            menuItDangNhap.setTitle(mGoogleSignInResult.getSignInAccount().getDisplayName());
            Log.d("Name Google",mGoogleSignInResult.getSignInAccount().getDisplayName());
        }
        String tennv = modelDangNhap.LayCachedDAngNhap(this);
        if (!tennv.equals("")){
            menuItDangNhap.setTitle(tennv);
        }else {

        }

        if(accessToken != null || mGoogleSignInResult != null || !tennv.equals("") ){
            menuItDangXuat.setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        int id = item.getItemId();
        switch (id){
            case R.id.dangnhap:
                if (accessToken == null && mGoogleSignInResult == null && modelDangNhap.LayCachedDAngNhap(this).equals("")){
                    Intent iDangNhap = new Intent(this, LoginActivity.class);
                    startActivity(iDangNhap);
                }
                break;
            case R.id.dangxuat:
                if (accessToken !=null ){
                    LoginManager.getInstance().logOut();
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }
                if(mGoogleSignInResult != null){
                    Auth.GoogleSignInApi.signOut(mGoogleAPIClient);
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }if(!modelDangNhap.LayCachedDAngNhap(this).equals("")){
                    modelDangNhap.UpdateCachedDangNhap(this,"");
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
            }
            break;
            case R.id.itSearch:
                Intent iTiemKiem = new Intent(this, TimKiemActivity.class);
                startActivity(iTiemKiem);
                break;
        }
        return true;
    }

    @Override
    public void HienThiDanhSachMenu(List<LoaiSanPham> loaiSanPhamList) {
        ExpandAdapter expandAdapter = new ExpandAdapter(this,loaiSanPhamList);
        expandableListView.setAdapter(expandAdapter);
        expandAdapter.notifyDataSetChanged();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
    // cho phép lắng nghe sự kiện của thằng appbar
    //verticalOffset kích thước thay đổi của appbar
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        Log.d("kiem tra appbar", collapsingToolbarLayout.getHeight() + "-" +
                verticalOffset + "-" + ViewCompat.getMinimumHeight(collapsingToolbarLayout));
        if(collapsingToolbarLayout.getHeight() + verticalOffset <= 1.5 * ViewCompat.getMinimumHeight(collapsingToolbarLayout)){
            LinearLayout linearLayout = appBarLayout.findViewById(R.id.lbSearch);
            linearLayout.animate().alpha(0).setDuration(200);

            MenuItem itSearch = menu.findItem(R.id.itSearch);
            itSearch.setVisible(true);
        }else {
            LinearLayout linearLayout = appBarLayout.findViewById(R.id.lbSearch);
            linearLayout.animate().alpha(1).setDuration(200);

            try{
                MenuItem itSearch = menu.findItem(R.id.itSearch);
                itSearch.setVisible(false);
            }catch (Exception e){

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (onPause){
            PresnterLogicChiTietSanPham presnterLogicChiTietSanPham= new PresnterLogicChiTietSanPham();
            txtGioHang.setText(String.valueOf(presnterLogicChiTietSanPham.DemSanPhamTrongGioHang(this)));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPause = true;
    }
}
