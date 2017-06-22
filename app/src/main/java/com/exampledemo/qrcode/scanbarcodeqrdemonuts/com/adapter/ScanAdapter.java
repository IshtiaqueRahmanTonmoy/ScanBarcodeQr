package com.exampledemo.qrcode.scanbarcodeqrdemonuts.com.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exampledemo.qrcode.scanbarcodeqrdemonuts.R;
import com.exampledemo.qrcode.scanbarcodeqrdemonuts.com.entity.AdapterClass;

import java.util.List;

/**
 * Created by TONMOYPC on 6/22/2017.
 */
public class ScanAdapter extends RecyclerView.Adapter<ScanAdapter.MyViewHolder> {
    private List<AdapterClass> scanList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtname, txtresult, txtdate;

        public MyViewHolder(View view) {
            super(view);
            txtname = (TextView) view.findViewById(R.id.name);
            txtresult = (TextView) view.findViewById(R.id.result);
            txtdate = (TextView) view.findViewById(R.id.date);
        }
    }

    public ScanAdapter(List<AdapterClass> scanList) {
        this.scanList = scanList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AdapterClass scanadapter = scanList.get(position);
        holder.txtname.setText(scanadapter.getScannedbyname());
        holder.txtresult.setText(scanadapter.getScannedresult());
        holder.txtdate.setText(scanadapter.getDate());
    }

    @Override
    public int getItemCount() {
        return scanList.size();
    }
}
