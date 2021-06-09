package com.lianhechuangxin.jetpackmvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.lianhechuangxin.jetpackmvvm.dagger.DaggerFruitComponent;
import com.lianhechuangxin.jetpackmvvm.dagger.FruitModule;
import com.lianhechuangxin.jetpackmvvm.dagger.Orangle;
import com.lianhechuangxin.jetpackmvvm.dagger.pea;

import javax.inject.Inject;
import javax.inject.Named;

public class DaggerTest4Activity extends AppCompatActivity {
    @Named("orangle")
    @Inject
    public Orangle orangle;

    @Named("pea")
    @Inject
    public pea pea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_test4);
        DaggerFruitComponent.builder().fruitModule(new FruitModule()).build().inject(this);

        Log.e("111", "onCreate: " + pea.eat() );
        Log.e("111", "onCreate: " + orangle.eat() );
    }
}