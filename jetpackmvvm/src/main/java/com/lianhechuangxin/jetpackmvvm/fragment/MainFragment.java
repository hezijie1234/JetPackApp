package com.lianhechuangxin.jetpackmvvm.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kunminx.player.bean.dto.ChangeMusic;
import com.lianhechuangxin.jetpackmvvm.DaggerTestActivity;
import com.lianhechuangxin.jetpackmvvm.R;
import com.lianhechuangxin.jetpackmvvm.bridge.MainViewModel;
import com.lianhechuangxin.jetpackmvvm.bridge.MusicRequestViewModel;
import com.lianhechuangxin.jetpackmvvm.data.bean.TestAlbum;
import com.lianhechuangxin.jetpackmvvm.databinding.AdapterPlayItemBinding;
import com.lianhechuangxin.jetpackmvvm.databinding.FragmentMainBinding;
import com.lianhechuangxin.jetpackmvvm.player.PlayerManager;
import com.lianhechuangxin.jetpackmvvm.ui.adapter.SimpleBaseBindingAdapter;
import com.lianhechuangxin.jetpackmvvm.ui.base.BaseFragment;

public class MainFragment extends BaseFragment {

    private FragmentMainBinding fragmentMainBinding;
    private MainViewModel mainViewModel;
    private SimpleBaseBindingAdapter<TestAlbum.TestMusic, AdapterPlayItemBinding> adapter;
    private MusicRequestViewModel musicRequestViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        fragmentMainBinding = FragmentMainBinding.bind(view);
        fragmentMainBinding.setLifecycleOwner(this);
        mainViewModel = getFragmentViewModelProvider(this).get(MainViewModel.class);
        musicRequestViewModel = getFragmentViewModelProvider(this).get(MusicRequestViewModel.class);
        fragmentMainBinding.setClick(new ClickProxy());
        fragmentMainBinding.setVm(mainViewModel);
        return view;
    }


    /**
     *
     * @param view
     * @param savedInstanceState
     * onCreateView执行完成之后会执行此方法
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainViewModel.initTabAndPage.set(true);
// 触发，---> 还要加载WebView
        mainViewModel.pageAssetPath.set("JetPack之 WorkManager.html");
        // 展示数据，适配器里面的的数据 展示出来
        // 设置设配器(item的布局 和 适配器的绑定)
        adapter = new SimpleBaseBindingAdapter<TestAlbum.TestMusic, AdapterPlayItemBinding>(getContext(), R.layout.adapter_play_item) {
            @Override
            protected void onSimpleBindItem(AdapterPlayItemBinding binding, TestAlbum.TestMusic item, RecyclerView.ViewHolder holder) {
                binding.tvTitle.setText(item.getTitle()); // 标题
                binding.tvArtist.setText(item.getArtist().getName()); // 歌手 就是 艺术家
                Glide.with(binding.ivCover.getContext()).load(item.getCoverImg()).into(binding.ivCover); // 左右边的图片

                // 歌曲下标记录
                int currentIndex = PlayerManager.getInstance().getAlbumIndex(); // 歌曲下标记录

                // 播放的标记
                binding.ivPlayStatus.setColor(currentIndex == holder.getAdapterPosition()
                        ? Color.RED : Color.TRANSPARENT); // 播放的时候，右变状态图标就是红色， 如果对不上的时候，就是没有

                // 点击Item
                binding.getRoot().setOnClickListener(v -> {
                    Toast.makeText(mContext, "播放音乐", Toast.LENGTH_SHORT).show();
                    PlayerManager.getInstance().playAudio(holder.getAdapterPosition());
                });
            }
        };

        fragmentMainBinding.rv.setAdapter(adapter);
        // 播放相关业务的数据（如果这个数据发生了变化，为了更好的体验） 盯着
        PlayerManager.getInstance().getChangeMusicLiveData().observe(getViewLifecycleOwner(), new Observer<ChangeMusic>() {
            @Override
            public void onChanged(ChangeMusic changeMusic) {
                adapter.notifyDataSetChanged(); // 更新及时
            }
        });
        musicRequestViewModel.getFreeMusicsLiveData().observe(getViewLifecycleOwner(), new Observer<TestAlbum>() {
            @Override
            public void onChanged(TestAlbum musicAlbum) {
                if (musicAlbum != null && musicAlbum.getMusics() != null) {
                    // 这里特殊：直接更新UI，越快越好
                    adapter.setList(musicAlbum.getMusics()); // 数据加入适配器
                    adapter.notifyDataSetChanged();

                    // 播放相关的业务需要这个数据
                    if (PlayerManager.getInstance().getAlbum() == null ||
                            !PlayerManager.getInstance().getAlbum().getAlbumId().equals(musicAlbum.getAlbumId())) {
                        PlayerManager.getInstance().loadAlbum(musicAlbum);
                    }
                }
            }
        });
        // 请求数据
        // 保证我们列表没有数据（music list）
        if (PlayerManager.getInstance().getAlbum() == null) {
            musicRequestViewModel.requestFreeMusics();
        }
    }

   public class ClickProxy{
        public void openMenu(){

//            sharedViewModel.openOrCloseDrawer.setValue(true);
            startActivity(new Intent(getActivity(), DaggerTestActivity.class));
        }

        public void search(){
            nav().navigate(R.id.action_mainFragment_to_searchFragment);
        }
    }
}
