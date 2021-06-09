package com.lianhechuangxin.jetpackmvvm.dagger.module;

import com.lianhechuangxin.jetpackmvvm.DaggerTest2Activity;


import dagger.Component;

@Component(modules = AppleModule.class)
public interface AppleComponent {
    void inject(DaggerTest2Activity daggerTestActivity);
}
