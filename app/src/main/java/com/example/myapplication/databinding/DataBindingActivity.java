package com.example.myapplication.databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.R;

public class DataBindingActivity extends AppCompatActivity {


    private User user;
    ActivityDataBindingBinding viewDataBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_data_binding);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        user = new User("hezijie", "12345");
        viewDataBinding.setClick(new ClickProxy());
        viewDataBinding.setUser(user);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    user.setName(user.getName() + i);
                    user.setPassword(user.getPassword() + "0");
//                    viewDataBinding.setUser(user);
//                    viewDataBinding.setVariable(BR.user,user);

                }
            }
        }).start();
    }

    public class ClickProxy{
        public void btn1Click(){
            Toast.makeText(DataBindingActivity.this,"按钮已被店家",Toast.LENGTH_SHORT).show();
        }

        public void btn2Click(){
            Toast.makeText(DataBindingActivity.this,"按钮e二倍点击",Toast.LENGTH_SHORT).show();
        }
    }
}