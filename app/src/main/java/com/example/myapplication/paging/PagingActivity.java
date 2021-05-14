package com.example.myapplication.paging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.R;
import com.example.myapplication.paging.itemsource.Person;
import com.example.myapplication.paging.itemsource.PersonRecyclerPagingAdapter;
import com.example.myapplication.paging.itemsource.PersonViewModel;
import com.example.myapplication.paging.pagekeysource.PersonRecyclerKeyPagingAdapter;
import com.example.myapplication.paging.pagekeysource.PersonViewModel2;

public class PagingActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    RecyclerPagingAdapter recyclerPagingAdapter;
    StudentViewModel viewModel;
    PersonViewModel personViewModel;
    PersonRecyclerPagingAdapter personRecyclerPagingAdapter;
    private PersonViewModel2 personViewModel2;
    private PersonRecyclerKeyPagingAdapter personRecyclerKeyPagingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging);
        recyclerView =  findViewById(R.id.recycle_view);
        recyclerPagingAdapter = new RecyclerPagingAdapter();
        personRecyclerPagingAdapter = new PersonRecyclerPagingAdapter();
        // 1最新版本初始化 viewModel
//        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory())
//                .get(StudentViewModel.class);

        // LiveData 观察者 感应更新
//        viewModel.getListLiveData().observe(this, new Observer<PagedList<Student>>() {
//            @Override
//            public void onChanged(PagedList<Student> students) {
//                // 再这里更新适配器数据
//                recyclerPagingAdapter.submitList(students);
//            }
//        });
//        recyclerView.setAdapter(recyclerPagingAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //2ItemKeyDataSource测试
//        personViewModel = new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory()).get(PersonViewModel.class);
//        personViewModel.getPagedListLiveData().observe(this, new Observer<PagedList<Person>>() {
//            @Override
//            public void onChanged(PagedList<Person> people) {
//                // 再这里更新适配器数据
//                personRecyclerPagingAdapter.submitList(people);
//            }
//        });
//
//        recyclerView.setAdapter(personRecyclerPagingAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //3.KeyPageDataSource测试
        personRecyclerKeyPagingAdapter = new PersonRecyclerKeyPagingAdapter();
        personViewModel2 = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(PersonViewModel2.class);
        personViewModel2.getPagedListLiveData().observe(this, new Observer<PagedList<Person>>() {
            @Override
            public void onChanged(PagedList<Person> people) {
                Log.e("111", "onChanged: "+ people );
                personRecyclerKeyPagingAdapter.submitList(people);
            }
        });
        recyclerView.setAdapter(personRecyclerKeyPagingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}