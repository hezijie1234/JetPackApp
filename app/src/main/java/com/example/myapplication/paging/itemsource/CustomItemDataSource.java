package com.example.myapplication.paging.itemsource;

import androidx.annotation.NonNull;
import androidx.paging.ItemKeyedDataSource;


import java.util.List;

public class CustomItemDataSource extends ItemKeyedDataSource<Integer,Person> {

    private DataRepository repository;
    private int i = 2;
    public CustomItemDataSource(DataRepository repository) {
        this.repository = repository;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Person> callback) {
        List<Person> people = repository.initData(params.requestedLoadSize);
        callback.onResult(people);
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Person> callback) {
        List<Person> people = repository.loadPageData(params.key, params.requestedLoadSize);
        if (null != people)
        callback.onResult(people);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Person> callback) {
        List<Person> people = repository.loadPageData(params.key, params.requestedLoadSize);
//        if (null != people)
//        callback.onResult(people);
    }

    @NonNull
    @Override
    public Integer getKey(@NonNull Person item) {
        return ++i;
    }
}
