package com.example.myapplication.room;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentRepository {


    private final StudentDao studentDao;

    public StudentRepository(Context context) {
        studentDao = AppDatabase.getInstance(context).userDao();
    }

    public LiveData<List<Student>> getAllStudent(){
        return studentDao.getAllStudentLiveData();
    }

    public void insert(Student ... student){
        studentDao.insert(student);
    }

    public void update(Student student){
        studentDao.update(student);
    }


}
