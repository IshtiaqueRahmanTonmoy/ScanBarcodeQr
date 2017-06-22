package com.exampledemo.qrcode.scanbarcodeqrdemonuts;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.exampledemo.qrcode.scanbarcodeqrdemonuts.com.entity.AdapterClass;
import com.exampledemo.qrcode.scanbarcodeqrdemonuts.com.sqlitedb.DatabaseHandler;

import java.util.List;

public class FormDataActivity extends AppCompatActivity {

    private EditText edtName,edtResult,edtDate,edtNote;
    private Button btnSave;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_data);

        db = new DatabaseHandler(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Form Data Activity");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        edtName = (EditText) findViewById(R.id.edtName);
        edtResult = (EditText) findViewById(R.id.edtResult);
        edtDate = (EditText) findViewById(R.id.edtDate);
        edtNote = (EditText) findViewById(R.id.edtNote);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = edtResult.getText().toString();
                String name = edtName.getText().toString();
                String date = edtResult.getText().toString();
                String note = edtResult.getText().toString();

                //db.isExist(result);

                Log.d("values","result"+result+"name"+name+"date"+date+"note"+note);
               /*
                    if(db.isExist(result) == true){
                    Toast.makeText(FormDataActivity.this, "Data exists..", Toast.LENGTH_SHORT).show();

                }
                else{
                    db.addData(new AdapterClass(name,result,date,note));
                    Toast.makeText(FormDataActivity.this, "Successfully added..", Toast.LENGTH_SHORT).show();

                   }
                */

                }
        });
    }
}
