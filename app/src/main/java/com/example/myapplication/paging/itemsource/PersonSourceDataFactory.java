package com.example.myapplication.paging.itemsource;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

public class PersonSourceDataFactory extends DataSource.Factory<Integer,Person> {
    @NonNull
    @Override
    public DataSource<Integer, Person> create() {
        return new CustomItemDataSource(new DataRepository());
    }
}
