package com.lianhechuangxin.jetpackmvvm.request;

import androidx.lifecycle.MutableLiveData;

import com.lianhechuangxin.jetpackmvvm.data.bean.LibraryInfo;
import com.lianhechuangxin.jetpackmvvm.data.repository.HttpRequestManager;

import java.util.List;

public class InfoRequestViewModel {

    private MutableLiveData<List<LibraryInfo>> mutableLiveData;

    public MutableLiveData<List<LibraryInfo>> getMutableLiveData(){
        if (null == mutableLiveData){
            mutableLiveData = new MutableLiveData<>();
        }
        return mutableLiveData;
    }

    public void requestLibraryInfo() {
        HttpRequestManager.getInstance().getLibraryInfo(getMutableLiveData());
    }
}
