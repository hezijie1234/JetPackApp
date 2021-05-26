package com.lianhechuangxin.jetpackmvvm.bridge;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lianhechuangxin.jetpackmvvm.data.bean.TestAlbum;
import com.lianhechuangxin.jetpackmvvm.data.repository.HttpRequestManager;


/**
 * 音乐资源 请求 相关的 ViewModel（只负责 MainFragment）
 */
public class MusicRequestViewModel extends ViewModel {

    private MutableLiveData<TestAlbum> freeMusicsLiveData;

    public MutableLiveData<TestAlbum> getFreeMusicsLiveData() {
        if (freeMusicsLiveData == null) {
            freeMusicsLiveData = new MutableLiveData<>();
        }
        return freeMusicsLiveData;
    }

    public void requestFreeMusics() {
        HttpRequestManager.getInstance().getFreeMusic(getFreeMusicsLiveData());
    }
}
