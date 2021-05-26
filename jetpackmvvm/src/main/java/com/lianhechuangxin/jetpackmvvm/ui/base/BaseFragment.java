package com.lianhechuangxin.jetpackmvvm.ui.base;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.lianhechuangxin.jetpackmvvm.App;
import com.lianhechuangxin.jetpackmvvm.bridge.SharedViewModel;


/**
 * 所有Fragment的父类
 */
public class BaseFragment extends Fragment {

    protected AppCompatActivity mActivity;
    public SharedViewModel sharedViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = getAppViewModelProvider().get(SharedViewModel.class);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
    }

    // // 同学们，测试用的，暂无用
    public boolean isDebug() {
        return mActivity.getApplicationContext().getApplicationInfo() != null &&
                (mActivity.getApplicationContext().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    // 同学们，只是提示而已
    public void showLongToast(String text) {
        Toast.makeText(mActivity.getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    // 同学们，只是提示而已
    public void showShortToast(String text) {
        Toast.makeText(mActivity.getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    // 同学们，只是提示而已
    public void showLongToast(int stringRes) {
        showLongToast(mActivity.getApplicationContext().getString(stringRes));
    }

    // 同学们，只是提示而已
    public void showShortToast(int stringRes) {
        showShortToast(mActivity.getApplicationContext().getString(stringRes));
    }

    // 给当前BaseFragment用的
    protected ViewModelProvider getAppViewModelProvider() {
        return ((App) mActivity.getApplicationContext()).getAppViewModelProvider(mActivity);
    }

    // 给所有的fragment提供的函数，可以顺利的拿到 ViewModel
    protected ViewModelProvider getFragmentViewModelProvider(Fragment fragment) {
        return new ViewModelProvider(fragment, fragment.getDefaultViewModelProviderFactory());
    }

    // 给所有的fragment提供的函数，可以顺利的拿到 ViewModel
    protected ViewModelProvider getActivityViewModelProvider(AppCompatActivity activity) {
        return new ViewModelProvider(activity, activity.getDefaultViewModelProviderFactory());
    }

    /**
     * 为了给所有的fragment，导航跳转fragment的
     * @return
     */
    protected NavController nav() {
        return NavHostFragment.findNavController(this);
    }

    /**
     * 对外暴露 SharedViewModel，所有的fragment都可以使用 共享的SharedViewModel
     * @return
     */
    public SharedViewModel getSharedViewModel() {
        return sharedViewModel;
    }

}
