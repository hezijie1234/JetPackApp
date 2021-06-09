package com.lianhechuangxin.jetpackmvvm.dagger.qualifier;

import dagger.Module;
import dagger.Provides;

@Module
public class FastFoodQualifierModule {

    @com.lianhechuangxin.jetpackmvvm.dagger.qualifier.KFCAnno
    @Provides
    public FastFood getKFC(){
        return new KFC();
    }
    @com.lianhechuangxin.jetpackmvvm.dagger.qualifier.MaAnno
    @Provides
    public FastFood getMa(){
        return new Mado();
    }
}
