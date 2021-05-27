package com.lianhechuangxin.jetpackmvvm.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.kunminx.player.PlayingInfoManager;
import com.kunminx.player.bean.dto.ChangeMusic;
import com.kunminx.player.bean.dto.PlayingMusic;
import com.lianhechuangxin.jetpackmvvm.R;
import com.lianhechuangxin.jetpackmvvm.bridge.PlayerViewModel;
import com.lianhechuangxin.jetpackmvvm.databinding.FragmentMainBinding;
import com.lianhechuangxin.jetpackmvvm.databinding.FragmentPlayerBinding;
import com.lianhechuangxin.jetpackmvvm.player.PlayerManager;
import com.lianhechuangxin.jetpackmvvm.ui.base.BaseFragment;
import com.lianhechuangxin.jetpackmvvm.ui.view.PlayerSlideListener;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

public class PlayerFragment extends BaseFragment {

    private FragmentPlayerBinding fragmentMainBinding;
    private PlayerViewModel playerViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player, container, false);
        fragmentMainBinding = FragmentPlayerBinding.bind(view);
        fragmentMainBinding.setLifecycleOwner(this);
        fragmentMainBinding.setClick(new ClickProxy());
        fragmentMainBinding.setEvent(new EventHandler());
        playerViewModel = getFragmentViewModelProvider(this).get(PlayerViewModel.class);
        fragmentMainBinding.setVm(playerViewModel);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 观察
        sharedViewModel.timeToAddSlideListener.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (view.getParent().getParent() instanceof SlidingUpPanelLayout) {
                    SlidingUpPanelLayout sliding = (SlidingUpPanelLayout) view.getParent().getParent();

                    // 添加监听（弹上来）
                    sliding.addPanelSlideListener(new PlayerSlideListener(fragmentMainBinding, sliding));
                }
            }
        });

        // 我是播放条，我要去变化，我成为观察者 -----> 播放相关的类PlayerManager
        PlayerManager.getInstance().getChangeMusicLiveData().observe(getViewLifecycleOwner(), new Observer<ChangeMusic>() {
            @Override
            public void onChanged(ChangeMusic changeMusic) {

                // 例如 ：理解 切歌的时候， 音乐的标题，作者，封面 状态等 改变
                playerViewModel.title.set(changeMusic.getTitle());
                playerViewModel.artist.set(changeMusic.getSummary());
                playerViewModel.coverImg.set(changeMusic.getImg());
            }
        });

        // 我是播放条，我要去变化，我成为观察者 -----> 播放相关的类PlayerManager
        PlayerManager.getInstance().getPlayingMusicLiveData().observe(getViewLifecycleOwner(), new Observer<PlayingMusic>() {
            @Override
            public void onChanged(PlayingMusic playingMusic) {

                // 例如 ：理解 切歌的时候，  播放进度的改变  按钮图标的改变
                playerViewModel.maxSeekDuration.set(playingMusic.getDuration()); // 总时长
                playerViewModel.currentSeekPosition.set(playingMusic.getPlayerPosition()); // 拖动条
            }
        });

        // 播放/暂停是一个控件  图标的true和false
        PlayerManager.getInstance().getPauseLiveData().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                playerViewModel.isPlaying.set(!aBoolean); // 播放时显示暂停，暂停时显示播放
            }
        });

        // 列表循环，单曲循环，随机播放
        PlayerManager.getInstance().getPlayModeLiveData().observe(getViewLifecycleOwner(), new Observer<Enum>() {
            @Override
            public void onChanged(Enum anEnum) {
                int resultID;
                if (anEnum == PlayingInfoManager.RepeatMode.LIST_LOOP) { // 列表循环
                    playerViewModel.playModeIcon.set(MaterialDrawableBuilder.IconValue.REPEAT);
                    resultID = R.string.play_repeat; // 列表循环
                } else if (anEnum == PlayingInfoManager.RepeatMode.ONE_LOOP) { // 单曲循环
                    playerViewModel.playModeIcon.set(MaterialDrawableBuilder.IconValue.REPEAT_ONCE);
                    resultID = R.string.play_repeat_once; // 单曲循环
                }else { // 随机循环
                    playerViewModel.playModeIcon.set(MaterialDrawableBuilder.IconValue.SHUFFLE);
                    resultID = R.string.play_shuffle; // 随机循环
                }

                // 真正的改变
                if (view.getParent().getParent() instanceof SlidingUpPanelLayout) {
                    SlidingUpPanelLayout sliding = (SlidingUpPanelLayout) view.getParent().getParent();

                    if (sliding.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED) {
                        // 这里一定会弹出：“列表循环” or “单曲循环” or “随机播放”
                        showShortToast(resultID);
                    }
                }
            }
        });

        // 例如：场景  back  要不要做什么事情
        sharedViewModel.closeSlidePanelIfExpanded.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (view.getParent().getParent() instanceof SlidingUpPanelLayout) {
                    SlidingUpPanelLayout sliding = (SlidingUpPanelLayout) view.getParent().getParent();

                    // 如果是扩大，也就是，详情页面展示出来的
                    if (sliding.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED) {
                        sliding.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED); // 缩小了
                    } else {
                        sharedViewModel.activityCanBeClosedDirectly.setValue(true);
                    }

                } else {
                    sharedViewModel.activityCanBeClosedDirectly.setValue(true);
                }
            }
        });
    }

    /**
     * 当我们点击的时候，我们要触发
     */
    public class ClickProxy {

        /*public void playerMode() {
            PlayerManager.getInstance().changeMode();
        }*/

        public void previous() {
            PlayerManager.getInstance().playPrevious();
        }

        public void next() {
            PlayerManager.getInstance().playNext();
        }

        // 点击缩小的
        public void slideDown() {
            sharedViewModel.closeSlidePanelIfExpanded.setValue(true);
        }

        //　更多的
        public void more() {

        }

        public void togglePlay() {
            PlayerManager.getInstance().togglePlay();
        }

        public void playMode() {
            PlayerManager.getInstance().changeMode();
        }

        public void showPlayList() {
            showShortToast("最近播放的细节，我没有搞...");
        }
    }

    /**
     * 专门更新 拖动条进度相关的
     */
    public class EventHandler implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}

        // 一拖动 松开手  把当前进度值 告诉PlayerManager
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            PlayerManager.getInstance().setSeek(seekBar.getProgress());
        }
    }
}
