package com.example.truanggg.lazada.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.truanggg.lazada.View.Fragment.ChuongTrinhKMFragment;
import com.example.truanggg.lazada.View.Fragment.DienTuFragment;
import com.example.truanggg.lazada.View.Fragment.LamDepFragment;
import com.example.truanggg.lazada.View.Fragment.MeVaBeFragment;
import com.example.truanggg.lazada.View.Fragment.NhaCuavaDoiSongFragment;
import com.example.truanggg.lazada.View.Fragment.NoiBatFragment;
import com.example.truanggg.lazada.View.Fragment.TheThaoVaDuLichFragment;
import com.example.truanggg.lazada.View.Fragment.ThoiTrangFragment;
import com.example.truanggg.lazada.View.Fragment.ThuongHieuFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Truang on 11/30/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter{
    List<Fragment> listFrag = new ArrayList<Fragment>();
    List<String> titleFrag = new ArrayList<String>();
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        listFrag.add(new NoiBatFragment());
        listFrag.add(new ChuongTrinhKMFragment());
        listFrag.add(new DienTuFragment());
        listFrag.add(new NhaCuavaDoiSongFragment());
        listFrag.add(new MeVaBeFragment());
        listFrag.add(new LamDepFragment());
        listFrag.add(new ThoiTrangFragment());
        listFrag.add(new TheThaoVaDuLichFragment());
        listFrag.add(new ThuongHieuFragment());

        titleFrag.add("Nổi Bật");
        titleFrag.add("Chương Trình Khuyến Mãi");
        titleFrag.add("Điện Tử");
        titleFrag.add("Nhà Cửa Và Đời Sống");
        titleFrag.add("Mẹ Và Bé");
        titleFrag.add("Làm Đẹp");
        titleFrag.add("Thời Trang");
        titleFrag.add("Thể Thao Và Du Lịch");
        titleFrag.add("Thương Hiệu");
    }

    @Override
    public Fragment getItem(int position) {
        return listFrag.get(position);
    }

    @Override
    public int getCount() {
        return listFrag.size();
    }
    // Phương thức cho ra title của từng fragment ứng với từng tablayout
    @Override
    public CharSequence getPageTitle(int position) {
        return titleFrag.get(position);
    }
}
