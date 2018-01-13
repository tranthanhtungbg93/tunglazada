package com.example.truanggg.lazada.View.DangNhap_DangKy;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.truanggg.lazada.Adapter.ViewpagerAdapterDangNhap;
import com.example.truanggg.lazada.R;

public class LoginActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tabLayout = findViewById(R.id.tabDangNhap);
        viewPager = findViewById(R.id.viewpagerDangNhap);
        toolbar = findViewById(R.id.toolbarDangNhap);

        setSupportActionBar(toolbar);
        // lấy dữ liệu bên ViewpagerAdapterDangNhap

        ViewpagerAdapterDangNhap viewpagerAdapterDangNhap = new ViewpagerAdapterDangNhap(getSupportFragmentManager());
        viewPager.setAdapter(viewpagerAdapterDangNhap);
        viewpagerAdapterDangNhap.notifyDataSetChanged();

        tabLayout.setupWithViewPager(viewPager);
    }
}
