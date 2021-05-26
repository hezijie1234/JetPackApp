package com.lianhechuangxin.jetpackmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;

import android.os.Bundle;

import com.lianhechuangxin.jetpackmvvm.bridge.MainActivityViewModel;
import com.lianhechuangxin.jetpackmvvm.databinding.ActivityMainBinding;
import com.lianhechuangxin.jetpackmvvm.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {


    private boolean isListened = false;
    private ActivityMainBinding viewDataBinding;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewDataBinding.setLifecycleOwner(this);
        mainActivityViewModel = getActivityViewModelProvider(this).get(MainActivityViewModel.class);
        viewDataBinding.setMainModel(mainActivityViewModel);
// 间接的可以打开菜单 （观察）
        mSharedViewModel.openOrCloseDrawer.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                mainActivityViewModel.openDrawer.setValue(true); // 触发，就会改变，---> 观察（打开菜单逻辑）
            }
        });

    }
    /**
     * 详情看：https://www.cnblogs.com/lijunamneg/archive/2013/01/19/2867532.html
     * 这个onWindowFocusChanged指的是这个Activity得到或者失去焦点的时候 就会call。。
     * 也就是说 如果你想要做一个Activity一加载完毕，就触发什么的话 完全可以用这个！！！
     *  entry: onStart---->onResume---->onAttachedToWindow----------->onWindowVisibilityChanged--visibility=0---------->onWindowFocusChanged(true)------->
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!isListened){
            mSharedViewModel.timeToAddSlideListener.setValue(true); // 触发改变
            isListened = true;
        }
    }

    /**
     * https://www.jianshu.com/p/d54cd7a724bc
     * Android中在按下back键时会调用到onBackPressed()方法，
     * onBackPressed相对于finish方法，还做了一些其他操作，
     * 而这些操作涉及到Activity的状态，所以调用还是需要谨慎对待。
     */
    @Override
    public void onBackPressed() {
        // super.onBackPressed();

        mSharedViewModel.closeSlidePanelIfExpanded.setValue(true); // 触发改变
    }
}