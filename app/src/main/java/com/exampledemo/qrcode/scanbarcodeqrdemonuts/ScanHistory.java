package com.exampledemo.qrcode.scanbarcodeqrdemonuts;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.exampledemo.qrcode.scanbarcodeqrdemonuts.com.adapter.ScanAdapter;
import com.exampledemo.qrcode.scanbarcodeqrdemonuts.com.entity.AdapterClass;
import com.exampledemo.qrcode.scanbarcodeqrdemonuts.com.sqlitedb.DatabaseHandler;

import java.util.ArrayList;
import java.util.List;

public class ScanHistory extends AppCompatActivity {

    private List<AdapterClass> finalList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ScanAdapter mAdapter;
    private DatabaseHandler db;
    private ArrayList<AdapterClass> arraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_history);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Scan History Activity");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        db = new DatabaseHandler(this);
        db.open();

        mAdapter = new ScanAdapter(finalList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareData();
    }

    private void prepareData() {

        arraylist = (ArrayList) db.getAllValues();

        for(int i=0;i<arraylist.size();i++){
            String scannedname = arraylist.get(i).getScannedbyname();
            String scannedresult = arraylist.get(i).getScannedresult();
            String date = arraylist.get(i).getDate();
            AdapterClass adapterClass = new AdapterClass(scannedname,scannedresult,date);
            finalList.add(adapterClass);
        }

        mAdapter.notifyDataSetChanged();

      /*
        for (AdapterClass country : arraylist) {


            //String log = "Id: " + country.getId() + " ,Name: " + country.getScannedresult() + " ,Population: " + country.getScannedbyname();
            // Writing Countries to log
            //Log.d("Name: ", log);
            //Toast.makeText(ScanHistory.this, ""+country.getScannedbyname(), Toast.LENGTH_SHORT).show();
        }
        */
       /*
        AdapterClass adapterclass = new AdapterClass("Mad Max: Fury Road", "Action & Adventure", "2015");
        movieList.add(adapterclass);

        adapterclass = new AdapterClass("Inside Out", "Animation, Kids & Family", "2015");
        movieList.add(adapterclass);

        adapterclass = new AdapterClass("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        movieList.add(adapterclass);

        mAdapter.notifyDataSetChanged();
        */
    }
}
