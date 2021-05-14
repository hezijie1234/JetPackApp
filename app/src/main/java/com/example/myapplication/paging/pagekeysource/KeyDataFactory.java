package com.example.myapplication.paging.pagekeysource;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.example.myapplication.paging.itemsource.DataRepository;
import com.example.myapplication.paging.itemsource.Person;

public class KeyDataFactory extends DataSource.Factory<Integer, Person> {
    @NonNull
    @Override
    public DataSource<Integer, Person> create() {
        return new PageSourceData(new DataRepository());
    }
}
