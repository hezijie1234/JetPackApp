package com.lianhechuangxin.jetpackmvvm.dagger.base;

import javax.inject.Inject;

public class Cola {
    @Inject
    public Cola() {
    }

    public String returnName(){
        return "百事可乐";
    }
}
