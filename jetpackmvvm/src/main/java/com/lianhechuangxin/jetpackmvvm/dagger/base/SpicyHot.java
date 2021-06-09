package com.lianhechuangxin.jetpackmvvm.dagger.base;

import javax.inject.Inject;

public class SpicyHot {
    public Cola cola;
    public Meat meat;
    public Sanvege sanvege;
    public Vegetable vegetable;

    @Inject
    public SpicyHot(Cola cola, Meat meat, Sanvege sanvege, Vegetable vegetable) {
        this.cola = cola;
        this.meat = meat;
        this.sanvege = sanvege;
        this.vegetable = vegetable;
    }

    public String eat(){
        return "eat all";
    }
}
