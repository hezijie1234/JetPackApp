package com.lianhechuangxin.jetpackmvvm.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.lianhechuangxin.jetpackmvvm.R;
import com.lianhechuangxin.jetpackmvvm.data.bean.LibraryInfo;
import com.lianhechuangxin.jetpackmvvm.databinding.AdapterLiberaryBinding;
import com.lianhechuangxin.jetpackmvvm.databinding.FragmentDrawerBinding;
import com.lianhechuangxin.jetpackmvvm.databinding.FragmentMainBinding;
import com.lianhechuangxin.jetpackmvvm.request.InfoRequestViewModel;
import com.lianhechuangxin.jetpackmvvm.ui.adapter.SimpleBaseBindingAdapter;
import com.lianhechuangxin.jetpackmvvm.ui.base.BaseFragment;

import java.util.List;

public class DrawerFragment extends BaseFragment {

    private FragmentDrawerBinding fragmentMainBinding;
    private SimpleBaseBindingAdapter<LibraryInfo, AdapterLiberaryBinding> simpleBaseBindingAdapter;
    private InfoRequestViewModel infoRequestViewModel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drawer, container, false);
        infoRequestViewModel = new InfoRequestViewModel();
        fragmentMainBinding = FragmentDrawerBinding.bind(view);
        fragmentMainBinding.setLifecycleOwner(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        simpleBaseBindingAdapter = new SimpleBaseBindingAdapter<LibraryInfo, AdapterLiberaryBinding>(getContext(), R.layout.adapter_liberary) {
            @Override
            protected void onSimpleBindItem(AdapterLiberaryBinding binding, LibraryInfo item, RecyclerView.ViewHolder holder) {
                binding.setInfo(item);
                binding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showShortToast("?????????");
                    }
                });
            }
        };
        fragmentMainBinding.rv.setAdapter(simpleBaseBindingAdapter);
        // ???????????????????????????????????? libraryLiveData ???????????????????????????????????????????????? RecyclerView
        infoRequestViewModel.getMutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<LibraryInfo>>() {
            @Override
            public void onChanged(List<LibraryInfo> libraryInfos) {
                // ???????????????????????????UI???????????????
                simpleBaseBindingAdapter.setList(libraryInfos);
                simpleBaseBindingAdapter.notifyDataSetChanged();
            }
        });

        // ????????????
        infoRequestViewModel.requestLibraryInfo();
    }

    public class ClickProxy {
        public void allClick() {
            showShortToast("?????????????????????");
        }
    }
}
