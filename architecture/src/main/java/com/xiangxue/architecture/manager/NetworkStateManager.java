package com.xiangxue.architecture.manager;

import android.content.IntentFilter;
import android.net.ConnectivityManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.xiangxue.architecture.bridge.callback.UnPeekLiveData;

import static java.util.Objects.requireNonNull;

/**
 * 此观察者 去 观察 BaseActivity 的 生命周期方法
 */
public class NetworkStateManager implements DefaultLifecycleObserver {

    private static final NetworkStateManager S_MANAGER = new NetworkStateManager();

    // 如果当网络状态发生变化时，让BaseFragment --  TODO 子类可以重写该方法，统一的网络状态通知和处理
    public final UnPeekLiveData<NetState> mNetworkStateCallback = new UnPeekLiveData<>();

    private NetworkStateReceive mNetworkStateReceive;

    private NetworkStateManager() {
    }

    public static NetworkStateManager getInstance() {
        return S_MANAGER;
    }

    /**
     * 那么观察到 观察 BaseActivity 的 生命周期方法 后 做什么事情呢？
     * 答；就是注册一个 广播，此广播可以接收到信息（然后 输出 “网络不给力”）
     * @param owner
     */
    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        mNetworkStateReceive = new NetworkStateReceive();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        if (owner instanceof AppCompatActivity) {
            ((AppCompatActivity) owner).registerReceiver(mNetworkStateReceive, filter);
        } else if (owner instanceof Fragment) {
            requireNonNull(((Fragment) owner).getActivity())
                    .registerReceiver(mNetworkStateReceive, filter);
        }
    }

    /**
     * 那么观察到 观察 BaseActivity 的 生命周期方法 后 做什么事情呢？
     * 答；就是移除一个 广播
     * @param owner
     */
    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        if (owner instanceof AppCompatActivity) {
            ((AppCompatActivity) owner).unregisterReceiver(mNetworkStateReceive);
        } else if (owner instanceof Fragment) {
            requireNonNull(((Fragment) owner).getActivity())
                    .unregisterReceiver(mNetworkStateReceive);
        }
    }
}
