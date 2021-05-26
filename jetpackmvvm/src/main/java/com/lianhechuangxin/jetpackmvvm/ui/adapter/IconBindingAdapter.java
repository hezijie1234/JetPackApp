package com.lianhechuangxin.jetpackmvvm.ui.adapter;

import androidx.databinding.BindingAdapter;


import com.lianhechuangxin.jetpackmvvm.ui.view.PlayPauseView;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;
import net.steamcrafted.materialiconlib.MaterialIconView;

/**
 * TODO 同学们一定要看哦，才能知道为什么，那么多同学一直编译不通过，各种错误，真正的原因是在这里哦，这里和布局建立绑定的呢
 *  注意：这个类的使用，居然是和fragment_player.xml里面的 setIcon / isPlaying 挂钩
 */
public class IconBindingAdapter {

    @BindingAdapter(value = {"isPlaying"}, requireAll = false)
    public static void isPlaying(PlayPauseView pauseView, boolean isPlaying) {
        if (isPlaying) {
            pauseView.play();
        } else {
            pauseView.pause();
        }
    }

    @BindingAdapter(value = {"mdIcon"}, requireAll = false)
    public static void setIcon(MaterialIconView view, MaterialDrawableBuilder.IconValue iconValue) {
        view.setIcon(iconValue);
    }
}
