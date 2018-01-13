package com.example.truanggg.lazada.Model.ObjectClass;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by Truang on 12/26/2017.
 */

public class LoadMoreSrcroll extends RecyclerView.OnScrollListener {

    int itemandautien= 0;
    int tongitem = 0;
    int itemloadtruoc = 10;
    RecyclerView.LayoutManager layoutManager;
    ILoadMore iLoadMore;

    public LoadMoreSrcroll(RecyclerView.LayoutManager layoutManager,ILoadMore iLoadMore) {
        this.layoutManager = layoutManager;
        this.iLoadMore = iLoadMore;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        tongitem = layoutManager.getItemCount();
        if (layoutManager instanceof LinearLayoutManager){ // instanceof được hiển thị như
            itemandautien = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }else if(layoutManager instanceof GridLayoutManager) {
            itemandautien = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }
        if(tongitem <= itemandautien + itemloadtruoc){
            Log.d("kiemtra",tongitem +"- " + itemandautien);
        }

    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
}
