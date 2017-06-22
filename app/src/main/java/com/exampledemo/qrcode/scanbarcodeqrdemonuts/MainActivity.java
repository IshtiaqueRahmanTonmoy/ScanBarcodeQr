package com.exampledemo.qrcode.scanbarcodeqrdemonuts;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static TextView tvresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("QR Scanner Dashboard");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        //tvresult = (TextView) findViewById(R.id.tvresult);
        Button btnQRcode = (Button) findViewById(R.id.btnQrcode);
        Button btnFormData = (Button) findViewById(R.id.btnFormData);
        Button btnHistoryData = (Button) findViewById(R.id.btnHistory);

        btnQRcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                startActivity(intent);
            }
        });

        btnFormData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormDataActivity.class);
                startActivity(intent);
            }
        });

        btnHistoryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScanHistory.class);
                startActivity(intent);
            }
        });

    }
}
