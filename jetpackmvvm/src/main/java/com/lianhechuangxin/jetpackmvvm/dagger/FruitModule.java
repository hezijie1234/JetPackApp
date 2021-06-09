package com.lianhechuangxin.jetpackmvvm.dagger;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class FruitModule {
    @Named("orangle")
    @Provides
    public Orangle getOrangle(){
        return new Orangle();
    }

    @Named("pea")
    @Provides
    public pea getPea(){
        return new pea();
    }
}
