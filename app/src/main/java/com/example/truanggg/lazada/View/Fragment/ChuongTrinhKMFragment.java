package com.example.truanggg.lazada.View.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.truanggg.lazada.Adapter.AdapterDSKM;
import com.example.truanggg.lazada.Model.ObjectClass.KhuyenMai;
import com.example.truanggg.lazada.Presenter.KhuyenMai.PresenterLogicKM;
import com.example.truanggg.lazada.R;
import com.example.truanggg.lazada.View.ViewKhuyenMai;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChuongTrinhKMFragment extends Fragment implements ViewKhuyenMai{

    LinearLayout lnKhuyenMai;
    RecyclerView recyclerView_Km;
    PresenterLogicKM presenterLogicKM;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.layout_chuongtrinhkm, container, false);
        recyclerView_Km= view.findViewById(R.id.recycler_DSKhuyenMai);
        lnKhuyenMai = view.findViewById(R.id.lnHinhKhuyenMai);

        presenterLogicKM = new PresenterLogicKM(this);
        presenterLogicKM.LayDanhSachSPKM();
        return  view;
    }

    @Override
    public void HienThidanhSachKM(List<KhuyenMai> khuyenMais) {

        int demhinh = khuyenMais.size();
        if (demhinh > 5){
            demhinh =5;
        }else {
            demhinh = khuyenMais.size();
        }

        lnKhuyenMai.removeAllViews();
        for(int i =0; i <demhinh; i++ ){
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            LinearLayout.LayoutParams layoutParams =new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200);
            layoutParams.setMargins(0,10,0,10);
            imageView.setLayoutParams(layoutParams);

            Picasso.with(getContext()).load(khuyenMais.get(i).getHINHKHUYENMAI())
                    .resize(720,200).into(imageView);

            lnKhuyenMai.addView(imageView);
            Log.d("hinh",imageView+"");
        }
        AdapterDSKM adapterDSKM = new AdapterDSKM(getContext(),khuyenMais);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView_Km.setLayoutManager(layoutManager);
        recyclerView_Km.setAdapter(adapterDSKM);
        adapterDSKM.notifyDataSetChanged();
    }
}
