package com.lianhechuangxin.jetpackmvvm.dagger.module;

public class Apple {
    public String weight;
    public String type;

    public Apple(String weight) {
        this.weight = weight;
    }

    public Apple(String weight, String type) {
        this.weight = weight;
        this.type = type;
    }

    public Apple() {
    }

    public String eat(){
        return "吃苹果";
    }
}
