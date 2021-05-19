package com.lianhechuangxin.jetpackmvvm.bridge;

import androidx.lifecycle.ViewModel;

import com.xiangxue.architecture.bridge.callback.UnPeekLiveData;

import java.util.ArrayList;
import java.util.List;

/**
 * SharedViewModel的职责是用于 页面通信的
 */
public class SharedViewModel extends ViewModel {

    // 存放记录，打开过“搜索界面”就会记录下来，owner.getClass().getSimpleName():SearchFragment / owner.getClass().getSimpleName():SearchFragment
    public static final List<String> TAG_OF_SECONDARY_PAGES = new ArrayList<>();

    // 是为了 sliding.addPanelSlideListener(new PlayerSlideListener(mBinding, sliding));
    public final UnPeekLiveData<Boolean> timeToAddSlideListener = new UnPeekLiveData<>();

    // 点击“播放条”弹上来
    // 播放详情中 左手边滑动图标(点击的时候)，与 MainActivity back 是 会set  ----> 如果是扩大，也就是 详情页面展示了出来
    public final UnPeekLiveData<Boolean> closeSlidePanelIfExpanded = new UnPeekLiveData<>();

    // 活动关闭的一些记录（实在没有发现他，到底有什么卵用）
    public final UnPeekLiveData<Boolean> activityCanBeClosedDirectly = new UnPeekLiveData<>();

    // openMenu打开菜单的时候会 set触发---> 改变 openDrawer.setValue(aBoolean); 的值
    public final UnPeekLiveData<Boolean> openOrCloseDrawer = new UnPeekLiveData<>();

    // 开启和关闭 卡片相关的状态，如果发生改变 会和 allowDrawerOpen 挂钩
    public final UnPeekLiveData<Boolean> enableSwipeDrawer = new UnPeekLiveData<>();
}
