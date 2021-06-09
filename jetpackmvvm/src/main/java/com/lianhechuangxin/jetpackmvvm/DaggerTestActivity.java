package com.lianhechuangxin.jetpackmvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lianhechuangxin.jetpackmvvm.dagger.base.Cola;
import com.lianhechuangxin.jetpackmvvm.dagger.base.DaggerColaComponent;
import com.lianhechuangxin.jetpackmvvm.dagger.base.SpicyHot;
import com.lianhechuangxin.jetpackmvvm.dagger.module.Apple;
import com.lianhechuangxin.jetpackmvvm.dagger.module.AppleModule;



import javax.inject.Inject;


public class DaggerTestActivity extends AppCompatActivity {
    private static final String TAG = "111";
    @Inject
    public Cola cola;
    @Inject
    public SpicyHot spicyHot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_test);
        DaggerColaComponent.builder().build().inject(this);
//        DaggerAppleComponent.builder().appleModule(new AppleModule()).build().inject(this);
        Log.e(TAG, "onCreate: " + cola.returnName() );
        Log.e(TAG, "onCreate: " + spicyHot.eat() );
        Log.e(TAG, "onCreate: " + spicyHot.meat.eatMeat() );
//        Log.e(TAG, "onCreate: " + apple.eat() );

    }

    public void intentTo2(View view) {
        startActivity(new Intent(this,DaggerTest2Activity.class));
    }
    public void intentTo3(View view) {
        startActivity(new Intent(this,DaggerTest3Activity.class));
    }
    public void intentTo4(View view) {
        startActivity(new Intent(this,DaggerTest4Activity.class));
    }
}