package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LiveDataBusActivity extends AppCompatActivity {

    private TextView tvCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data_bus);
        tvCenter = findViewById(R.id.tv_center);
        LiveDataBus.getInstance().with("center",String.class).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String o) {
                tvCenter.setText(o);
            }
        });
    }
}