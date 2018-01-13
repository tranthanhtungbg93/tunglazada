package com.example.truanggg.lazada.View.TimKiem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import com.example.truanggg.lazada.Adapter.AdapterTopDienThoaiDientu;
import com.example.truanggg.lazada.Model.ObjectClass.ILoadMore;
import com.example.truanggg.lazada.Model.ObjectClass.LoadMoreSrcroll;
import com.example.truanggg.lazada.Model.ObjectClass.SanPham;
import com.example.truanggg.lazada.Presenter.TiemKiem.PresenterlogicTimKiem;
import com.example.truanggg.lazada.R;
import com.example.truanggg.lazada.View.ViewTimKiem;

import java.util.List;

/**
 * Created by Truang on 1/1/2018.
 */

public class TimKiemActivity extends AppCompatActivity implements ViewTimKiem,ILoadMore,SearchView.OnQueryTextListener {
    RecyclerView recyclerView;
    Toolbar toolbar;
    PresenterlogicTimKiem presenterlogicTimKiem;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_timkiem);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recycler_timkiem);
        presenterlogicTimKiem = new PresenterlogicTimKiem(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timkiem,menu);

        MenuItem itSearch = menu.findItem(R.id.itSearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(itSearch);
        searchView.setIconified(false);

        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public void TimKiemThanhCong(List<SanPham> sanPhamList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        AdapterTopDienThoaiDientu adapterTopDienThoaiDientu = new AdapterTopDienThoaiDientu
                (this,R.layout.custom_recyclerview_list_topdienthoaivamaytinhbang,sanPhamList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTopDienThoaiDientu);
        recyclerView.addOnScrollListener(new LoadMoreSrcroll(layoutManager,this));
        adapterTopDienThoaiDientu.notifyDataSetChanged();
    }

    @Override
    public void TimKiemThatBai() {

    }

    @Override
    public void LoadMore(int tongitem) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        presenterlogicTimKiem.TimKiemSanPhamTheoTenSp(query,0);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
