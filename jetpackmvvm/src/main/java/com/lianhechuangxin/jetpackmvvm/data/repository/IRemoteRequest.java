package com.lianhechuangxin.jetpackmvvm.data.repository;

import androidx.lifecycle.MutableLiveData;


import com.lianhechuangxin.jetpackmvvm.data.bean.DownloadFile;
import com.lianhechuangxin.jetpackmvvm.data.bean.LibraryInfo;
import com.lianhechuangxin.jetpackmvvm.data.bean.TestAlbum;

import java.util.List;

/**
 * 远程请求标准接口（在仓库里面）
 * 只为 HttpRequestManager 服务
 */
public interface IRemoteRequest {

    void getFreeMusic(MutableLiveData<TestAlbum> liveData);

    void getLibraryInfo(MutableLiveData<List<LibraryInfo>> liveData);

    // 下载文件
    void downloadFile(MutableLiveData<DownloadFile> liveData);

}
