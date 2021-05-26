package com.lianhechuangxin.jetpackmvvm.bridge;

import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    public ObservableBoolean initTabAndPage = new ObservableBoolean();

    public ObservableField<String> pageAssetPath = new ObservableField<>();
}
