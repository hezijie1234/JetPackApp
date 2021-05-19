package com.lianhechuangxin.jetpackmvvm.data.config;

import android.os.Environment;

import com.xiangxue.architecture.utils.Utils;

public class Configs {

    // 封面路径地址
    public static final String COVER_PATH = Utils.getApp().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();

    public static final String TAG = "Derry";
}
