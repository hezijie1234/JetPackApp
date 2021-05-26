package com.lianhechuangxin.jetpackmvvm.data.binding;

import androidx.databinding.BindingAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.lianhechuangxin.jetpackmvvm.R;
import com.xiangxue.architecture.ui.adapter.CommonViewPagerAdapter;

/**
 * TODO 同学们一定要看哦，才能知道为什么，那么多同学一直编译不通过，各种错误，真正的原因是在这里哦，这里和布局建立绑定的呢
 * 注意：这个类的使用，居然是和fragment_player.xml里面的initTabAndPage挂钩
 */
public class TabPageBindingAdapter {

    @BindingAdapter(value = {"initTabAndPage"}, requireAll = false)
    public static void initTabAndPage(TabLayout tabLayout, boolean initTabAndPage) {
        if (initTabAndPage) {
            int count = tabLayout.getTabCount();
            String[] title = new String[count];
            for (int i = 0; i < count; i++) {
                TabLayout.Tab tab = tabLayout.getTabAt(i);
                if (tab != null && tab.getText() != null) {
                    title[i] = tab.getText().toString();
                }
            }
            ViewPager viewPager = (tabLayout.getRootView()).findViewById(R.id.view_pager);
            if (viewPager != null) {
                viewPager.setAdapter(new CommonViewPagerAdapter(count, false, title));
                tabLayout.setupWithViewPager(viewPager);
            }
        }
    }

    @BindingAdapter(value = {"tabSelectedListener"}, requireAll = false)
    public static void tabSelectedListener(TabLayout tabLayout, TabLayout.OnTabSelectedListener listener) {
        tabLayout.addOnTabSelectedListener(listener);
    }

}
