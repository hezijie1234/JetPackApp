package com.example.myapplication;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NameModel extends ViewModel {

    private MutableLiveData<String> curName;

    public MutableLiveData<String> getCurName() {
        if (null == curName){
            curName = new MutableLiveData<>();
        }
        return curName;
    }

    public int i = 0;
}
