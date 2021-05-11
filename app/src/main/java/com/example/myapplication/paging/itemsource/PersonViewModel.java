package com.example.myapplication.paging.itemsource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class PersonViewModel extends ViewModel {

    private LiveData<PagedList<Person>> pagedListLiveData;

    public PersonViewModel() {
        DataSource.Factory<Integer, Person> factory = new PersonSourceDataFactory();
        pagedListLiveData = new LivePagedListBuilder<Integer, Person>(factory, 20).build();
    }

    public LiveData<PagedList<Person>> getPagedListLiveData() {
        return pagedListLiveData;
    }
}
