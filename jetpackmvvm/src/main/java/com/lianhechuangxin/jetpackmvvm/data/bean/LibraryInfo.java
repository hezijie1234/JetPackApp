package com.lianhechuangxin.jetpackmvvm.data.bean;

/**
 * 音乐描述的实体类（显示在：例如：左侧半边白色的 “享学VIP之JetPack项目”）
 * 被 adapter_library.xml(DataBinding) 使用了
 * 被 InfoRequestViewModel 使用了
 * 被 HttpRequestManager 使用了
 * 被 IRemoteRequest接口 使用了
 * 被 IRemoteRequest接口 使用了
 * 被 DrawerFragment 使用了
 */
public class LibraryInfo {

    private String title; // XiangxeMusic
    private String summary; // “享学VIP之JetPack项目”
    private String url; // 本来是用来跳转到 WebView要加载的网页路径的

    public LibraryInfo() {
    }

    public LibraryInfo(String title, String summary, String url) {
        this.title = title;
        this.summary = summary;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
