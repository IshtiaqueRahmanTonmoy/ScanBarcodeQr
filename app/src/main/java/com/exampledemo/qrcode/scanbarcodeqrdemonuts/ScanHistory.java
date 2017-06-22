package com.exampledemo.qrcode.scanbarcodeqrdemonuts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.exampledemo.qrcode.scanbarcodeqrdemonuts.com.adapter.ScanAdapter;
import com.exampledemo.qrcode.scanbarcodeqrdemonuts.com.entity.AdapterClass;

import java.util.ArrayList;
import java.util.List;

public class ScanHistory extends AppCompatActivity {

    private List<AdapterClass> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ScanAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_history);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new ScanAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareData();
    }

    private void prepareData() {
        AdapterClass adapterclass = new AdapterClass("Mad Max: Fury Road", "Action & Adventure", "2015");
        movieList.add(adapterclass);

        adapterclass = new AdapterClass("Inside Out", "Animation, Kids & Family", "2015");
        movieList.add(adapterclass);

        adapterclass = new AdapterClass("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        movieList.add(adapterclass);

        mAdapter.notifyDataSetChanged();
    }
}
