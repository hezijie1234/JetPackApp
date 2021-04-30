package com.example.myapplication.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {
    private StudentRepository studentRepository;

    public StudentViewModel(@NonNull Application application) {
        super(application);
        studentRepository = new StudentRepository(application);
    }

    public LiveData<List<Student>> getAllStudentLiveData(){
        return studentRepository.getAllStudent();
    }

    public void insert(Student ... student){
        studentRepository.insert(student);
    }

    public void update(Student student){
        studentRepository.update(student);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
