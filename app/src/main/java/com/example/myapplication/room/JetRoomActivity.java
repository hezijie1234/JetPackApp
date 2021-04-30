package com.example.myapplication.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.myapplication.R;

import java.util.List;

public class JetRoomActivity extends AppCompatActivity {
    StudentViewModel studentViewModel;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jet_room);

        //数据库的操作应该是在子线程
//        DbTest t=new DbTest();
//        t.start();

        listView=findViewById(R.id.listView);
        studentViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(StudentViewModel.class);
        studentViewModel.getAllStudentLiveData().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                listView.setAdapter(new GoodsAdapter(JetRoomActivity.this,students));
            }
        });
            //运行一次创建数据，就注释
//        for (int i = 0; i < 50; i++) {
//            studentViewModel.insert(new Student("jett","123",1));
//        }
//
        new Thread(){
            @Override
            public void run() {
                int x=0;
                for (int i = 0; i < 50; i++) {
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    studentViewModel.update(new Student(6,"jett"+i,"123",1));
                }
            }
        }.start();
    }

    public class DbTest extends Thread{
        @Override
        public void run() {
            //数据库操作在这里进行
            AppDatabase jettDB= Room.databaseBuilder(getApplicationContext()
                    ,AppDatabase.class
                    ,"jettDB").build();
            StudentDao dao=jettDB.userDao();
            dao.insert(new Student("jett","123",1));
            dao.insert(new Student("jett1","123",2));
            dao.insert(new Student("jett2","123",3));
            dao.insert(new Student("jett3","123",4));

            List<Student> list=dao.getAll();
            Log.i("jett",list.toString());

            Student jett2=dao.findByName("jett3");
            Log.i("jett",jett2.toString());
            List<Student> allId=dao.getAllId(new int[]{2,3,4});
            Log.i("jett",allId.toString());

            List<StudentTuple> record=dao.getRecord();
            Log.i("jett",record.toString());

        }
    }
}