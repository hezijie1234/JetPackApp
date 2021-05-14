package com.example.myapplication.paging.pagekeysource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.myapplication.paging.itemsource.Person;

public class PersonViewModel2 extends ViewModel {

private final LiveData<PagedList<Person>> pagedListLiveData;

    public PersonViewModel2() {
        DataSource.Factory<Integer, Person> factory = new KeyDataFactory();

        PagedList.Config pConfig = new PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20)
                .build();

        pagedListLiveData = new LivePagedListBuilder<Integer,Person>(factory,pConfig)
                .build();
    }

    public LiveData<PagedList<Person>> getPagedListLiveData() {
        return pagedListLiveData;
    }
}
