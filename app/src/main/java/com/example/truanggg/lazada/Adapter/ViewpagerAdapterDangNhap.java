package com.example.truanggg.lazada.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.truanggg.lazada.View.DangNhap_DangKy.Fragment.FragmentDangKy;
import com.example.truanggg.lazada.View.DangNhap_DangKy.Fragment.FragmentDangNhap;

/**
 * Created by Truang on 12/6/2017.
 */
// khi dùng viewpager phải tao thêm class adapter và kế thừa fragmentPagerAdapter
public class ViewpagerAdapterDangNhap extends FragmentPagerAdapter {

    public ViewpagerAdapterDangNhap(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FragmentDangNhap fragmentDangNhap = new FragmentDangNhap();
                return fragmentDangNhap;
            case 1:
                FragmentDangKy fragmentDangKy = new FragmentDangKy();
                return fragmentDangKy;
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
    // kế thừa thêm phương thức getTitle
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Đăng Nhập";
            case 1:
                return "Đăng Ký";
            default:
                return null;
        }
    }
}
