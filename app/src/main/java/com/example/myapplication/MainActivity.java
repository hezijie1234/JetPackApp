package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.databinding.DataBindingActivity;
import com.example.myapplication.navigation.NavigationActivity;
import com.example.myapplication.room.JetRoomActivity;

public class MainActivity extends AppCompatActivity  {

    private static final String TAG = "111";
    private TextView tvText;
    private TextView tvBtn;
    private NameModel nameModel;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "onCreate: " );
        tvText = findViewById(R.id.tv_text);
        tvBtn = findViewById(R.id.tv_btn);
        nameModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(NameModel.class);
        nameModel.getCurName().observe(this,new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvText.setText(s);
            }
        });

        tvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在主线程要调用这个方法
//                nameModel.i++;
//                nameModel.getCurName().setValue("" + nameModel.i);
                //在子线程必须要调用下面的方法
//                nameModel.getCurName().postValue("");
                startActivity(new Intent(MainActivity.this,LiveDataBusActivity.class));
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    LiveDataBus.getInstance().with("center",String.class).postValue("" + i);
                }

            }
        }).start();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreateX(LifecycleOwner lifecycleOwner){
        Log.e(TAG, "onCreateX: " );
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStartX(LifecycleOwner lifecycleOwner){
        Log.e(TAG, "onStartX: " );
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPauseX(LifecycleOwner lifecycleOwner){
        Log.e(TAG, "onPauseX: " );
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStopX(LifecycleOwner lifecycleOwner){
        Log.e(TAG, "onStopX: " );
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroyX(LifecycleOwner lifecycleOwner){
        Log.e(TAG, "onDestroyX: ");
    }

    public void intentToBinding(View view) {
        startActivity(new Intent(this, DataBindingActivity.class));
    }

    public void intentToRoom(View view) {
        startActivity(new Intent(this, JetRoomActivity.class));
    }

    public void intentToNavigation(View view) {
        startActivity(new Intent(this, NavigationActivity.class));
    }
}