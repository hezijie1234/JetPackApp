package com.lianhechuangxin.jetpackmvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.lianhechuangxin.jetpackmvvm.dagger.module.Apple;
import com.lianhechuangxin.jetpackmvvm.dagger.module.AppleModule;
import com.lianhechuangxin.jetpackmvvm.dagger.module.DaggerAppleComponent;

import javax.inject.Inject;
import javax.inject.Named;

public class DaggerTest2Activity extends AppCompatActivity {
    @Named("null")
    @Inject()
    public Apple apple;
    @Named("all")
    @Inject()
    public Apple apple2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_test2);
        DaggerAppleComponent.builder().appleModule(new AppleModule()).build().inject(this);
        Log.e("111", "onCreate: " + apple.weight + apple.type + apple.eat());
        Log.e("111", "onCreate:" + apple2.weight + apple2.type + apple2.eat());
    }
}