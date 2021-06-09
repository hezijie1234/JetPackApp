package com.lianhechuangxin.jetpackmvvm.dagger.module;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class AppleModule {
    @Named("null")
    @Provides
    public Apple getApple(){
        return new Apple();
    }


    @Named("all")
    @Provides
    public Apple getAppleAll(){
        return new Apple("1","hongfushi");
    }


}
