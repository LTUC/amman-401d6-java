package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Student {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "student")
    public String student;

    @ColumnInfo(name = "enroll_year")
    public Integer enrollYear;

    public Student(String student, Integer enrollYear){


        this.student = student;
        this.enrollYear =enrollYear;

    }
}
