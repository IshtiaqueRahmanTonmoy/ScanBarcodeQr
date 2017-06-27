package com.exampledemo.qrcode.scanbarcodeqrdemonuts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.exampledemo.qrcode.scanbarcodeqrdemonuts.com.entity.AdapterClass;
import com.exampledemo.qrcode.scanbarcodeqrdemonuts.com.entity.Shop;
import com.exampledemo.qrcode.scanbarcodeqrdemonuts.com.sqlitedb.DatabaseHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FormDataActivity extends AppCompatActivity {

    private EditText edtName,edtResult,edtDate,edtNote;
    private Button btnSave;
    private DatabaseHandler db;
    private String name,result,date,note;
    SharedPreferences mSharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_data);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Form Data Activity");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        edtName = (EditText) findViewById(R.id.edtName);
        edtResult = (EditText) findViewById(R.id.edtResult);
        edtDate = (EditText) findViewById(R.id.edtDate);
        edtNote = (EditText) findViewById(R.id.edtNote);
        btnSave = (Button) findViewById(R.id.btnSave);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd / MM / yyyy ");

        String strDate = mdformat.format(calendar.getTime());
        edtDate.setText(""+strDate);

        mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String value=(mSharedPreference.getString("message", ""));
        //String code =(mSharedPreference.getString("code", "Default_Value"));

        if(value!=null) {
            edtResult.setText("" + value);
        }
        else {
            edtResult.setText("");
        }
        //Toast.makeText(FormDataActivity.this, ""+value, Toast.LENGTH_SHORT).show();

        db = new DatabaseHandler(this);
        db.open();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // int id = 1;
                name=edtName.getText().toString();
                result=edtResult.getText().toString();
                date=edtDate.getText().toString();
                note=edtNote.getText().toString();

                if(db.isExist(result)){
                    Toast.makeText(getApplicationContext(),"already exist", Toast.LENGTH_SHORT).show();
                }else{

                    db.insert(name,result,date,note);
                    mSharedPreference.edit().clear().commit();
                    edtName.setText("");
                    edtResult.setText("");
                    edtDate.setText("");
                    edtNote.setText("");
                    Toast.makeText(getApplicationContext(),"Successfully added", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        edtName.setText("");
        edtResult.setText("");
        edtDate.setText("");
        edtNote.setText("");
    }
}
