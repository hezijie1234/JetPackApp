package com.lianhechuangxin.jetpackmvvm.dagger;

import com.lianhechuangxin.jetpackmvvm.DaggerTest4Activity;

import dagger.Component;

@Component(modules = FruitModule.class)
public interface FruitComponent {

    void inject(DaggerTest4Activity daggerTest4Activity);
}
