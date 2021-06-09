package com.lianhechuangxin.jetpackmvvm.dagger.base;

import com.lianhechuangxin.jetpackmvvm.DaggerTestActivity;

import dagger.Component;

@Component
public interface ColaComponent {
    void inject(DaggerTestActivity daggerTestActivity);
}
