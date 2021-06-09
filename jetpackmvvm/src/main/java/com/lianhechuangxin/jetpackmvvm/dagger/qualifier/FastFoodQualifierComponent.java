package com.lianhechuangxin.jetpackmvvm.dagger.qualifier;

import com.lianhechuangxin.jetpackmvvm.DaggerTest3Activity;

import dagger.Component;

@Component(modules = FastFoodQualifierModule.class)
public interface FastFoodQualifierComponent {

    void inject(DaggerTest3Activity daggerTest3Activity);
}
