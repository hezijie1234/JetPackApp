package com.lianhechuangxin.jetpackmvvm.bridge;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    public final MutableLiveData<Boolean> openDrawer = new MutableLiveData<>();
    public final MutableLiveData<Boolean> drawerCanOpen = new MutableLiveData<>();
    {
        drawerCanOpen.setValue(true);
    }
}
