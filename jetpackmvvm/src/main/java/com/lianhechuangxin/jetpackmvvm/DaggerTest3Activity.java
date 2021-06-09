package com.lianhechuangxin.jetpackmvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;


import com.lianhechuangxin.jetpackmvvm.dagger.qualifier.DaggerFastFoodQualifierComponent;
import com.lianhechuangxin.jetpackmvvm.dagger.qualifier.FastFood;
import com.lianhechuangxin.jetpackmvvm.dagger.qualifier.FastFoodQualifierModule;
import com.lianhechuangxin.jetpackmvvm.dagger.qualifier.KFCAnno;
import com.lianhechuangxin.jetpackmvvm.dagger.qualifier.MaAnno;

import javax.inject.Inject;

public class DaggerTest3Activity extends AppCompatActivity {
    @KFCAnno
    @Inject
    public FastFood kfc;
    @MaAnno
    @Inject
    public FastFood ma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_test3);
        DaggerFastFoodQualifierComponent.builder().fastFoodQualifierModule(new FastFoodQualifierModule()).build().inject(this);
        Log.e("111", "onCreate: " + kfc.returenContent() );
        Log.e("111", "onCreate: " + ma.returenContent() );
    }
}