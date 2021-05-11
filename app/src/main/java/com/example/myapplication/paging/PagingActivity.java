package com.example.myapplication.paging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.paging.itemsource.Person;
import com.example.myapplication.paging.itemsource.PersonRecyclerPagingAdapter;
import com.example.myapplication.paging.itemsource.PersonViewModel;

public class PagingActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    RecyclerPagingAdapter recyclerPagingAdapter;
    StudentViewModel viewModel;
    PersonViewModel personViewModel;
    PersonRecyclerPagingAdapter personRecyclerPagingAdapter;
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
        personViewModel = new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory()).get(PersonViewModel.class);
        personViewModel.getPagedListLiveData().observe(this, new Observer<PagedList<Person>>() {
            @Override
            public void onChanged(PagedList<Person> people) {
                // 再这里更新适配器数据
                personRecyclerPagingAdapter.submitList(people);
            }
        });

        recyclerView.setAdapter(personRecyclerPagingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}