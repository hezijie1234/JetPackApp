package com.lianhechuangxin.jetpackmvvm.bridge;

import android.graphics.drawable.Drawable;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

import com.kunminx.player.PlayingInfoManager;
import com.lianhechuangxin.jetpackmvvm.R;
import com.lianhechuangxin.jetpackmvvm.player.PlayerManager;
import com.xiangxue.architecture.utils.Utils;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

/**
 * 同学们注意：底部播放条的 UI数据管理层
 */
public class PlayerViewModel extends ViewModel {

    // 歌曲名称
    public final ObservableField<String> title = new ObservableField<>();

    // 歌手
    public final ObservableField<String> artist = new ObservableField<>();

    // 歌曲图片的地址  htpp:xxxx/img0.jpg
    public final ObservableField<String> coverImg = new ObservableField<>();

    // 歌曲正方形图片
    public final ObservableField<Drawable> placeHolder = new ObservableField<>();

    // 歌曲的总时长，会显示在拖动条后面
    public final ObservableInt maxSeekDuration = new ObservableInt();

    // 当前拖动条的进度值
    public final ObservableInt currentSeekPosition = new ObservableInt();

    // 播放按钮，状态的改变(播放和暂停)
    public final ObservableBoolean isPlaying = new ObservableBoolean();

    // 这个是播放图标的状态，也都是属于状态的改变
    public final ObservableField<MaterialDrawableBuilder.IconValue> playModeIcon = new ObservableField<>();

    // 构造代码块，默认初始化
    {
        title.set(Utils.getApp().getString(R.string.app_name)); // 默认信息
        artist.set(Utils.getApp().getString(R.string.app_name)); // 默认信息
        placeHolder.set(Utils.getApp().getResources().getDrawable(R.drawable.bg_album_default)); // 默认的播放图标

        if (PlayerManager.getInstance().getRepeatMode() == PlayingInfoManager.RepeatMode.LIST_LOOP) { // 如果等于“列表循环”
            playModeIcon.set(MaterialDrawableBuilder.IconValue.REPEAT);
        } else if (PlayerManager.getInstance().getRepeatMode() == PlayingInfoManager.RepeatMode.ONE_LOOP) { // 如果等于“单曲循环”
            playModeIcon.set(MaterialDrawableBuilder.IconValue.REPEAT_ONCE);
        } else {
            playModeIcon.set(MaterialDrawableBuilder.IconValue.SHUFFLE); // 随机播放
        }
    }
}
