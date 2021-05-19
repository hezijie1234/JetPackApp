package com.lianhechuangxin.jetpackmvvm.data.bean;

import java.io.File;

/**
 * 下载文件的实体Bena封装
 *
 *  被 DownloadViewModel使用
 *  被 IRemoteRequest 接口使用
 *  被 HttpRequestManager （模拟下载使用）
 *  被 CanBeStoppedUseCase 使用了，具体细节还需要看研究
 */
public class DownloadFile {

    private int progress;
    private File file;
    private boolean forgive;

    public DownloadFile() {
    }

    public DownloadFile(int progress, File file, boolean forgive) {
        this.progress = progress;
        this.file = file;
        this.forgive = forgive;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public boolean isForgive() {
        return forgive;
    }

    public void setForgive(boolean forgive) {
        this.forgive = forgive;
    }
}
