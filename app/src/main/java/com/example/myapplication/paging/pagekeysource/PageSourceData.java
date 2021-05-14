package com.example.myapplication.paging.pagekeysource;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.myapplication.paging.itemsource.DataRepository;
import com.example.myapplication.paging.itemsource.Person;

import java.util.List;

public class PageSourceData extends PageKeyedDataSource<Integer, Person> {

    private DataRepository dataRepository;

    public PageSourceData(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Person> callback) {
        Log.e("111", "loadInitial: " + params.requestedLoadSize);//初始化加载三页数据
        List<Person> dataList = dataRepository.initData(params.requestedLoadSize);
        callback.onResult(dataList, null, 2);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Person> callback) {
        Log.e("111", "loadBefore: " + params.key );
        List<Person> dataList = dataRepository.loadPageData(params.key, params.requestedLoadSize);
        if (dataList != null) {
            callback.onResult(dataList, params.key - 1);
        }
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Person> callback) {
        Log.e("111", "loadAfter: " + params.key );
        List<Person> dataList = dataRepository.loadPageData(params.key, params.requestedLoadSize);
        if (dataList != null) {
            callback.onResult(dataList, params.key + 1);
        }
    }
}
