package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<Student> studentData = new ArrayList<Student>();

        studentData.add(new Student("Ahmad",2019));
        studentData.add(new Student("Ali",2020));
        studentData.add(new Student("Roaa",2021));

        // get the Recycler view
        RecyclerView allStudentRecyclerView = findViewById(R.id.recycleViewId);

        // set a layout manager
        allStudentRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // set the adapter for this recycler view
        allStudentRecyclerView.setAdapter(new StudentAdapter(studentData));

        Button addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener((v)->{
            Intent intent = new Intent(MainActivity.this,AddStudentActivity.class);
            startActivity(intent);
        });
    }
}