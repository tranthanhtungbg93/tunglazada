package com.example.truanggg.lazada.View.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.truanggg.lazada.Adapter.AdapterNoiBat;
import com.example.truanggg.lazada.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoiBatFragment extends Fragment {
    AdapterNoiBat adapterNoiBat;
    RecyclerView recyclerView;

    public NoiBatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_noi_bat, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewNoiBat);
        List<String> dulieu = new ArrayList<>();
        for (int i = 0; i<20; i++){
            String ten = "Noi Bat " + i;
            dulieu.add(ten);
        }
        adapterNoiBat = new AdapterNoiBat(getActivity(),dulieu);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterNoiBat);
        adapterNoiBat.notifyDataSetChanged();
        return view;
    }

}
